---
assigned: 2020-01-21 11:00
assigned2: 2020-01-22 12:30
desc: Testing and Test Case Coverage
due: 2020-01-29 23:59
due2: 2020-01-29 23:59
github_org: ucsb-cs56-w20
layout: lab
num: lab03
ready: true
starter_repo: https://github.com/ucsb-cs56-w20/STARTER_lab03
---

<div style="display:none;">
https://ucsb-cs56.github.io/w20/lab/lab03
</div>


READY: Except for the Gradescope autograder; that should be ready by 5pm Tuesday 01/21

# lab03

In this lab:

-   using Maven instead of Ant
-   using packages
-   writing your own JUnit tests
-   test coverage using Jacoco


## Working in a pair? Switch navigator/driver frequently and tradeoff who commits

If you are in your repo directory, and type git log at the command
line, you'll see a list of the commits for your repo.

Record that you are pairing on each commit message by putting the
initials of the pair partners at the start of the commit message.

E.g. If Selena Gomez is driving, and Justin Timberlake is
navigating, and you fixed a bug in your `getDanceMoves()` method, your
commit message should be `SG/JT fixed bug in getDanceMoves()`

We should see frequent switches between SG/JT and JT/SG.


Step-by-Step
============

# Step 0: Set up your repo

You may work individually or as a pair on this lab.  However, if you work as a pair, please:
* Pair with someone from your same team
* Consider pairing with someone *different* from who you paired with before
* Remember to name the repo correctly, and also to add your pair on Gradescope each time you submit

If there is some reason this is not feasible, please check with your mentor before starting.

Create your repo the same way you did for [lab01](/lab/lab01/)
   * under the <tt>{{page.org}}</tt> organization
   * name should be <tt>{{page.num}}-githubid</tt> OR <tt>{{page.num}}-githubid1-githubid2</tt> as appropriate
   * private, and initially *empty* (no README.md, .gitignore or LICENSE).
   * add your pair partner as a collaborator

Clone this empty repo into your `~/cs56` directory, or wherever you prefer to work.

The starter code is in <{{page.starter_repo}}>.  Visit that page for the approrpiate URL to add the `starter` remote.

To add the starter as a remote, cd into the repo you cloned, then do:

<div>
<tt>git remote add starter {{page.starter_repo}} </tt>
</div>

Then do:
```
git pull starter master
git push origin master
```

That should get you set up with the starter code.

# Step 1: Get oriented to using Maven instead of Ant

A few things to notice:

* Under `src`, there are two directory trees:
   * `src/main/java/edu/ucsb/cs56/pconrad/menuitems` contains regular Java classes.
   * `src/test/java/edu/ucsb/cs56/pconrad/menuitems` contains the test classes.

Don't change the package from `pconrad` to your name; the Gradescope autograder is looking for the code under the `edu.ucsb.cs56.pconrad.menuitems` package.
So each source file:

* must be under that directory path when it is compiled, and
* must have `package edu.ucsb.cs56.pconrad.menuitems;` as the first line in the file

Here are the commands you'll need as you work with the code. Try them out now.

| To do this | Type this command | Notes |
|-|-|-|
| compile the code | `mvn compile` | |
| reset everything | `mvn clean` | | 
| run the tests | `mvn test` | |
| generate javadoc | `mvn javadoc:javadoc site site:deploy` | 
| generate javadoc *and* copy into docs directory for GitHub pages | `mvn javadoc:javadoc site site:deploy` | 
| generate a report of test coverage | `mvn test jacoco:report ` | Open the file `target/site/jacoco/index.html` in a browser to read the report |
| generate a jar file | `mvn package` | |


# Step 2: Start writing code to make tests pass

In this lab, you'll be implementing several methods of a class called `MenuItem` that represents
item on a restaurant Menu.

(There is a follow up lab in which we will add a `Menu` class that uses these menu items; but
we need to discuss sorting, `java.lang.Comparable`, `java.util.Comparator`,
and Java lambda expressions in lecture first before we get to that.)

A `MenuItem` represents an item on the menu of a restaurant.  It has three attributes:
* the menu item name, e.g. `"Small Poke Bowl"`
* the price, in cents (e.g an item that costs $1.49 is represented by the integer 149)
* a category such as `"Beverages"` or `"Poke Bowls"`


Note that the starter code:
* Has stubs for SOME of the needed methods, but NOT ALL of them
* Has unit tests for SOME of the needed methods, but NOT ALL of them

YOU WILL NEED TO WRITE SOME OF YOUR OWN TESTS.

So you'll need to do a bit more work than you may be used to.

I suggest that you work in this order:
* <b>First, Add stubs for all of the methods that don't have them yet.</b>  
   * Until you do this, you won't be able to run any of the instructor unit tests on Gradescope.
   * The reasons is that the instructor tests won't compile against your code unless and until you have those methods.
