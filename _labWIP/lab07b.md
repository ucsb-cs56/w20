---
layout: lab
num: lab07b
labnum: lab07
ready: true
desc: "Spring Boot Skills"
assigned: 2019-11-08 17:00
due: 2019-11-16 23:59
github_org: "ucsb-cs56-f19"
org: "ucsb-cs56-f19"
gauchospace_url: "https://gauchospace.ucsb.edu/courses/mod/assign/view.php?id=TBD"
prev: lab07a
starter: 
---

<div style="display:none" >
Look here for formatted version: http://ucsb-cs56.github.io/f19/labWIP/lab07n
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

See Goals section of  [lab07a](lab07a).

# Picking up from step 5 of [lab07a](lab07a).

Please return to your same repo:

* <tt>{{page.labnum}}-githubid</tt>.

We will work with this repo, and with the Heroku app you configured for {{lab.prev}}

   
# Step by step instructions


# Step 6: Set up repo for Travis-CI

We'll start this lab by setting up our repo on Travis-CI.

Setting up our repo on Travis-CI will set it up for "continuous integration"&mdash;that's what CI stands for.

Continuous integration means that we try to integrate new code into the code base early and often, and that we run all of the tests of our code base each time we do that.

With Travis-CI setup, each time you push code to GitHub, or do a pull request, a server in the cloud (at the Travis-CI.org website) will pull your repo, and run all of your JUnit tests.  You'll get an indication on the GitHub site for your repo whether the tests passed or not.

To set up your repo for Travis-CI, the first step is to copy the two line file `.travis.yml` from the starter code repo <{{page.starter}}> into the root of your repo.  (For this change, we'll make an exception and just do it directly on the master branch.)

So, please copy that file in to your repo, and do:

```
git add .travis.yml
git commit -m "xx - add .travis.yml for Travis-CI"
git push origin master
```

The next step is to visit the following website, and login with your GitHub account:

<https://travis-ci.org>

Once there, at the upper left hand corner of the dashboard, you should see a small plus sign next to the text "My Repositories".  You want to click this `+` sign as shown in this image:

![Travis Dashboard + sign](travis-dashboard-plus-sign-30.png)

That takes you to a page where you can add the <tt>{{page.org}}</tt> GitHub organization to your authorized organizations for Travis-CI.

You might have to scroll down the left column where the text says:

> MISSING AN ORGANIZATION? <br>
> <u>Review and add</u> your authorized organizations

On the page, the text "review and add" is a link; if you click it, you should be able to enable Travis-CI access for <tt>{{page.org}}</tt>.

Once you do that, you should be able to see the organization 
<tt>{{page.org}}</tt> in the left hand column.   If you click on it, you should then be able to see your repo, and enable it for Travis-CI by clicking the small button next to the repo name. 

It can be a bit confusing, but if you are patient with yourself and the site, you'll figure it out.  If after trying for a while, you are still having difficulty, ask a mentor, TA or the instructor for assistance.

Once you've got the repo enabled for Travis-CI, there will a web page specifically for your repo, with the url:

* <https://travis-ci.org/{{page.org}}/{{page.labnum}}-githubid>

where <tt>{{page.org}}/{{page.labnum}}-githubid</tt> is the name of your repo.   On that page, at the upper right, you should be able to find a button with the text "More Options". Click on this reveals the following menu:

![More Options Menu](travis-more-options-menu-30.png)

Clicking on the "Trigger Build" option will bring up this pop-up:

![Trigger Build](travis-trigger-build-30.png)

Here, you can trigger a build for any branch, with the master branch being the default.  Go ahead and trigger a build for your master branch.

You should be able to see on the Travis-Ci page for your repo that the branch build successfully, all the test cases pass, and that you end up "green on master".   (The color green indicates success, i.e. that all the tests passed.)

If not, try to determine what's wrong first by checking these things:
* When you type `mvn test` locally, do all the tests pass?
* Do you have a `.travis.yml` committed on the master branch?

If you aren't able to figure out what is wrong, seek out help from a mentor, TA, or instructor.

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

# Step 8: Next feature branch: `xxCreateForm`


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

# Step 9: Next feature branch: `xxCallAPI`

9. In this step, we'll make yet another branch where we do something useful
   with the information on the results page.  We'll make a call to an API
   that provides information in JSON format.

   In this step we'll only
   echo that JSON information on the page; it won't yet be in a format
   that is pleasing to an end user.   But we'll be able to see that we
   are making progress.

# Step 10: Next feature branch: `xxJavaObjects`

10. In this final step, we'll learn how to transform that JSON string into
    usable Java objects, and use those Java objects to put useful information
    on the page.

    There will be one final pull request at this stage, and we'll be done.n


# OLD STUFF




# Final Step: Submitting your work for grading

When you have a running web app, visit <{{page.gauchospace_url}}> and make a submission.

In the text area, enter something like this, substituting your repo name and your Heroku app name:

<div style="font-family:monospace;">
repo name: https://github.com/chrislee123/spring-boot-minimal-webapp<br>
on heroku: https://cs56-{{site.qxx}}-{{page.num}}-chrislee123.herokuapp.com<br>
</div>

Then, **and this is super important**, please make both of those URLs **clickable urls**.

The instructions for doing so are here: <https://ucsb-cs56.github.io/topics/gauchospace_clickable_urls/>


# Grading Rubric:

TBA



