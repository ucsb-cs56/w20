---
layout: lab
num: lab07
ready: true
desc: "Spring Boot Skills"
assigned: 2019-11-07 17:00
due: 2019-11-05 23:59
github_org: "ucsb-cs56-f19"
org: "ucsb-cs56-f19"
gauchospace_url: "https://gauchospace.ucsb.edu/courses/mod/assign/view.php?id=TBD"
prev: lab06
---

<div style="display:none" >
Look here for formatted version: http://ucsb-cs56.github.io/f19/labWIP/lab07
</div>



This lab builds on your work from {{page.prev}}.

# What if I didn't finish {{page.prev}}

If you were not successful in completing {{page.prev}}, you should go
back and complete any unfinished steps from {{page.prev}} first.  You
will get partial credit for this lab simply for doing the unfinished
steps from {{page.prev}}, even if you missed the deadline for
{{page.prev}}

# Individual lab

This is an **individual** lab on the topic of Java web apps on Heroku.

You may cooperate with one or more pair partners from your team to help in debugging and understanding the lab, but each person should complete the lab separately for themselves.


# Goals

You will learn:

*  How to set up a repo for automated testing with Travis-CI.  This
   kind of automated testing is called "continuous integration".

*  You'll make a few simple changes to static HTML, along with unit
   tests for those changes.   In the process you'll learn about making
   clean git commits that combine tests and changes.

*  You'll then add an HTML form using Thymeleaf that can gather some
   information from a user, and echo back that information on another page.

   Initially, that page is just a placeholder.  But we'll see that we can
   do some testing to ensure that the page contains the correct elements.

*  Finally, you'll learn how to request data from an API, and display that
   data to the user.

Throughout, most of the steps will be pretty cookbook.   You'll be walked through most of what you need to do.   These are skills though, that when you do this in the context of a project, you'll have to figure out for yourself.

# How we will proceed

Here is an overview of how the lab will go.  These are not the detailed
instruction; those follow later on this web page.  This is just an outline
so that you can get the big picture before you start.

1. We will start with a new, empty, public repo called:

   * <tt>{{page.num}}-githubid</tt>.

   We are making the repo public so that we can set up Travis-CI; the
   free tier requires the repo to be public.

2. We'll then add a remote for our code from {{page.prev}}, and pull in that
   code as our starting point.

3. We'll next see that when we type `mvn test`
   some of the unit tests in the starter
   code are not passing.   
   
   We'll create a bug fix branch in which
   we make the necessary changes to fix this.

   We'll then do a pull request on that branch.

   Finally, we'll merge that branch into master.

4. Next, now that our test cases are passing,
   we'll set up the javadoc, website, and jacoco report, and publish it
   to github pages on the master branch.

5. We'll then get the app running on both localhost and Heroku as we did for
   {{prev.num}}.  For `localhost`, this should be straightforward; we can reuse the
   localhost credentials from {{prev.num}}.  For Heroku, we'll have to create a new
   Github OAuth client id and client secret, since the Heroku URL will have
   changed.


6. We'll then set up the repo for Travis-CI.   This allows us to ensure
   that all of the test cases are run automatically each time we
   do a pull request from a branch.

7. We'll then create a feature branch with

   ```
    git checkout -b branchName
   ``` 
   to make some small, simple changes to the static HTML along
   with writing test cases for those changes.  In the process we'll discuss
   writing good commit messages, making frequent small commits,
   and combining changes with the test cases
   for those changes in single commits.

   We'll finish this step by doing a pull request for that branch,
   and then merging it into master.

8. We'll now create a second branch for creating a form.

   On this branch, we will create a simple HTML form using Thymeleaf.

   We'll need to add a controller method that routes the user to this form,
   and be sure that a link on the navigation menu routes to this page.

   That page will gather some information from the user, and then
   echo that information back on a second "results" page.

   This is an intermediate step; echoing the information back is not
   useful in and of itself.  In the next step, we'll do something useful
   with that information.

   This step will end with a second pull request.  You should then merge
   that pull request into master.

9. In this step, we'll make yet another 
   branch where we do something useful
   with the information on the results page.  We'll make a call to an API
   that provides information in JSON format.

   In this step we'll only
   echo that JSON information on the page; it won't yet be in a format
   that is pleasing to an end user.   But we'll be able to see that we
   are making progress.

10. In this final step, we'll learn how 
    to transform that JSON string into
    usable Java objects, and use those Java objects to put useful information
    on the page.

    There will be one final pull request at this stage, and we'll be done.n
   
# Step by step instructions


## Step 1: New empty public repo

We will start with a new, empty, public repo called:

* <tt>{{page.num}}-githubid</tt>.

