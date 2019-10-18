---
layout: lab
num: lab04
ready: true
desc: "Polynomials"
assigned: 2019-10-07 17:00
due: 2019-10-18 23:59
github_org: "ucsb-cs56-f19"
starter_repo: "https://github.com/ucsb-cs56-f19/STARTER-lab04"
---

# UPDATE 6:20PM, Thu October 17, 2019

If you get a warning about the `summary` attribute on the `<table>` tag when generating Javadoc for lab04, try changing this code in `Polynomial.java` from:

```
<table summary="each row ...">
```

to just:

```
<table>
```

I read in the JN7 text that `<table>` elements were supposed to have `summary` attributes for WAI compliance, but as it turns out, the version of `javadoc` we are using doesn't like that attribute.

You may also need to change `<sup>i<sup>` to `<sup>i</sup>`.   That's a typo.


# UPDATE: 7pm, Wed October 16, 2019

In order to get the Javadoc to publish correctly to GitHub Pages, you will need to add this to your `pom.xml`.   

It should go:
* right *after* the `</dependencies>` close tag 
* right *before* the `<build>` open tag:

```xml
  <!-- For use with github pages, to publish the site to the /docs subdirectory -->	
  <distributionManagement>	
    <site>	
      <id>website</id>	
      <url>file://${project.basedir}/docs/</url>	
    </site>	
  </distributionManagement>
```

You should also find this part of the `pom.xml` by searching for the string `org.apache.maven.plugins`

```xml
<plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-site-plugin</artifactId>
	<version>3.3</version>
</plugin>
```

And add in these lines:
* *after* the line `<version>3.3</version>`
* *before* the close tag `</plugin>

```xml
    <configuration>
       <dependencyLocationsEnabled>false</dependencyLocationsEnabled>
    </configuration>