* <b>Then, try submitting on Gradescope</b>
   * At this point, you should have a clean compile for both the student and instructor code, though you won't be passing most of the unit tests.
* Then, one at a time, work on each method
   * If the method doesn't have a unit test yet, <b>write the test first and see it fail</b>.
   * Then make the test pass.
   * Then submit on Gradescope and see if the test for that method passes on Gradescope
   * Continue until all of your methods work.

# Details about methods of MenuItem

The constructor has the signature:
```
public MenuItem(String name,
                int priceInCents,
                String category)
```

Here are the instance methods you'll need to implement for `MenuItem`

| Modifier and Type	| Method | Description |
|-|-|
|String	| getCategory() | Returns the category of the menu item |
|String	| getName() | Returns the name of the menu item |
|String	| getPrice() | Returns the price, formatted as a string with a $.
|String	| getPrice(int width) | Returns the price, formatted as a string with a $, right justified in a field with the specified width. |
| int	| getPriceInCents() | get the price in cents only |
| String	| toString() | return a string in csv format, in the order name,price,cateogry. <br> For example: `"Small Poke Bowl,1049,Poke Bowls"`<br>In this case, the price is unformatted; just an integer number of cents. |


# Step 3: Learning about Test Coverage

Now, did you really write unit tests for all of your code? Let's check!

We can automatically compute "test case coverage", using a tool call JaCoCo (Java Code Coverage).

Read these short articles about test coverage before moving to step 4:
* <https://ucsb-cs56.github.io/topics/testing/>
* <https://ucsb-cs56.github.io/topics/testing_jacoco_reports/>

Once you've looked over those, it's time to check your test coverage, which
we'll do in Step 4.

# Step 4: Checking Test Case Coverage

Be sure that you've added your pair partner to your submissions on Gauchospace

Then, check your test coverage:
* Run: `mvn test jacoco:report`
* Then, open the file `target/site/jacoco/index.html` in a browser:
   * In CSIL or Phelps 3525, from your top level repo directory, use
      either `firefox target/site/jacoco/index.html` or `google-chrome target/site/jacoco/index.html`
   * That will also work if you are ssh-ing in to CSIL, but only if you have X11 forwarding enabled.
   * If working *directly* on your own machine (i.e. not ssh-ing in), you can probably
      just double-click on the file `target/site/jacoco/index.html` to open it.

Some of the points in the manual inspection may be awarded on the basis of having good test coverage.  
While 100% test coverage is not always the goal, in this particular exercise, it *should be possible*.   

So if you see that you don't have 100% test coverage, go back and write some additional unit tests.

## How to read the test coverage reports

* If any line of code is red, that means it is not tested at all&mdash;it is being missed by *line coverage*
* If a line of code is yellow, it means there are multiple ways to execute the line.
   * it may have an if/else, or a boolean expression involving `&&` or `||`, and thus there are multiple paths through the code (multiple branches).  
   * Yellow means it is being missed by *branch coverage*; some branches are covered, and others are not.   
   * Think about the multiple paths through the code and be sure your tests are coverage all of them.

# Step 5: Try to get as close to 100% coverage as you can

Keep reworking your code until you get as close as you can to 100% test coverage.


As we'll discuss in lecture, 100% coverage isn't always necessary or even desirable, but
in this case you should be able to get there, or at least pretty close.

One method that it's definitely ok to not have test coverage for (in this particular project) is the `public static void main(String [] args)` method; that method is only there as a placeholder, and contributes nothing to the correctness of the code.   If it did, we'd need to consider writing tests for it (and that is possible, though a little awkward.)

Resubmit on Gradescope once you've gotten as close to 100% as you think you can get.

If there are lines of code that are NOT coverage by tests, add some explanation in your
README.md as to why it wasn't feasible to test those lines of code.

# Step 6: Before final submission on Gradescope

* Make sure that your README.md file has your github id(s) and name(s) (Both if you worked as a pair.)
* Make sure that your README.md file has a link to your javadoc, and that your javadoc is readable online
* Make sure that your README.md file has a link to your private repo in the <tt>{{page.github_org}}</tt> repo on GitHub.

# Troubleshooting

If you get this message from Gradescope:

```
Could not locate expected jar file to run unit tests: 
target/menuitem-1.0-fat-tests.jar
```

what that actually means is that autograder was not able to compile the instructor tests against your solution.

That is probably because your solution does not yet contain stubs for one or more of the required methods.  

For example, if you don't implement stubs for the `getPriceInCents()` or the `getCategory()` methods before you submit to Gradescope, then you'll probably get the message about the `target/menuitem-1.0-fat-tests.jar` being missing.

You have to implement stubs for all of the specified functions before you will get test results for any of them on Gradescope.  So writing those first is a really good plan.

# End of description for {{page.num}}
