---
layout: lab
num: lab06
ready: false
desc: "Spring Boot OAuth Hello World"
assigned: 2019-10-31 17:00
due: 2019-11-05 23:59
github_org: "ucsb-cs56-f19"
gauchospace_url: "https://gauchospace.ucsb.edu/courses/mod/assign/view.php?id=TBD"
starter: "https://github.com/ucsb-cs56-f19/STARTER-lab06"
prev: lab02
---

<div style="display:none" >
Look here for formatted version: http://ucsb-cs56.github.io/f19/lab/lab06
</div>

This is an **individual** lab on the topic of Java web apps on Heroku.

You may cooperate with one or more pair partners from your team to help in debugging and understanding the lab, but each person should complete the lab separately for themselves.


### What are we trying to accomplish again in this lab?

-   In this lab, we will <em>create a basic Spring Boot app that uses GitHub OAuth</em>
-   We will get that running first on localhost then on Heroku

See {{page.prev}} for information about Heroku.

If you are working on your own machine, revisit {{page.prev}} for software that you will need on your machine (Maven and the Heroku CLI).


# Step 1: Create an empty PUBLIC repo called `lab06-githubid`

Create an empty PUBLIC repo called `lab06-githubid` UNDER the organization <tt>{{page.github_org}}</tt>.
* Initially, no README.md, no `.gitignore`, no license

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





