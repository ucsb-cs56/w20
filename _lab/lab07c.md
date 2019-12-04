---
layout: lab
num: lab07c
labnum: lab07
ready: true
desc: "Spring Boot Skills Part 3"
assigned: 2019-11-21 17:00
due: 2019-11-27 23:59
github_org: "ucsb-cs56-f19"
org: "ucsb-cs56-f19"
gauchospace_url: "https://gauchospace.ucsb.edu/courses/mod/assign/view.php?id=2837754&forceview=1"
prev: lab07b
starter: https://github.com/ucsb-cs56-f19/STARTER-lab07
---

<div style="display:none" >
Look here for formatted version: http://ucsb-cs56.github.io/f19/lab/lab07c
Look here for formatted version: http://ucsb-cs56.github.io/f19/labWIP/lab07c
</div>


<div style="background-color: #fed; border: 4px inset #c00; font-size: 120%; width:80%; margin-left:auto;margin-right:auto;text-align:center;" markdown="1">

Updates 
<div style="text-align:left;" markdown="1">

There are updates at:
* Step 15b (one extra line in `application.properties` needed to fix `HTTP Status 500 - Internal Server Error` on Heroku.
* Step 16f (one extra declaration, and a few import statements, so that the new `getUid` method compiles.)

</div>
</div>


This lab builds on your work from {{page.prev}}.

# What if I didn't finish {{page.prev}}

If you were not successful in completing {{page.prev}}, you should go
back and complete any unfinished steps from {{page.prev}} first.  You
will get partial credit for this lab simply for doing the unfinished
steps from {{page.prev}}, even if you missed the deadline for
{{page.prev}}

# Individual lab vs Project Track

* If you are on the **individual lab** track, the **deadline for this lab is Wednesday Nov 27, 23:59pm**.  
   * Late submissions will 
     be accepted only up until grading is complete; submissions received 
     after grading is complete will NOT be accepted   for regrades.
* If you are on the **project track**, you are still responsible for completing this lab, but you should probably **prioritize getting started with your project**.  
   * Work on this if/when you are stuck/blocked and don't know how to proceed on your project, and/or when you need to understand how to work with databases and users settings.     
   * For Project track folks, your deadline for this lab is Saturday 12/07, 23:59pm.    
   * Late submissions will be accepted only up until grading is complete; submissions received after grading is complete will NOT be accepted for regrades.

# Individual lab

This is an **individual** lab on the topic of Java web apps on Heroku.

You may cooperate with one or more pair partners from your team to help in debugging and understanding the lab, but each person should complete the lab separately for themselves.


# Goals

The big picture of this lab is to learn how to add a database that would support individual user settings in a
web app.  It also gives us a basis for adding database functionality to our webapp.

Goals:
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
    
### Step 15a: Adding profiles to our `pom.xml`

The first step is to add a section to our `pom.xml` called `<profiles>`.

Profiles in a `pom.xml` allow us to use the same `pom.xml` file for different contexts. 
* We will create one profile called `localhost` and another one called `heroku`
* When we run `mvn` we can specify one profile or the other with the `-P` flag, for example:
   * `mvn -P localhost spring-boot:run` runs with the localhost profile
   * `mvn -P heroku spring-boot:run` would run with the heroku profile
* We'll set up the `heroku` profile as the default profile so that when we deploy our app on Heroku, it does the right thing.
* When running locally, we'll henceforth need to use `mvn -P localhost spring-boot:run`

To prepare to add a profile, checkout a clean version of master, and then create a new branch (your initials instead of `xx` as usual):

```
git checkout master
git pull origin master
git checkout -b xxAddProfiles
```

Here is the `<profile>` section to add into `pom.xml`.   Look at it a bit before just blinding copying and pasting it into your `pom.xml` to understand what it is doing.

* You'll see that it creates two profiles, one called `localhost`, and one called `heroku`.  
* Each profile specifies a `<build>` section that indicates where `<resources>` can be found, i.e. a series of directories.   One of those is shared, i.e. `src/main/resources`.  The other is unique to each profile.
* Each profile also has separate dependencies. 
   * The `localhost` profile has `h2`, the in-memory database, as a dependency.
   * The `heroku` profile has `postgres`, the database used on heroku, as a dependency, along with `jdbc`, which is 
     a layer used by Java to connect to databases over the internet (**J**ava **D**ata**b**ase **C**onnectivity).
* Finally, the `<activeByDefault>` element signifies that `heroku` is the default profile.  This is necessary
  so that when we deploy to Heroku, this profile is chosen.

If you scroll all the way to the bottom of your current `pom.xml`, you should find this comment just before the end:

```
<!-- (28) <profiles/> -->
```

Copy this into your `pom.xml` as an entire element right after that comment, before the closing `</profile>` tag. 

```xml
  <profiles>
        <profile>
            <id>localhost</id>
            <build>
                <resources>
                    <resource>
                        <directory>src/main/resources</directory>
                    </resource>
                    <resource>
                        <directory>src/main/config/localhost</directory>
                    </resource>
                </resources>
            </build>
            <dependencies>
                <dependency>
                    <groupId>com.h2database</groupId>
                    <artifactId>h2</artifactId>
                    <scope>runtime</scope>
                 </dependency>
            </dependencies>
        </profile>
        <profile>
            <id>heroku</id>
            <activation>
                <activeByDefault>true</activeByDefault>
            </activation>
            <build>
                <resources>
                    <resource>
                        <directory>src/main/resources</directory>
                    </resource>
                    <resource>
                        <directory>src/main/config/heroku</directory>
                    </resource>
                </resources>

            </build>
            <dependencies>
                <dependency>
                    <groupId>org.postgresql</groupId>
                    <artifactId>postgresql</artifactId>
                </dependency>

                <dependency>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-jdbc</artifactId>
                </dependency>
            </dependencies>
        </profile>
    </profiles>
```

To check the syntax of your `pom.xml` you can run this command:

```
mvn validate
```

This will simply check whether the XML elements are formatted properly.  If there are errors, try to fix them on your own first, by looking at the error messages and the instructions.  If you are still stuck, ask for help.

Once you get a clean result from `mvn validate`, do a commit with a commit message that indicates you added separate profiles for localhost and heroku, and
push it your current feature branch (i.e. `xxAddProfiles`).

### Step 15b: Adding additional `properties` files

Part of what the profile step did was to allow us to set up separate `.properties` files for `localhost` vs. `heroku`.

Create these directories:
* `src/main/config/localhost`
* `src/main/config/heroku`

Note that these are the directories defined in the `<directory>` elements in the two profiles you just put in your `pom.xml`.

In the directory `src/main/config/localhost`, create a file called `localhost.properties` and put this in as its contents:

```
spring.jpa.hibernate.ddl-auto=create
```

In the directory `src/main/config/heroku`, create a file called `heroku.properties` and put this in as its contents:

```
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.datasource.driver-class-name = org.postgresql.Driver
spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults=false

spring.datasource.url=${JDBC_DATABASE_URL}
spring.datasource.username=${JDBC_DATABASE_USERNAME}
spring.datasource.password=${JDBC_DATABASE_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
```

Finally, put these values into your `src/main/resources/application.properties` at the end:

```
spring.output.ansi.enabled=DETECT
logging.level.org.hibernate.SQL=debug

spring.jpa.show-sql=true
spring.jpa.generate-ddl=true
```

<div style="background-color: #fed; border: 4px inset #c00; font-size: 120%; width:80%; margin-left:auto;margin-right:auto;text-align:center;" markdown="1">

An update

<div style="text-align:left;" markdown="1">

The first versions of the lab did not have the final line for the `application.properties` file, i.e. the line:

```
spring.jpa.generate-ddl=true
```

This doesn't really come into play until you try to deploy on Heroku.   If you have trouble with that, check and make sure that you put this line in your `application.properties` file, and the trying deploying again.

Apologies for the omission.

</div>
</div>



Add these files to a commit:

```
git add -p src/main/resources/application.properties
git add src/main/config/heroku/heroku.properties
git add src/main/config/localhost/localhost.properties
```

No need to test just yet, but do push this commit to your feature branch.

### Step 15c: Additional dependencies for working with database

To work with databases, we need a few more dependencies that will go in the main `<dependencies>` element of our
`pom.xml`.

Add these:

```xml
        <!-- jpa, crud repository -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

        <dependency>
            <groupId>org.javassist</groupId>
            <artifactId>javassist</artifactId>
            <version>3.25.0-GA</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/javax.xml.bind/jaxb-api -->
        <dependency>
            <groupId>javax.xml.bind</groupId>
            <artifactId>jaxb-api</artifactId>
            <version>2.3.1</version>
        </dependency>
```

Check the syntax of your `pom.xml` with `mvn validate.   When there are no errors, 
do a commit with a commit message that indicates you added dependencies needed for SQL databases,
and push the commit to your feature branch (i.e. `xxAddProfiles`) on the `origin` remote (i.e. on GitHub).

### Step 15d: Test that nothing is broken locally

As a sanity check, do the following steps before doing a pull request, and merging this branch into master:

* `mvn -P localhost spring-boot:run`
   * Just check that everything still seems to work as it did before
* `mvn test`
   * Do the test cases still pass?
   
If there are problems, try to fix them on your own first; ask for help if you need it.

If not, do a pull request, and merge this branch into master.

## Step 16:  Adding a table for Users

In this step, we'll:

* Set up a new `@Entity`, a Java class that represents one row in a database.
   * The row in the database will store the `uid` and the `login` name of each GitHub user that logs into our application.
* Set up a new `@Repository`, a Java class that represents one table in the database.

### Step 16a:  Create a feature branch `xxAppUsersTable`

When starting a new branch, you always should start with a clean version of master:

```
git checkout master
git pull origin master
git checkout -b xxAppUsersTable
```

From here on out, we'll just describe the steps above like this:
* Create a feature branch called `xxSomeNewFeature` off of master.

The idea that you should start with a clean branch of master will be understood to be a given.  You should also anticipate being asked about this on a future exam.

### Step 16b:  Create `AppUser.java`

Make a new directory called `/src/main/java/hello/entities`, and into this directory, you'll put the code
shown below, in a file called `AppUser.java`.

Note a few things about this code:

* It is in the package `hello.entities`
* It has three fields, `id`, `uid` and `login`.
* These three fields will be the fields in each row of a database table for users.
* The `@Id` and `@GeneratedValue(strategy = GenerationType.IDENTITY)` are used to make sure that each database
  row in the table gets a unique id value.  The framework requires us to use a `long` to represent this value.
* The other fields are the ones that we want to store in this database object.  We are storing `uid`, which is a
  permanent "user id" string assigned to each GitHub user, and `login` which the actual login name that users are
  used to seeing.  User are allowed to change their login name, but their `uid` stays the same permanently.
* We need to follow the Java Bean standard and have standard getters and setters for the fields.

```java
package hello.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AppUser {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String uid;
    private String login;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
   
    public String getUid() { return uid; }
    public void setUid (String uid) { this.uid = uid; }
}
```

Do `mvn clean compile` to ensure that this code compiles.  If so, do a commit.   Add an appropriate commit message,
and push the commit to your current feature branch.  You can use `git status` to see what the current feature branch is.


### Step 16c:  Creating a `@Repository` for users

In this step, we'll set up a new `@Repository`, a Java class that represents one table in the database.

The table we are setting up is one that stores all of the `AppUser` instances that we want to keep track of.
We'll call this class `UserRepository`, and put it in a new directory `/src/main/java/hello/repositories` in 
a file called `UserRepository.java`.

The contents of `UserRepository.java` should be as follows:

```java
package hello.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import hello.entities.AppUser;

@Repository
public interface UserRepository extends CrudRepository<AppUser, Long> {
   List<AppUser> findByUid(String uid);
}
```

Do `mvn clean compile` to ensure that this code compiles.  If so, do a commit.   Add an appropriate commit message,
and push the commit to your current feature branch.  



### Step 16d:  Creating a `UsersController`

It is a common practice to have a separate controller class for each database entity.  So in this step, we'll create a file called `UsersController.java` and put it into a new directory called `/src/main/java/hello/controllers`. 

Here's the code that goes into `UsersController.java`.   Take a look and notice a few things:

* We have 

```java
package hello.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import hello.entities.AppUser;
import hello.repositories.UserRepository;

@Controller
public class UsersController {

    private UserRepository userRepository;

    @Autowired
    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;   
    }
    
    @GetMapping("/users")
    public String index(Model model) {
        Iterable<AppUser> users= userRepository.findAll();
        model.addAttribute("users", users);
        return "users/index";
    }

}
```

### Step 16e:  Creating a view for the Users

We now need a view to go along with our `/users` endpoint.  
* The endpoint is marked with `@GetMapping("/users")` 
* That endpoint is served up by the `index` method of `UsersController`
* The return value of that method is `"users/index"`
* That implies there should be a file called `src/main/resources/templates/users/index.html`

Take a look at that again, and understand the pattern:

| If the return value of the endpoint is: | We are looking for this file under `/src/main/resources/templates` |
|--|--|
| `"foo"` | `foo.html` |
| `"bar/fum"` | `/bar/fum.html` |

So we need to create a new directory (folder) under `/src/main/resources/templates/` called  `users` and put 
a file in there called `index.html`.

That file should look similar to all of our other full web pages.   We can start with our `index.html` from the
root of our application, make a copy of that, and then modify it.

Here's what the `index.html` in `/src/main/resources/templates` should look like:

```html
<!DOCTYPE HTML>
<html xmlns:th="http://www.thymeleaf.org" lang="en"> 
  <head>
    <title>CS56 Spring Boot Practice App</title>
    <div th:replace="bootstrap/bootstrap_head.html"></div>
  </head>
  <body>
    <div class="container">
      <div th:replace="bootstrap/bootstrap_nav_header.html"></div>

      <h1>Home Page</h1>

      <p>This page is a placeholder.</p>

      <div th:replace="bootstrap/bootstrap_footer.html"></div>
    </div>
    <div th:replace="bootstrap/bootstrap_scripts.html"></div>
  </body>