```

This tells Maven to skip the "dependency report" when generating the site.  This will make the site:deploy command go a lot faster.

Then, make sure that your `.gitignore` has this in it:

```
docs/jacoco
```
But that it does NOT have this in it:

```
docs
```

That should allow you to do:

```
mvn javadoc:javadoc site:site site:deploy
```

And then, if you do:

```
git add docs
git commit -m "add javadoc to repo"
git push origin master
```

And if you go to the settings for your repo and turn on GitHub pages for the `docs` folder of the `master` branch, you should be able to see the docs at the url:

* <https://ucsb-cs56-f19.github.io/your-repo-name/apidocs>


In this lab:

-   using inheritance
-   using an `ArrayList`
-   overriding `equals`
-   overriding `toString`
-   problem solving

Step-by-Step
============

# Step 0: Set up your repo

This lab will build on the material we introduced about Maven and JUnit in previous labs.

You will also apply what you are learning from your reading about inheritance, object equality, and using the ArrayList class.

You may work as a pair, or individually on this lab (your choice).  Name your repo as appropriate.

Create your repo the same way you did for a previous lab:

   * under the <tt>{{page.org}}</tt> organization
   * name should be <tt>{{page.num}}-githubid</tt> OR <tt>{{page.num}}-githubid1-githubid2</tt> as appropriate
   * private, and initially empty.
   * add your pair partner as a collaborator

Then, create an empty directory for {{page.num}}, do a `git init` and then add remotes just as you did in previous labs.

The starter code is in <{{page.starter_repo}}>.  Visit that page for the approrpiate URL to add the `starter` remote.

## If pairing, register your pair on Gradescope

On Gradescope, you can mark your submission as a pair submission.    Be sure to do this if you are working in a pair.

## Working in a pair? Switch navigator/driver frequently and tradeoff who commits

If you are in your repo directory, and type git log at the command
line, you'll see a list of the commits for your repo.

Record that you are pairing on each commit message by putting the
initials of the pair partners at the start of the commit message.

E.g. If Selena Gomez is driving, and Justin Timberlake is
navigating, and you fixed a bug in your `getDanceMoves()` method, your
commit message should be `SG/JT fixed bug in getDanceMoves()`

* We should see frequent switches between SG/JT and JT/SG.
* We should see good commit messages, not just
   * `git commit -m "fixed something"` 
   * `git commit -m "my hands are typing words"` (see [XKCD 1296](https://xkcd.com/1296/))


Step 1: A note about the .git directory and the .gitignore file
---------------------------------------------------------------

If you aren't already there, cd into your repo directory.

-   repo is short for "repository"; your repo directory is something like ~/cs56/{{page.num}}-github1-github2
-   from now on, I'll just say "your repo directory" and assume you know what I mean.

As you may know, in Unix, "hidden files" are those that start with a dot.

-   There is one hidden directory in your repo, called .git
-   There is one hidden file in your repo called .gitignore

The `-a` flag of the Unix `ls` command can be used to list hidden files in directory listings.

-   You can use the `ls` `-a` (pronounced: ell ess minus ay) to get a short form directory list that includes these files.
-   You can use the `ls` `-al` (pronounced: ell ess minus ay ell) to get a long form directory list that includes these files.

### What is the .git directory?

NOTE: The contents of this section may be something you are tested on on Midterm Exam 1 and subsequent exams, so READ CAREFULLY!

The .git directory is the magic ingredient that turns an ordinary directory into a git repository.

-   It ONLY exists in the root directory of the repository.
-   It's presence causes EVERYTHING under that directory (all subdirectories, and sub-sub directories, etc) to be considered part of the repository
-   It's presence or absence is the ONLY difference between a plain old directory and a git repository.
-   That directory contains the ENTIRE HISTORY of the repository--every commit, every commit message, every version of every file, EVERYTHING.
-   It is stored in an intelligent data structure that uses lots of compression and algorithmic tricks to make it both small and fast.
-   Normally, you DO NOT NEED to mess with stuff in this directory, except sometimes making changes in the .git/config file.
-   Normally, you SHOULD NOT mess with stuff in this directory directly unless you REALLY know what you are doing.
    -   Instead, we use "git" commands that manipulate the contents of this directory while preserving its structure.

For now, that's all you need to know about the contents of the .git directory. However, if you want to learn more, you are encouraged to read ahead in the GIT pocket guide. The internals of git are fascinating, and the more you know, the more power you will have to make git do all kinds of wonderful tricks.

### What is the .gitignore file?

The .gitignore file is NOT inside the .git directory, but instead is considered a regular file in the repository.

-   That is, if you change it, you need to "git add", "git commit" and "git push" to make the changes take effect, just like any regular file in your repo.

BUT, the .gitignore file has special powers over the way that git operates.

Generally speaking, when we set up a repository, we want to store only SOURCE CODE:

-   For Java, we store the .java files in the repo, but NOT the .class or .jar files
-   For C/C++ we store the .c, .h, .cpp files in the repo, but NOT the .o files, and executables
-   etc.

We also don't want to store backup files, such as the files that emacs creates that end in tilde, such as Foo.java~, Bar.java~, etc.

The .gitignore helps us do that. Use the more command to list the contents of your .gitignore file, which will look something like this (if you created a .gitignore for Java and it has more contents than this, that is fine):

    -bash-4.2$ more .gitignore
    *~
    *.class
    -bash-4.2$

You see that there are two lines in this file.

-   The first line uses a wildcard pattern (\* means wildcard) to say that "any file that ends in ~" should be ignored by git.
-   The second line says: "any file that ends in .class should be ignored by git".

You will see that when you do "git status" in this repo, the backup and .class files don't show up. They are ignore by git. And that's the way we want it.

If you use emacs, you may want to make sure that this is in your `.gitignore`

```
*~
```

### Summary of this Step

In short:

-   Leave the `.git` directory alone unless you know what you are doing
-   Modify the `.gitignore` anytime there is some other kind of file that you also want git to ignore


Step 3: Publish Javadoc and Fix your README!
--------------------------------------------

These steps are needed to get full credit for the lab.  Up to 10% may be deducted for not following them.

* Find the comment at the top of Polynomial.java where it says "your name here", and add your name (or names)
* Generate the javadoc with these steps:
   ```
   mvn javadoc:javadoc
   mvn javadoc:test-javadoc
   ```
   
* If the following line appears in your `.gitignore`
  ```
  docs
  ```
  Then change it to:
  ```
  docs/jacoco
  ```
  That line may already be `docs/jacoco` depending on when you started the lab.
* Use `git add docs` and then do a commit and a push to push the javadocs to github on the master branch
* Go to your repo settings and find the section for Javadoc, and select "docs/ from master branch"
  * Note that the javadoc will be public even if the repo is private
  * Note that you may need to append `/apidocs` to the generated URL 
* Add your name/github, and your pair partner's name/github to your README.md
* Add links to your github repo and to your published javadoc in your README.md


Step 4: Java Programming
------------------------

Your programming task in this lab can be described very simply:
replace all the stubs in Polynomial.java with code that

-   implements the methods described in the javadoc comments
-   passes the unit tests in `PolynomialTest.java`
-   passes any other reasonable unit tests that the instructional staff might devise

It is possible (though unlikely) that there may be typos/errors in the
unit tests themselves. If you suspect this, please post something on the
typos channel on Slack.   The FIRST
person/pair partner to CORRECTLY identify each incorrect unit test
(supplying a correct test in its place) will receive extra credit.

If there are corrections to the tests, that information will be sent
out to you by email and posted on the "Instructor Announcements" forum
in Gauchospace.

Do NOT simply change the `PolynomialTest.java` file. We will test your
program against the original `PolynomialTest.java` file (with any
corrections that are distributed).

You may ADD tests to `PolynomialTest.java`, but you should not CHANGE the
tests that are listed there or delete tests, unless you have been told
to do so in an email from the instructor.

I suggest that you work in this order. There are also three hints for
debugging that follow this suggested order of work. You may want to
read though ALL of the instructions in Step 2 before starting
anything.

### Suggested order of work


1. Start by commenting out the toString method temporarily.
   * That will cause the class to use the ArrayList<Integer> `toString` method
     instead.  That way, while you work on other methods, you'll be able to
     see the ground truth of what's inside the Arrays.
   * Come back and uncomment it when you are ready to work on it.
2. You might want to do this so that you can focus on one method at a time:
   * `cd src/test/java/edu/ucsb/cs56/polynomial`
   * `cp PolynomialTest.java PolynomialTest.java.keep`
   * Then, delete the code inside `PolynomialTest.java` for all tests except the method you are working on.
   * Copy/paste the tests back into the file one at a time, and try to get one test at a time to pass.
   * Run the entire test suite each time, to make sure that when you get one test to pass, you don't break something else.
3. I suggest you then focus on these methods first, since they don't depend on anything else, and they are useful when
   getting other things to work:
   * `public static int degreeOfPolynomialCoeffsLowToHigh(int [] coeffsLowToHigh)`
   * `public static int degreeOfPolynomialCoeffsHighToLow(int [] coeffsHighToLow)`
   * `public static int [] lowToHigh(int [] coeffsHighToLow)`
   * `public static int [] highToLow(int [] coeffsLowToHigh)` 


4. Then, tackle these as a group.  There is a bit of a chicken and egg problem here, because
   the `equals` method is used by JUnit, and until it is correct, the JUnit test results
   may be misleading!  But you can't test `equals` unless you have a way to construct
   `Polynomial` objects.  You'll have to think about how to handle that.
  
   * `public boolean equals(Object o)`
   * `public Polynomial(int [] coeffsHighToLow)`
   * `public int getDegree()`

5. Next, before tackling the plus, times, and minus, get `toString` working.
   Bugs in `toString` can be maddening because the JUnit output uses toString.
   You'll be looking at test case output, and it won't represent what's really
   going on.  So get this working and solid before moving on.

6. Finally, I suggest doing `plus`, `times` and `minus`, in that order.
   There is a reason for doing it that way; it will save you work if you are
   clever.

7. When you are all done, you can submit on Gradescope.  Note that there may be
   extra tests on Gradescope for cases that you didn't consider.

<div style="text-align:center; font-weight:bold;">
Helper functions are a good idea
</div>

If you find that a certain method is getting a bit too complex, or if you find that you are repeating yourself a lot, you may want to factor some code out into helper methods.

There are two approaches to dealing with helper methods, each of which can be argued:

-   Make them private, since they are helper methods and no code outside the class should be using them.
-   Make them public, so that they can be easily unit tested.

My suggestion is: make them private UNLESS you write unit tests. And writing unit tests is a VERY GOOD IDEA. But, that's up to you.

### Additional debugging help

There are three additional things that can help while doing your debugging:

1. There is a main program that you can run to test basic Polynomial operations and printing of Polynomials. Here are some examples of the Unix command line to run that program. Note that you need single quotes around the Polynomials, and that the \* operator needs a backslash in front of it (so the Unix shell doesn't turn into a "wildcard" for filenames.) You can use this main to see what happens when you take in a Polynomial and then print it back out with the toString() method. If you pass just ONE polynomial in the command line, it simply gets "constructed" from the string, and then printed back out with toString. If you are using "debug print statements" (see item 2 below) then it can be hard to make sense of those in the JUnit test output. But, running the main, you can isolate what's happening with just ONE polynomial at a time, and see your debugging print statements in a less cluttered, sensible order.
    -   `java -cp build Polynomial '10x^2 - 20x + 3'`
    -   `java -cp build Polynomial '2x + 3' + '4x - 5'`
    -   `java -cp build Polynomial '2x + 3' \\\* '4x - 5'`
    -   `java -cp build Polynomial '2x + 3' - '4x - 5'`


1. There is a public static boolean variable called `debug` inside the Polynomial class. You can set this to true, and then print debugging output. You'll see examples of that in the constructor that creates a Polynomial object from a String.


1. If you need to, for debugging purposes, you may add an additional class called PolynomialDebugging. That class might look something like this:

   ```java

       public class PolynomialDebugging {

         public static void main (String [] args) {
           if (args[0]=="1") {
               // Put some code you want to test here
            } else if (args[0]=="2") {
               // Put some code you want to test here
            } else 
            .. // continue in this fashion
            }
         }
       } 
   ```

   The point of this is to be able to isolate one small thing that you
   are trying to debug, independent of all the confusing output of all
   the many test cases, and so you can see your debugging print
   statements more easily. 
   
   If you do this, please DO `git add`, `git commit`,
   `git push` the `PolynomialDebugging` class. Note that we won't grade that
   class---and you aren't required to use one. This is just a suggestion
   in case you find it helpful.

# Other Notes

By the way, the constructor that creates a Polynomial object from a String is already written for you, and is, 
to the best of our knowledge, correctly implemented. It should pass its tests once the other methods are correctly 
implemented.  However, until you get `toString`, the constructor that takes an `int []`, and the `.equals` method working, 
however, some of its tests may fail.

When all the tests pass, you may then add additional tests for other kinds of polynomials (both well formed and badly formed) 
that are not covered by the tests that you see in `PolynomialTest.java`. The number of such tests is up to you. 
There is such as thing as too many tests, and there is such a thing as not enough tests. 
Finding the right balance is part of the exercise here.

The reason for adding the additional tests is to try to anticipate what the "secret" 
tests might be testing for, i.e cases not already covered by the tests in `PolynomialTest.java`.

When you feel you have tested sufficiently, and all your tests pass, 
you are ready for your final check over, then submission on Gauchospace.


Step 3: Preparing to submit: final code walkthrough against rubric
------------------------------------------------------------------

Now, you are almost done. The last four steps are to:

-   check your code to be sure you will maximize your grade, and
-   generate the final version of your javadoc
-   do a final commit and push for any last minute changes
-   submit on Gauchospace.

### Checking over your code

First, check your code Check it against two things:

1.  **ALL OF THE INSTRUCTIONS IN THIS LAB ASSIGNMENT.** Start over at the very first step. If you are working a pair, have one pair partner read through the instructions, and have the other pair partner check the code (or in the case of the javadoc, check both the code, and what appears on the web.)
2.  **ALL OF THE ITEMS IN THE GRADING RUBRIC**. The grading rubric for this lab appears near the end of this file. It is the checklist that the TAs and instructor will use to determine your lab grade. Again, if working in a pair, divide up the responsibility (switching roles)—one person read the rubric items out loud, and the other person checks.

Even if you "solo programmed" this lab, you may want to see if you can find someone in the lab that also solo programmed, and ask him/her to be a "rubric buddy" with whom you can take turns doing this checklist step.

### Updating javadoc

If you are working along, right before you do the final submission, do a "git commit", "git push" and then run "mvn javadoc:javadoc".

Then do the steps to publish the updated javadoc to your public repo.

### Final git pull, git status, git add, git commit, git push origin master

Go through the git steps one last time in case you made any last minute changes. 
Be sure that your repo is up to date. When it is, you are ready to submit on Gauchospace.

# Grading:

* 100 points are determined by the Grade on Gradescope.

There may be additional deductions, however if you failed to follow any of the following instructions:
* Did you follow the instructions for setting up your repo? (naming, making it private, pulling in starter code,
     adding partner as collaborator)
* Is the code you submitted to Gradescope that same as the latest code that you've pushed to Github?
* Did you add your name/github, and your pair partner's name/github to your README.md?
* Did you add links to your github repo and to your published javadoc in your README.md?
* Are your commit messages in the proper form, with the initials of driver/navigator and with reasonable messages describing the changes in each commit?
   