We are making the repo public so that we can set up Travis-CI; the
free tier requires the repo to be public.

## Step 2. Add remote for {{page.prev}}

Now add a remote for the code from your {{page.prev}} repo:

<tt markdown="1">git remote add {{page.prev}} git@github.com:{{page.org}}/{{page.prev}}-<i>githubid</i>.git</tt>

Then, to pull the code from your {{page.prev}}  work into this new repo, use:

<tt markdown="1">git pull {{page.prev}} master</tt>

Finally, to push this to github, do:

```
git push origin master
```

Check that you see your code on github under the  repo name <tt>{{page.num}}-githubid</tt>.


## Step 3: Fixing tests in a bug fix branch.

Now, type `mvn test`.  You'll see that there
are some test failures.

We are going to create a separate branch
in github in which we fix those problems,
and then we'll do a pull request to merge that
branch into master.

Now, to be sure you have the latest code (in case you changed anything on another computer, or on github), do this in your terminal before proceeding:

```
git pull origin master
```

We will now create a feature branch. The first two letters should be your initials, e.g. `pc`, `ab`, etc.  

The rest should be `fixFailingTests`.  So the branch name will be something like `pcFixFailingTests` or `abFixFailingTests`.

Type this (but not literally `xx` unless your first and last name both start with `x`)

```
git checkout -b xxFixFailingTests
```

Now we are ready to look at the code that needs to be fixed.

Running `mvn test` shows us two tests that are failing:

```
HomePageTest.getHomePage_BootstrapLoaded:43 Status expected:<200> but was:<302>
[ERROR]   HomePageTest.getHomePage_hasCorrectTitle:54 XPath //title expected:<Getting Started: Serving Web Content> but was:<Title of your page goes here>
```

Let's tackle these one at a time.

First, open up the file `src/test/java/hello/HomePageTest.java` and look for the method `getHomePage_hasCorrectTitle` at line `54`.

Notice that this comes directly from the test failure message.  JUnit test output may look very messy at first, but when you learn how to read it, you can find exactly where to go in the code to fix a problem.

What we see is that this test is looking for the `<title>` element on the home page (i.e. `/`).

It is looking for that to be the value 
```
Getting Started: Serving Web Content
```

But what it is finding instead is:

```
Title of your page goes here
```

Neither of those, however, is really a good title for our page.  Let's change the test so that it is looking for:

```
CS56 Spring Boot Practice App
```

After making this change, run the test again with , and it should fail again, but with the message that it was expecting `"CS56 Spring Boot Practice App"` and it got instead `"Getting Started: Serving Web Content"`.

Now, we need to find the actual place in the code that has the content `Title of your page goes here`.  Note that when we search for it, we should NOT include the `""`, since those might or might not be part of the code; it depends on where that value is coming from (HTML code, JavaScript code, Thymeleaf template, or Java code).

If you are using a IDE that allows you to search across multiple files, you can use that to search for the string.  However, if you don't know how to do that, it is handy to know this Unix trick you can use at command line.

```
mvn clean
grep -r "Title of your page goes here" .
```

The `grep -r` command recursively searches a directory for a string.  The `""` here are not part of what is being searched for; they tell the Unix shell that `"Title of your page here"` is one argument to `grep`.   The `.` signifies the curent directory.

Here's the output I got:

```
pconrad$ grep -r "Title of your page goes here" .
./src/main/resources/templates/page1.html:    <title>Title of your page goes here</title>
./src/main/resources/templates/index.html:    <title>Title of your page goes here</title>
./src/main/resources/templates/page2.html:    <title>Title of your page goes here</title>
pconrad$
``` 

We can see that this string appears in three files, all of them `html` files under `src/main/resourcs/templates`.  The one we want is `index.html`, since that is the home page of the application.

Side note: I do a `mvn clean` first before I do the `grep -r`.  That's because if I don't, the `grep -r` will also search my `target` directory and come up with many distracting false hits.  The `mvn clean` gets rid of the `target` directory. (It will come back each time I use `mvn` commands to build my project.)

Now, back to coding: change the `<title>` element in `index.html` to the string in our test.

It should look like this (probably on line 4):

```
<title>CS56 Spring Boot Practice App</title>
```

Re-run `mvn test`.  The test should now pass.  

So, we'll do a commit with a commit message, where `xx` is replaced by your initials:

```
git commit -m "xx - fix failing test for title element on home page"
```

Then, do this (remembering to replace `xx` with your initials for branch name:)

```
git push origin xxFixFailingTests -u
```

The `-u` specifies the `upstream` for `git`.  It makes that remote (`origin`) and branch (`xxFixFailingTests`) the default for `git push` unless and until you change the `upstream` to something else.  This is handy if you are going to be doing many commits in a row on the same branch.