</html>

```

We'll modify the part of the page that changes, which is the part inside the `<div class="container">` element between
the bootstrap header and bootstrap footer.

Here's the code we'll put in there, replacing this:

```html
 <h1>Home Page</h1>

 <p>This page is a placeholder.</p>
```

with this. 

```html 
  <h3>Users</h3>
      <table class="table">
          <thead>
              <tr>
                  <th>id</th>
                  <th>uid</th>
                  <th>login</th>
              </tr>
          </thead>
          <tbody>
              <tr th:each="u: ${users}">
                <td th:text="${u.id}"></td>
                <td th:text="${u.uid}"></td>
                <td th:text="${u.login}"></td>
              </tr>
          </tbody>
      </table>
```

 Look over the HTML code above and see how it works.  There is an HTML table element, with headers for `id`, `uid` and `login`.   The `<tr>` element has an Thymeleaf attribute `th:each="u: ${users}"` which causes the `<tr>` element to be repeated
for each instance in the `users` variable.  For this to work, `users` in the `Model` object has to be some kind of `Iterable<>`.   The code that makes this work is two lines of code in the `UsersController.java` that read:

```java
        Iterable<AppUser> users= userRepository.findAll();
        model.addAttribute("users", users);
```        

* The `users = userRepository.findAll()` is the Java equivalent of an SQL statement such as `SELECT * from app_users;`
* The `model.addAttribute("users", users);` puts that value into the `model`; that makes it available in the view (the Thymeleaf HTML template files.)

We can't really test this very effectively yet with no users in our table.  But we can at least make sure that the 
code doesn't crash.  

So fire up the app with the following (the `-P localhost` is super important!)

```
mvn -P localhost spring-boot:run
```

Then navigate to the `/users` endpoint.  You should see an empty table of users (not an error message).

If you see an error, look at the console output closely and try to locate the actual root cause of the error, and fix it.  If you need help, ask for it.

If you do get a nice clean empty table, then you are ready to do a commit on the code you've put in so far for the
view (`src/main/resources/templates/users/index.html`).  

### Step 16f:  Add users to table

In this step, we need to add some code that puts users into the `UserRepository` (i.e. stores them in the SQL database table for users.)

We are going to add that code into a file called `src/main/java/hello/AuthControllerAdvice.java`

First let's add this private method to the very end of the class:

```java
    private String token2login(OAuth2AuthenticationToken token) {
        return token.getPrincipal().getAttributes().get("login").toString();
    }
