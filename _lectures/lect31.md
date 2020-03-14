---
num: Lecture 31
lecture_date: 2020-03-02
desc: "Spring Boot Databases"
ready: true
pdfurl:
---

We covered some basics of databases in Spring Boot including

* the role of `@Entity` classes
* the role of `@Repository` classes
   * Note not the same a git/GitHub repositories
* rows, columns, tables 
   
# Some detail

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


## Setting up a Spring Boot app to use an SQL database

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
   