We are now going to tackle the other failing test.

The message on this one is:
```
HomePageTest.getHomePage_BootstrapLoaded:43 Status expected:<200> but was:<302>
```

The status code here refers to an HTTP status code.  Each time we make a web request, if we get back a response, that response contains a status code.   

The status code `200` is we get back a reponse  `200`, which signifies `OK`, i.e a normal successful completion of the request.

What we got instead was `302`, which signifies `Found`.   This is used when a URL is being redirected to another URL.   

This is typically a symptom that we are trying to access a page that requires authentication without being authenticated yet.    However, note that this test is supposed to be access the home page, which typically does NOT require authentication.

As it turns out, the test has a simple typo in it.  The test code looks like this:

```
@Test
    public void getHomePage_BootstrapLoaded() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(xpath(BootstrapLiterals.bootstrapCSSXpath).exists());
        for (String s: BootstrapLiterals.bootstrapJSurls) {
            String jsXPath = String.format("//script[@src='%s']",s);
            mvc.perform(MockMvcRequestBuilders.get("/greeting").accept(MediaType.TEXT_HTML))
              .andExpect(status().isOk())
              .andExpect(xpath(jsXPath).exists());
        }
    }
```

What is happening here is that the test is trying to load the home page (`get("/")`) and then it makes sure that the page loads ok.

It then tries to ensure that the CSS file for Bootstrap loaded ok.

Finally, it checks that three different JavaScript files loaded ok; it does that in a loop.  But here, we find the typo: 

```
MockMvcRequestBuilders.get("/greeting")
```

That is trying to access the page `/greeting` which is a page that doesn't even exist in the web app anymore!   Instead, that call should read:

```
MockMvcRequestBuilders.get("/")
```

Note: the part that comes before and after should remain unchanged, so the entire line after the change should read:

```
mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.TEXT_HTML))
```

Make this change.  Then try running `mvn test` again.  

Note: If the tests don't pass because of a "compilation problem" where the class `BootstrapLiterals` cannot be found, try doing
`mvn clean test` instead.  This cleans out all of the old compiled code  before running the test; it's equivalent to doing:

```
mvn clean
mvn test
```

I have found that sometimes if you get compilation problems, doing a `mvn clean` makes them go away.  Not always; sometimes you really did miss a semicolon or something.  But if it's saying that a given class is not there, when it clearly is, sometimes it's just confused, and a fresh compile fixes things.

At this point, you should have a clean `mvn test` run.  If so, commit your second change to `HomePageTest.java` (remember, your initials, not `xx`)

```
git add src/main/java/hello/HomePageTest.java
git commit -m "xx - fix typo in test cases for bootstrap"
git push 
```

You should now be ready to do a pull request from this branch to master.

You can do this by going to the pull request menu on github.com for your repo.

Here's what a PR should look like for this branch:

<<<<<<< Updated upstream
![](newpr-01.png)
{:width="600" height="800"}
=======
![](newpr-01-30.png)

Create the pull request.

Then merge the pull request into the master branch.   You do this on the Github website by clicking the green "Merge" button.

After you do that, at the command line, do this:

```
git checkout master
git pull origin master
```

This should pull the changes into the master branch on your local repo.  Try running `mvn clear test` again to be sure.

Then you are ready for the next step.

## Step 4: javadoc, jacoco, website

We'll set up the javadoc, website, and jacoco report next, and publish it to github pages on the master branch.

Run these commands:

```
mvn javadoc:javadoc
mvn test
mvn jacoco:report
mvn site
mvn site:deploy
```

Then do a `git status`.  You should see under the `docs` directory that you now have an `index.html` file as well as a subdirectory for `apidocs` and a subdirectory for `jacoco`.   

Use these commands to push these changes to github pages:

```
git add docs
git commit -m "add javadoc and jacoco report"
git push origin master
```

Then, visit the settings page of your repo, i.e. 

* <https://github.com/{{page.org}}/{{page.num}}-githubid/settings>

Find the section where you turn on GitHub pages.  Turn this on, and then visit the page.  You should see a web page with links to your javadoc, and your jacoco report.  

Make sure it works before moving on.  

If it does, then do an additional commit where you add a link to the web page of your documentation into your README.md, i.e. a link to this URL (where githubid is replaced with yours)

<https://ucsb-cs56-f19.github.io/lab07-githubid/>

Commit this change to the master branch.



## Step 5: Deploy app on localhost and heroku

Now, we'll get the app running on localhost first, then heroku.

To get it running on localhost, copy the `localhost.json` from the directory where you worked on {{page.prev}} into your current directory.   You should be able to reuse the client-id and client-secret values, since you are still running on the same web address, i.e. `http://localhost:8080`.

