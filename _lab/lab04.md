---
desc: "Ratcalc: Web App Front End for Rational Calculator"
assigned: 2020-01-28 17:00
assigned2: 2020-01-29 17:00
due2: 2020-02-07 23:59
due: 2020-02-07 23:59
gauchospace_url: https://gauchospace.ucsb.edu/courses/mod/assign/view.php?id=3163146
github_org: ucsb-cs56-w20
layout: lab
num: lab04
rational_lab: lab01
ready: true
starter: https://github.com/ucsb-cs56-w20/STARTER_lab04
---

<div style="display:none" >
Look here for formatted version: http://ucsb-cs56.github.io/w20/lab/lab04
</div>

This is an **individual** lab on the topic of Java web apps on Heroku.

You may cooperate with one or more pair partners from your team to help in debugging and understanding the lab, but each person should complete the lab separately for themselves.

# Step 0: If you are working on your own machine

If you are working on CSIL, you can skip this step. 

Otherwise, revisit step 0 of lab02, and install the software mentioned there.

# Step 1: Understanding what we are trying to do

### What are we trying to accomplish again in this lab?

-   In this lab, we will <em>create a web app interface</em> on top of the 
    `Rational` class that we created in a previous lab, {{page.rational_lab}}.

# Step 2: Create your repo


Create a new repo that is:
* is in the GitHub organization <tt>{{page.github_org}}</tt>
* has name <tt>{{page.num}}-<i>githubid</i></tt> where <tt><i>github</i></tt> is your github id
* is <s>public</s> **private** (Sorry, this was a typo!)
* is initially empty, i.e. no README.md, no `.gitignore`, no license

Clone that repo somewhere and cd into it.

Then add this remote:

<tt>git remote add starter {{page.starter}}</tt>

Then do:

```
git pull starter master
git push origin master
```

# Step 3: Start your webapp on `localhost`

Assuming you are working on CSIL, you can use `mvn` to run Maven.

* If you are working on your own machine, you'll need to install Maven on your machine.
* We've collected [advice on how to do that here](https://ucsb-cs56-pconrad.github.io/topics/maven_installing/).

Use `mvn compile` and `mvn spring-boot:run` to try to run the code and get a web app running on `localhost`.

Note that in order to see this web app running, you'll need to be in a web browser on the same host that you are running your program on.  
* For example, if you are running on `csil-04.cs.ucsb.edu`, you'll need to be running your web browser on `csil-04.cs.ucsb.edu`.   
* If you are working in Phelps 3525 on `cstl-07.cs.ucsb.edu`, you'll need to be running your web browser on `cstl-07.cs.ucsb.edu`.

This webapp should now be running on  `http://localhost:8080`

## How do I access `http://localhost:8080` on CSIL from my laptop?

See the instructions in lab02.

# Step 4: Create a new Heroku App using the Heroku CLI

In this step, we'll deploy our Spring Boot application to the public internet using Heroku.

Logged into CSIL (or one of the machines in the CSTL, i.e. Phelps 3525), use this command to login to Heroku at the command line:

```
heroku login
```

**NOTES**: 

* If you are ssh'ing in to CSIL, you may need to use `heroku login -i` which allows you to login without having to go to a browser.

* If the `heroku login` command doesn't work, you can instead create the Heroku App at the Heroku Dashboard by
  visiting <https://dashboard.heroku.com/apps>, clicking (at upper right):  "New&nbsp;=>&nbsp;Create New App" and
  then creating an app with the name <tt>heroku create cs56-{{site.qxx}}-<i>githubid</i>-{{page.num}}</tt> as explained in 
  the instructions below.

Then, use this command to create a new web app running on heroku.  Substitute your github id in place of `githubid`.  
Note that you should convert your githubid to all lowercase; heroku web-app names do not permit uppercase letters.

<tt>heroku create cs56-{{site.qxx}}-<i>githubid</i>-{{page.num}}</tt>

If that name is too long, then abbreviate in whatever way you see fit.

