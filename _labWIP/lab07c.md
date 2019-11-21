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
* Understanding a few basic concepts of SQL databases (tables, rows, columns)
* Setting up a Spring Boot app to use an SQL database
   * We are using Hibernate, JPA and JDBC as layers in between the SQL database and our application code.
   * We are using H2, a temporary "in-memory" database when running on `localhost`.   The data in this
     temporary in-memory database is lost each time we shut down and restart the application.
   * We are using `postgres`, a real SQL database when running on Heroku.  That database will
     "persist" the data (meaning the data stays there even if the application is shut down.)
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


## Step 14:  Understanding SQL databases (tables, rows, columns)

It's helpful to have at least a little understanding of how SQL databases work.

If you are familiar with, and comfortable with the following concepts, that's enough for what we are doing:
* database tables
* rows in a database
* columns in a database
* fields (which are the names of the columns) in a database row.

If you are unfamiliar with these concepts, I recommend looking over the following *four pages* from the w3schools website. 

* [SQL Intro](https://www.w3schools.com/sql/sql_intro.asp)
* [SQL Syntax](https://www.w3schools.com/sql/sql_syntax.asp)
* [SQL SELECT](https://www.w3schools.com/sql/sql_select.asp)
* [SQL INSERT INTO](https://www.w3schools.com/sql/sql_insert.asp)

While the tutorial goes
on at much more length, you will likely not need more than these pages of it for the work we are
doing with SQL databases in this lab.   You just need to know what we mean when we say *table*, *row*, *field*, and *column*.

In fact, arguably, these few pages, which should take no more than
about 5-10 minutes to look over, have more information about SQL that you'll likely need, since Spring Boot
takes care of most of the low level details of SQL databases for us.  


## Step 15: Setting up a Spring Boot app to use an SQL database

We are using Hibernate, JPA and JDBC as layers in between the SQL database and our application code.

There is so much to learn about these three technologies, and we'll only be able to barely scratch 
the surface.  In many cases, it won't be clear, for any particular part of what we are doing, whether its
Hibernate, JPA, or JDBC is that involved.   That may make you wonder why I'm even mentioning the words.

The reason is that if/when something goes wrong, and you are debugging, it's helpful to know what keywords
to include in your web searches, and/or what keywords to look for in the results.    Even if you don't know
exactly what Hibernate, JPA and JDBC are, at least knowing that you are using them is a good start.

### Two different database technologies

For the actual database, we'll be using two different technologies:

* When running on `localhost`, we are using *H2*
  * H2 is a temporary "in-memory" database.
  * The chief advantage of H2 is that it is completely built into Spring Boot, and requires no additional software.
  * Therefore, it should run anywhere that Spring Boot runs, including on CSIL.
  * The chief disadvantage is that it is an "in memory" temporary database.  The data stored in the database
    exists *only* as long as the spring boot application is running.  If you shut down the application, all of the
    data stored in the database is lost.
  * *Obviously* that's no good for a real applicaiton.  But it's fine for testing purposes.
  * Setting up a real database on localhost is possible, but tricky, especially given that it works differently
    on Mac, Windows, Linux (different for each variety!), and CSIL.   Thus avoiding that is great!
* When running on Heroku, we'll use *Postgres*
  * Postgres is a "real" SQL database system.  When you store data in postgres, it stays there until you delete it,
    even if the application is shut down.
  * Heroku offers Postgres as a free add on in the "Hobby/Dev" tier.   That has enough data storage capacity for our needs.   
  * If we exceeded the data storage capacity of the free tier, then we'd have to pay for it, but that won't happen
    in this lab assignment.
    
## Step 15a: Adding profiles to our `pom.xml`

The first step is to add a section to our `pom.xml` called `<profiles>`.

Profiles in a `pom.xml` allow us to use the same `pom.xml` file for different contexts. 
* We will create one profile called `localhost` and another one called `heroku`
* When we run `mvn` we can specify one profile or the other with the `-P` flag, for example:
   * `mvn -P localhost spring-boot:run` runs with the localhost profile
   * `mvn -P heroku spring-boot:run` would run with the heroku profile
* We'll set up the `heroku` profile as the default profile so that when we deploy our app on Heroku, it does the right thing.
* When running locally, we'll henceforth need to use `mvn -P localhost spring-boot:run`

Here is the `<profile>` section to add.

```xml
```

    

## Step 16:  Creating an  `@Entity`, for `AppUser`

In this step, we'll set up a new `@Entity`, a Java class that represents one row in a database.

The row in the database will store the `uid` and the `login` name of each GitHub user that logs into our application. 

## Step 17:  Creating a `@Repository` for users

In this step, we'll set up a new `@Repository`, a Java class that represents one table in the database.

The table we are setting up is one that stores all of the `AppUser` instances that we want to keep track of.
We'll call this class `UserRepository`, and put it in a new directory `/src/main/java/hello/repositories` in 
a file called `UserRepository.java`.

## Step 18:  Creating a `UsersController`

It is a common practice to have a separate controller class for each database entity.  So in this step, we'll create a file called `UsersController.java` and put it into a new directory called `/src/main/java/hello/controllers`. 


## Step 19:  Add users to table

In this step, we need to add some code that puts users into the `UserRepository` (i.e. stores them in the SQL database table for users.)

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



