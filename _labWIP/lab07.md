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





