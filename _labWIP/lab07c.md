---
layout: lab
num: lab07c
labnum: lab07
ready: true
desc: "Spring Boot Skills Part 3"
assigned: 2019-11-14 17:00
due: 2019-11-20 23:59
github_org: "ucsb-cs56-f19"
org: "ucsb-cs56-f19"
gauchospace_url: "https://gauchospace.ucsb.edu/courses/mod/assign/view.php?id=TBD"
prev: lab07b
starter: https://github.com/ucsb-cs56-f19/STARTER-lab07
---

<div style="display:none" >
Look here for formatted version: http://ucsb-cs56.github.io/f19/lab/lab07c
Look here for formatted version: http://ucsb-cs56.github.io/f19/labWIP/lab07c
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

In this lab, we add the following learning goals:
* Setting up a Spring Boot app to use an SQL database
   * We are using Hibernate, JPA and JDBC as layers in between the SQL database and our application code.
* Understanding a few basic concepts of SQL databases (tables, rows, columns)
* Setting up a new `@Entity`, a Java class that represents one row in a database
* Setting up a new `@Repository`, a Java class that represents a database table
* Setting up a separate controller and view that corresponds to a database table
* Adding code to an `@ControllerAdvice` class that will run before each web request/response
  to add new users to an AppUsers table in our database
* Adding a User Settings page to our application
* Adding columns to our database to store User Settings



# Picking up from where [{{page.prev}}]({{page.prev}}) left off

Please return to your same repo:

* <tt>{{page.labnum}}-githubid</tt>.

We will work with this repo, and with the Heroku app you configured for {{lab.prev}}

# Step by step instructions


## Step 14: Setting up a Spring Boot app to use an SQL database

We are using Hibernate, JPA and JDBC as layers in between the SQL database and our application code.



## Step 15:  Understanding SQL databases (tables, rows, columns)

TODO

## Step 16:  Creating an  `@Entity`, for `AppUser`


Setting up a new `@Entity`, a Java class that represents one row in a database

## Step 17:  Creating a `@Repository` for users

## Step 18:  Creating a `UsersController`

## Step 19:  Add users to table

## Step 20:  Add a placeholder user settings page

## Step 21:  Add columns to db for User Settings

## Step 22:  Make User Settings work


# Final Steps

## Final Step 1: Check that your code is all on master and Heroku

* Have you pushed all changes to your last feature branch?
* Have you done a final pull request?
* Is that pull request green on Travis-CI?
* Have you accepted that pull request?
* Have you deployed your master branch to Heroku?
* Do all the parts of your application work? Can you login/logout, and access all pages?

## Final Step 2: Update your javadoc and jacoco report

To update your javadoc and jacoco report, do this:

```
mvn clean
mvn javadoc:javadoc
mvn javadoc:test-javadoc
mvn test
mvn jacoco:report
mvn site
mvn site:deploy
git add docs
git commit -m "xx - update javadoc and jacoco report"
git push origin master
```

## Final Step 3: Check your README.md

Check that your README.md has links to
* your GitHub pages webpage, and that the webpage is published.
* your app running on Heroku
* your Travis-CI page

## Final Step 4: Submit on Gauchospace

Then, finally visit <{{page.gauchospace_url}}> and make a submission.

In the text area, enter something like this, substituting your repo name and your Heroku app name:

<div style="font-family:monospace;">
repo name: https://github.com/chrislee123/spring-boot-minimal-webapp<br>
on heroku: https://cs56-{{site.qxx}}-{{page.num}}-chrislee123.herokuapp.com<br>
</div>

Then, **and this is super important**, please make both of those URLs **clickable urls**.

The instructions for doing so are here: <https://ucsb-cs56.github.io/topics/gauchospace_clickable_urls/>


# Grading Rubric:

TBA