```

Look at the return value of this method. This method takes an OAuth token (returned by GitHub), uses it to look
up the `Principal`, which is a Java abstraction for a user.   It then gets an attribute called `login`, which is the
users GitHub login name (e.g. `pconrad`, `cgaucho`, etc.)

We are going to use this to keep our code DRY.  Find this method, which loads that same value into an attribute
in the model called `login`.  This is what the file `src/main/resources/templates/bootstrap/bootstrap_nav_header.html` uses to put the login name at the top of the page after you login.

```
    @ModelAttribute("login")
    public String getLogin(OAuth2AuthenticationToken token){
        if (token == null) return "";
        return token.getPrincipal().getAttributes().get("login").toString();
    }
```

You'll see that the return value of `getLogin` is the same as that of `token2login`.  So let's use that
method call; you should replace the return value from `getLogin` with a call to `token2login`:

```
       return token2login(token);
```

Now we are going to use that method in another context as well. Find the method `getUid`.  It should look like this:

```java
    @ModelAttribute("id")
    public String getUid(OAuth2AuthenticationToken token){
        if (token == null) return "";
        return token.getPrincipal().getAttributes().get("id").toString();
    }  
```

<div style="background-color: #fed; border: 4px inset #c00; font-size: 120%; width:80%; margin-left:auto;margin-right:auto;text-align:center;" markdown="1">

A few updates

<div style="text-align:left;" markdown="1">

In addition to the code below, if your code is/was not working at this step, it may be because these lines were missing in the first version of the lab.   These updates were made about 8pm on Thursday, Nov 21.

(1) Just after the opening class line:

```
public class AuthControllerAdvice {
```

Add this declaration:

```java
    @Autowired   
    private UserRepository userRepository;

```


(2) You'll need some additional imports:

```
import hello.repositories.UserRepository;
import hello.entities.AppUser;
import java.util.List;
```

I apologize for the oversight.

</div>
</div>


We are going to put code into this method that will check, every time we look up a users GitHub `id`, we'll check to 
see if that user was *not* found in our `userRepository` (i.e. if the size of `List<AppUser>` returned was 0.)  
If it was indeed not found, we'll
add this user to the `userRepository` (which adds it to the database).  

Here's the new version.  Look over this code, and replace the code for the `getUid` method:

```java
 @ModelAttribute("id")
    public String getUid(OAuth2AuthenticationToken token){
        if (token == null) return "";

        String uid = token.getPrincipal().getAttributes().get("id").toString();

        List<AppUser> users = userRepository.findByUid(uid);

        if (users.size()==0) {
            AppUser u = new AppUser();
            u.setUid(uid);
            u.setLogin(token2login(token));
            userRepository.save(u);
        }

        return uid;
    }
```

With this code in place, you should be able to run on localhost with:

```
mvn -P localhost 
```

See whether, if you logout and login, you can go to the `/users` endpoint and see your user 

### Step 16g:  Exploratory Testing

Make sure that the app still runs on localhost and that all of the other features work.  This is called "exploratory testing".  It isn't foolproof; it's tedious and easy to miss thing that were accidentally broken, or stopped working.

That's why a better approach is automated unit and integration testing.   We haven't been doing it as we've been developing, which is not ideal.    But, some tests are better than none, so go ahead and run:

```
mvn test
``` 

And ensure that the tests we *do* have still run ok.

### Step 16g: Push, PR

Assuming that all went well in the previous step, push your feature branch to the `origin`, do a pull request,
and merge the pull request into master.

## Step 17: Add a navigation bar item for Users

For this step, I will not give you specific instructions.  You should be able to use the instructions from
previous steps of lab07a, lab07b, and lab07c to figure out how to do items listed below.

You should create a feature branch with an appropriate name, and use appropriate commit messages.

* Add a navigation bar link called `Users` that goes to the `/users` endpoint.
* Add a test that the navigation bar has a link called `Users` that links to the `/users` endpoint.

Do a pull request when your tests pass, and when the feature works properly, and merge the request to master.


## Step 18: Deploying on Heroku

Before deploying the master branch anew on Heroku, you'll need to do one more step.

### Step 18a: Provision an Heroku Postgres database addon

1. Go to the Heroku dashboard for your app, at <https://dashboard.heroku.com/apps/cs56-f19-lab07-githubid> where
`githubid` is your github id.
2. There should be a "Resources" tab. Click on it.
3. Find the AddOns box where can type in the name of add-ons to search for them.  Type in `Heroku Postgres`
4. When it comes up, click on it.  You want to add the "Hobby/Dev" free tier.

That's it.  If it worked, you should be able to to the "Settings" tab, click on "Reveal Config Vars" and see a new variable called `DATABASE_URL`.  If you see that, then you have a database provisioned and ready for use.

The `DATABASE_URL` contains the hostname, port, username, password, and database name needed to connect to your database.  When Heroku sees a Java application, it automatically parses these into environment variables with the names
we already specified in our `heroku.properties` file earlier, i.e.:

```
spring.datasource.url=${JDBC_DATABASE_URL}
spring.datasource.username=${JDBC_DATABASE_USERNAME}
spring.datasource.password=${JDBC_DATABASE_PASSWORD}
```

This is how our spring boot app  can connect to the database when running on Heroku.

We are now ready to deploy again.

### Step 18b: Deploy master branch

In the "Deploy" tab of the Heroku dashboard, find the "Deploy Branch" button, and deploy from the master branch.

### Step 18c: Exploratory testing of the Heroku version

Now, try the application on Heroku.  Get another student, a tutor, TA or instructor to try logging into your app so that you can see if the users table works properly.

If your application loads, but you get a `500 Application Error` when you try to do a login, there are two possible causes:
* Make sure you provisioned your `Heroku Postgres` database as a Heroku Add on (see step 18a).
   * You can tell if you did it by looking under `Settings`, `Reveal Config Vars` for `DATABASE_URL`.  If it's there, you are good.
* Make sure that you added this extra line into the `application.properties` (Step 15b).  This line was not in the first version of the lab; we caught the error about 8:30pm Friday 11/22.
   ```
   spring.jpa.generate-ddl=true
   ```
   * If that line isn't there, then Spring Boot doesn't automatically create the database tables the first time the
     application runs.

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

| Points | Step |
|-|-|
| 30 | Github login works | 
| 30 | After github login, the Users menu shows that the user has logged in (i.e. their record is in the database) |
| 20 | /src/main/java/hello has controllers and entities subdirectories | 
| 20 | README.md has a link to Travis-CI, and repo is green on CI (no points if no link) |