Notes:
* A reminder that this is an individual lab, 
  so you should complete it for yourself, i.e. there is only one github id in the name, not a pair of github ids.   
* Please do not literally put the letters <tt><i>githubid</i></tt> 
  in your app name; you are meant to substitute your own github id there.

# Step 5: Login to the Heroku Dashboard

Login to <https://dashboard.heroku.com/apps> and look for the <tt>create cs56-{{site.qxx}}-<i>githubid</i>-{{page.num}}</tt> app that you created.

You should find a place where you can connect your App to Github.  

Click on this, and select your repo to connect the Github Repo to Heroku.

Then, click on "deploy branch".

You should then see your app running on Heroku.

# What if it doesn't work?

See the troubleshooting steps in lab02.

# Step 6: Plug in your files from  {{page.rational_lab}}

In a previous lab, {{page.rational_lab}}, you worked on two files:  
* `Rational.java`
* `RationalTest.java`

In your current directory, i.e. the repo you created for {{page.num}}, 
you'll find these files, which are just placeholders:
* `src/main/java/edu/ucsb/cs56/ratcalc/model/Rational.java`
* `src/test/java/edu/ucsb/cs56/ratcalc/model/RationalRest.java`

You should copy the files `Rational.java` and `RationalTest.java` from {{page.rational_lab}} 
on top of the corresponding files in your current directory.   Then, add this line to the top
of both of those files to declare the correct package:

```java
package edu.ucsb.cs56.ratcalc.model;
```

At this point, you should be able to run `mvn test` and see that the test cases are passing.

You should also be able to enter fractions into the `Add` function of the webapp and see
correct results.

Now the real programming starts.  All your programming will be in the file
`OperationsController.java`.

# Step 7: Fix the `/subtract/results` page.

In the file  `OperationsController.java`, you'll see this annotation:

```java
@GetMapping("/subtract/results")
```

This signifies the code that is run when you hit the `Subtract` button on the `/subtract` page 
of the webapp.  The button takes you to the `/subtract/results` page, and this code is run
to produce that page.  Some code is missing.

Compare the code that is under this annotation, which is correct code:

```java
@GetMapping("/add/results")
```

You'll see code similar to what you need for your `getSubtractResult` method.  

Using this code as a guide, fill in the code needed for the `getSubtractResult` method.

You'll know you are done when you can enter subtraction operations and get the correct results.

You can check your answers in two ways:
* using this tool: <https://www.calculator.net/fraction-calculator.html>
* with the instructors version of the working app: <http://cs56-w20-lab04-pconrad.herokuapp.com/>

When this is working do a commit with your initials (e.g. cg for Chris Gaucho) and a commit message 
such as this one:

```
git commit -m "cg - fixed controller for subtract results"
```

# Step 8: Fix the `/multiply/results` page.

If you try the multiply page, and click on the Multiply button, you'll get the "White Label Error Page",
a page that Spring Boot throws up when it doesn't know what else to do.

The reason is that there is no controller method for the route:

```java
@GetMapping("/multiply/results")
```

So, looking at the methods for `@GetMapping("/add/results")` and `@GetMapping("/subtract/results")`
infer what the code should be for `@GetMapping("/multiply/results")` and add this code to the 
file  `OperationsController.java`.

You'll know it's working when you can multiply fractions on the `/multiply` page.

You can check your answers in two ways:
* using this tool: <https://www.calculator.net/fraction-calculator.html>
* with the instructors version of the working app: <http://cs56-w20-lab04-pconrad.herokuapp.com/>

When this is working, do a separate commit for it. 

```
git commit -m "cg - added controller method for multiplication results"
```

# Step 9: Fix the `/divide` and `/divide/results` pages

In the file  `OperationsController.java`, you'll see that these two annotations do not appear:

* `@GetMapping("/divide")`
* `@GetMapping("/divide/results")`

Accordingly, if you click on the `Divide` menu item, you get no form in which you can enter a division problem.

Get that working first.  Use the methods annotated with `@GetMapping("/add")`, `@GetMapping("/subtract")` and
`@GetMapping("/multiply")` as guides.