Type `mvn spring-boot:run` and see if you can access the web app, login, and logout.

Then, following the same steps as you did in {{page.prev}}, get your app working on heroku, under the app name `cs56-f19-lab07-githubid`.

That is, your URL should be:

`cs56-f19-lab07-githubid.herokuapp.com`.

You'll have to set up a new Github OAuth app.  You can find the instructions in the README.md for the starter code from {{page.prev}} which you copied into your current lab.


# Step 6: Set up repo for Travis-CI

We'll then set up the repo for Travis-CI.   This allows us to ensure that all of the test cases are run automatically each time we
do a pull request from a branch.

Note: there could be complications here with OAuth, i.e. we may need to mock and stub the OAuth stuff to get the test cases to pass if we are checking any pages that require being logged in.

TODO: Enter set up for Travis-CI STEPS

# Step 7: First feature branch: `xxSmallUIFixes`

Now, to be sure you have the latest code (in case you changed anything on another computer, or on github), do this in your terminal before proceeding:

```
git pull origin master
```

We will now create a feature branch. The first two letters should be your initials, e.g. `pc`, `ab`, etc.  

The rest should be `SmallUIFixes`.  So the branch name will be something like `pcSmallUIFixes` or `abSmallUIFixes`.

Type this (but not literally `xx` unless your first and last name both start with `x`)

```
git checkout -b xxSmallUIFixes
```

Now on this branch, we are going to first change one unit test.  That test is 

7. We'll then create a feature branch with `git checkout -b branchName`.


   to make some small, simple changes to the static HTML along
   with writing test cases for those changes.  In the process we'll discuss
   writing good commit messages, making frequent small commits,
   and combining changes with the test cases
   for those changes in single commits.

   We'll finish this step by doing a pull request for that branch,
   and then merging it into master.

8. We'll now create a second branch for creating a form.

   On this branch, we will create a simple HTML form using Thymeleaf.

   We'll need to add a controller method that routes the user to this form,
   and be sure that a link on the navigation menu routes to this page.

   That page will gather some information from the user, and then
   echo that information back on a second "results" page.

   This is an intermediate step; echoing the information back is not
   useful in and of itself.  In the next step, we'll do something useful
   with that information.

   This step will end with a second pull request.  You should then merge
   that pull request into master.

9. In this step, we'll make yet another branch where we do something useful
   with the information on the results page.  We'll make a call to an API
   that provides information in JSON format.

   In this step we'll only
   echo that JSON information on the page; it won't yet be in a format
   that is pleasing to an end user.   But we'll be able to see that we
   are making progress.

10. In this final step, we'll learn how to transform that JSON string into
    usable Java objects, and use those Java objects to put useful information
    on the page.

    There will be one final pull request at this stage, and we'll be done.n


# OLD STUFF

# Step 1: Create an empty PUBLIC repo called `lab06-githubid`

Create an empty PUBLIC repo called `lab06-githubid` UNDER the
organization <tt>{{page.github_org}}</tt>.  * Initially, no README.md,
no `.gitignore`, no license

Clone that repo somewhere and cd into.

Then add this remote:


<tt>git remote add starter {{page.starter}}</tt>

Then do:

```
git pull starter master
```

# Step 2: Get OAuth working on Localhost

The README.md in the repo itself has instructions for getting the app working on localhost.

Follow these instructions.

# Step 3: Get OAuth working on Heroku

The README.md in the repo itself has instructions for getting the app working on Heroku.

Follow these instructions.


# Step 4: Submitting your work for grading

When you have a running web app, visit <{{page.gauchospace_url}}> and make a submission.

In the text area, enter something like this, substituting your repo name and your Heroku app name:

<div style="font-family:monospace;">
repo name: https://github.com/chrislee123/spring-boot-minimal-webapp<br>
on heroku: https://cs56-{{site.qxx}}-{{page.num}}-chrislee123.herokuapp.com<br>
</div>

Then, **and this is super important**, please make both of those URLs **clickable urls**.

The instructions for doing so are here: <https://ucsb-cs56.github.io/topics/gauchospace_clickable_urls/>


# Grading Rubric:

* (20 pts) Having a repo that is built with starter code from <tt>{{page.starter}}</tt>
* (30 pts) Having a running web app at <tt>https://cs56-{{site.qxx}}-{{page.num}}-<i>githubid</i>.herokuapp.com</tt>
* (30 pts) GitHub OAuth is working in this app.
* (10 pts) There is a post on Gauchospace that has the correct content
* (10 pts) The links on Gauchospace are clickable links (to make it easier to test your app)