Once that's working, i.e. when you can get to the divide form, but the divide button still doesn't work, 
do a commit:

```
git commit -m "cg - added controller method to show divide form "
```

Now, using the following controller methods a a guide, write a controller method
for the `/divide/results` route:

* `@GetMapping("/add/results")`
* `@GetMapping("/subtract/results")`
* `@GetMapping("/multiply/results")`

Note that division has one extra opportunity for errors, the possibility of division by zero.  
Look throughout the file for helper methods that you might need to call to account for that
possibility.

You'll know you are done when you can enter division operations and get the correct results, 
including getting suitable error messages for dividing by zero.

You can check your answers in two ways:
* using this tool: <https://www.calculator.net/fraction-calculator.html>
* with the instructors version of the working app: <http://cs56-w20-lab04-pconrad.herokuapp.com/>


# Step 10: Publishing the javadoc

As usual we want to publish the javadoc, and put links to the javadoc and the repo in your README.md

To generate the javadoc and publish it to github pages, take these steps:

1. `mvn javadoc:javadoc` to generate the regular javadoc
2. `mvn javadoc:test-javadoc` to generate javadoc for the test classes
3. `mvn site` to generate a web page for your project
4. `mvn site:deploy` to copy that website to the `/docs` folder of your repo
5. `git status` to verify see that the `/docs` folder now exists.
6. `git add docs`
7. `git commit -m "xx - add javadoc"`  where `xx` are your initials 
8. `git push origin master`
9.  Then, go to the `Settings` link for your repo, and turn on GitHub pages for the `docs` folder of the master branch.

Finally, check the URL shown in the settings.  It can take 3-5 minutes before it shows up.

Note:
* If you are using Windows Subsystem Linux (WSL), an error `The environment variable JAVA_HOME is not correctly set.` may appear. If this does occur a temporary fix is this: 
`export JAVA_HOME=/usr/lib/jvm/java-1.11.0-openjdk-amd64` 
replace `java-1.11.0-openjdk-amd64` with whatever version of Java you have installed if needed.

Look at the list of commits on the repo page on github.com, and you should see either a green check, a yellow circle, or a red X indicating the status of the commit.  These markers show the status of GitHub pages.   Later in the quarter, when we enable automatic testing (via a service called Travis-CI), these markers will show the status of our test cases as well.

# Step 11: Adding links to javadoc and repo in the README.md

Edit your README.md.  You'll find some TODO items inside indicating what edits you need to make.

All quarter long, we want you to develop the habit of adding these links in your README.md:
* A link to your javadoc
* A link to your repo

The link to your repo may seem redundant, but it helps your mentors, TAs and instructors; when you submit your work for grading to either Gradescope or Gauchospace, having those links handy really helps us navigate through your assignments quickly to evaluate them and assign grades.

# Step 12: Submitting your work for grading

When you have a running web app, visit <{{page.gauchospace_url}}> and make a submission.

In the text area, enter something like this, substituting your repo name and your Heroku app name:

<div style="font-family:monospace;">
repo name: https://github.com/chrislee123/spring-boot-minimal-webapp<br>
on heroku: https://cs56-{{site.qxx}}-chrislee123-{{page.num}}.herokuapp.com<br>
</div>

Then, **and this is super important**, please make both of those URLs **clickable urls**.

The instructions for doing so are here: <https://ucsb-cs56.github.io/topics/gauchospace_clickable_urls/>


# Grading Rubric:

* (5 pts) Having a repo with the correct name in the correct organization
* (5 pts) Having a running web app at <tt>https://cs56-{{site.qxx}}-<i>githubid</i>-{{page.num}}.herokuapp.com</tt>
* (5 pts) There is a post on Gauchospace that has the correct content
* (5 pts) The links on Gauchospace are clickable links (to make it easier to test your app)
* (5 pts) README has links to javadoc, and javadoc is accesssible on GitHub pages
* (75 pts) Correctness of code for subtract, multiply, and divide controllers (details TBA)

