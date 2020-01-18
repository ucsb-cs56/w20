---
desc: Spring Boot and Heroku Hello World
assigned: 2020-01-13 12:30
assigned2: 2020-01-14 11:00
due2: 2020-01-22 23:59
due: 2020-01-22 23:59
gauchospace_url: https://gauchospace.ucsb.edu/courses/mod/assign/view.php?id=3114866
github_org: ucsb-cs56-w20
layout: lab
num: lab02
ready: true
starter: https://github.com/ucsb-cs56-w20/STARTER_lab02

---

<div style="display:none" >
Look here for formatted version: http://ucsb-cs56.github.io/w20/lab/lab02
</div>

This is an **individual** lab on the topic of Java web apps on Heroku.

You may cooperate with one or more pair partners from your team to help in debugging and understanding the lab, but each person should complete the lab separately for themselves.

# Step 0: If you are working on your own machine

If you are working on CSIL, you can skip this step.

But if you are working on your own machine, you'll need to install a few things before proceeding.

Here are some commands to let you be familiar with Maven in 5 mins! <https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html>

* Make sure you have Java 11
   * Install instructions for Mac OS: <https://ucsb-cs56.github.io/topics/macos/>
   * For Windows, see:  <https://ucsb-cs56.github.io/topics/windows/>
   
* Install Maven.  Instructions are here: <https://maven.apache.org/index.html>
   * If you are a Mac user, try to install Maven using Homebrew. <https://ucsb-cs56.github.io/topics/macos/>
		  Type "brew install maven" in your Terminal. (Check if your mac have Homebrew installed firstly)
		  
* Install the Heroku CLI.  Instructions are here: <https://devcenter.heroku.com/articles/heroku-cli#download-and-install>


# Step 1: Understanding what we are trying to do

### What are we trying to accomplish again in this lab?

-   In this lab, we will <em>create a basic "Hello, World" type web app in Java"</em>
-   A web app is a piece of Java code that takes HTTP request messages as input, and responds with HTTP response objects as output.
-   Heroku is a platform where we can host a Java web app.

### Why use Heroku?

-   Web applications run on the "server" side of the web architecture, not the client side.
-   So to test a web application, we need to set up a web server that can run Java code.
-   Configuring a web server for Java is challenging. But, fortunately, we don't have to.
-   Heroku.com offers "platform as a service" cloud computing for Java web applications.
-   We'll use the "free plan" that they offer for folks just getting started with learning Heroku.
-   This puts your application "on the web", for real, so that anyone in the world can access it 24/7



### Limitations of the free plan of Heroku

TL;DR: You should NOT need to enter a credit card into Heroku.  If you are asked for one, something has gone wrong.

-   If no-one has accessed your web app for a while, it "goes to sleep", so to speak.
    -   The first time someone tries to access it after it has gone to sleep, there is a <em>noticable</em> delay in the response, perhaps several seconds or even up to a minute.
-   If too many people try to access your service per hour, eventually, you'll run out of "free resources".
    -   That is <em>very unlikely to happen</em> unless you make a web app that somehow attracts the attention of a very large audience.
    -   I suggest you try to avoid doing that with the web apps you develop for this class.
    -   I suggest you avoid doing that in general, unless/until you have some plan for how to make money off your web app to pay for the server resources. (With a credit card, you can set up Heroku to have higher usage limits, and to keep your app running so that response time is fast. But you should NOT need that for this course.)


### Web Apps vs. Static Web Pages

You may already have some experience with creating static web pages, and/or with creating web applications (e.g. using PHP, Python (Django or Flask) or Ruby on Rails.) If so, then the "Learn More" section will be basic review.

If you are new to writing software for the web, you are <em>strongly encouaged</em> to read the background information at the "learn more" link below.
-   [Web Pages vs. Web Apps](https://ucsb-cs56.github.io/topics/webapps_webapps_vs_websites/)

### What are we trying to accomplish again in this lab?

If you just did a deep dive into the article [Web Pages vs. Web Apps](https://ucsb-cs56.github.io/topics/webapps_webapps_vs_websites/) it may be helpful to again review what we are trying to accomplish in this lab:

-   In this lab, we will <em>create a basic "Hello, World" type web app in Java"</em>
-   To test that, we need to run that on a server somewhere.
-   Configuring a web server for Java is challenging. But, fortunately, we don't have to.
-   Heroku.com offers "platform as a service" cloud computing for Java web applications.
    -   We'll use the "free plan" that they offer for folks just getting started with learning Heroku.
    -   This puts your application "on the web", for real, so that anyone in the world can access it 24/7

### Disk Quota

IMPORTANT: if you are working on CSIL, and at some point things just "stop working":

-   You get odd error messages, especially "cannot write file", or "disk quota exceeded"
-   You cannot log in---it takes your user name and password on the machines in Phelps 3525 or CSIL, but then just logs you out immediately.

Then you probably have a disk quota problem.

-   The best way to troubleshoot this, if you cannot log in, is to ask someone else that CAN log in to allow you to use a terminal window on their screen.
    -   Use `ssh yourusername@csil.cs.ucsb.edu` to get into your account from their terminal session.
-   For troubleshooting tips, visit: [CSIL Disk Quota Troubleshooting](https://ucsb-cs56.github.io/topics/csil_disk_quota/)

# Step 2: Create a Heroku Account

If you do not already have a Heroku account, navigate to <https://www.heroku.com/> and click the "Sign up for Free" link.


You'll be asked for:

-   First Name
-   Last Name
-   Email (you may use any email address you like)
-   Company (you may leave this blank).
-   Preferred Development Language: We suggest you select "Java" if you are currently enrolled in CMPSC 56
    -   (Don't worry; this doesn't prevent you from using the account with other languages later.)


# Step 3: Create your repo


Create a new repo that is:
* is in the GitHub organization <tt>{{page.github_org}}</tt>
* has name <tt>{{page.num}}-<i>githubid</i></tt> where <tt><i>github</i></tt> is your github id
* is public 
* is initially empty, i.e. no README.md, no `.gitignore`, no license

Clone that repo somewhere and cd into it.

Then add this remote:

<tt>git remote add starter {{page.starter}}</tt>

Then do:

```
git pull starter master
git push origin master
```

# Step 4: Start your webapp on `localhost`

Assuming you are working on CSIL, you can use `mvn` to run Maven.

* If you are working on your own machine, you'll need to install Maven on your machine.
* We've collected [advice on how to do that here](https://ucsb-cs56-pconrad.github.io/topics/maven_installing/).

Use `mvn compile` and `mvn spring-boot:run` to try to run the code and get a web app running on `localhost`.

Note that in order to see this web app running, you'll need to be in a web browser on the same host that you are running your program on.  
* For example, if you are running on `csil-04.cs.ucsb.edu`, you'll need to be running your web browser on `csil-04.cs.ucsb.edu`.   
* If you are working in Phelps 3525 on `cstl-07.cs.ucsb.edu`, you'll need to be running your web browser on `cstl-07.cs.ucsb.edu`.


## About `localhost` and "Port Numbers"

The code in this repo is configured to start up a webserver on port 8080, running on `localhost`, which is a name for the machine on which the code is running.  

* If you are running the code on a Phelps 3526 or CSIL machine, either because you are sitting in Phelps 3526, or sitting in CSIL, or 
  even if it is because you ssh'd into a CSIL machine from your laptop, then `localhost` refers to that CSIL machine.
* The port number is a more specific "communications channel" on that machine.   You can find more information on port numbers
  at this short article, which you are encouraged to read if you are not already familiar with port numbers
  (or, for that matter, even if you are): <https://ucsb-cs56.github.io/topics/port_numbers/>
  
So the web address to acccess your server is: `http://localhost:8080`.

* Note: You should use `http` not `https` when running on `localhost`. Using `http` is the unsecure, unencrypted version.   
* It is possible to set up Spring Boot to run `https` (the secure, encrypted version), but it's complicated and typically
  unnecessary; Heroku sets up `https` for us automatically, so we really don't need to deal with those steps most of the time.

## What if I get `port already in use`

The error `port already in use` signifies that someone else on the same system (perhaps you, in another window?) is already using
the port you are trying to use.

In this case:
* If it's you, shut down the server in the other window.
* It it's not you, then you are probably ssh'd into CSIL, say `csil-14.cs.ucsb.edu`, and someone else is logged into the same machine,
  doing the same assignment as you.  Therefore, they already grabbed that port number.
* In that case, maybe:
  * Try a different machine, say `csil-15.cs.ucsb.edu` OR
  * Type `export PORT=8082` before running `mvn spring-boot:run`.  That should start your server on port 8082 instead of 8080.
  * In that case, you'll need to change `http://localhost:8080` to `http://localhost:8082` of course.
  
## How do I access `http://localhost:8080` on CSIL from my laptop?

Suppose you are running your Spring Boot application on `localhost:8080` on one of the CSIL 
machines.

You normally *will not* be able to access that application from any browser that is NOT directly
running on that CSIL machine.

If you are ssh'ing into CSIL on your laptop (e.g. using `ssh` in a terminal session, or using an app such as `PuTTY` or `MobaXTerm` on Windows) keep in mind that if you direct your browser (running on your laptop) to `localhost:8080`, that request *never leaves your laptop*.  It looks for a web app running *on your laptop*.

So, how to solve this?  There are two ways:

### (1) Use curl 

This is the least satisfying way, because you won't see a proper web page.  You'll only see a dump of the HTML content of the page.  But for a simple 'Hello World' app, this works fine.


The `curl` program is a command line web client (curl stands for "C" the "URL").  For example, this command should show you the output from the `/` route for your webapp.  Run this command at the shell prompt on the CSIL machine to which you are logged in:

```
curl http://localhost:8080
```

A better way, which allows you to see the full web page is to use SSH Tunneling, described next.

### (2) Use SSH Tunneling

In F19, CS56 student Darragh B offered this tip for when you are running your Spring Boot app on CSIL but your browser is on your laptop.    It involves setting up what's called an "SSH Tunnel".

Suppose you are running on port 8080 on host `csil-10.cs.ucsb.edu`

Then you'll type the command: 

```
ssh -L 12345:localhost:8080 username@csil-10.cs.ucsb.edu
```

* If you have Mac or Linux, this should "just work".   
* On Windows, you may need to install [Git For Windows](https://git-scm.com/download/win) and use the `Git Bash Shell` to do this.

What this does is make it so that when you navigate to `http://localhost:12345` on your local browser, it sends the web request and response through an "SSH Tunnel" to port `8080` on `csil-10.cs.ucsb.edu`

Running on `localhost` is fine, but it has some limitations.  That's our next task: to understand those limitations, and why we need a cloud computing platform.

# Step 5: Undertstanding `localhost` vs. Heroku

When running on `localhost`:
* The web app is only runnning as long as your program is executing. 
* As soon as you CTRL/C the program to interrupt it, the web app is no longer available.
* The web app is only available on the machine where you are running the program; not on the public internet.

Running on `localhost` is fine for testing and development.  But eventually we want to know how to deploy a web application so that anyone on the internet can access it.

To get the web app running on the public internet, we'll need to use a cloud-computing platform such as Heroku.
Heroku allows us to deploy web applications in Java rather easily. 

*A side note*: though we won't explore it in this course, Heroku also makes it easy to deploy webapps in *a variety of langauges*, including Python, Node (JavaScript), and Ruby just to name a few.   Many of the skills you'll learn in this course about Heroku will transfer to those other languages if you want to work with them in other courses such as CMPSC 48, CMPSC 189A/B, or personal projects.)

*A note about security*: Let's say up front that this is a risky thing to do.   You need to be very careful about security when deploying web applications to the public internet.  Fortunately, this particular application is rather simple and low-risk.   We'll discuss web security throughout the course.

# Step 6: Create a new Heroku App using the Heroku CLI

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

Notes:
* A reminder that this is an individual lab, 
  so you should complete it for yourself, i.e. there is only one github id in the name, not a pair of github ids.   
* Please do not literally put the letters <tt><i>githubid</i></tt> 
  in your app name; you are meant to substitute your own github id there.


# Step 7: Login to the Heroku Dashboard

Login to <https://dashboard.heroku.com/apps> and look for the <tt>create cs56-{{site.qxx}}-<i>githubid</i>-{{page.num}}</tt> app that you created.

You should find a place where you can connect your App to Github.  

Click on this, and select your repo to connect the Github Repo to Heroku.

Then, click on "deploy branch".


# What if it doesn't work?

If it doesn't work, try these things before asking a mentor, TA, or instructor for help.

1. Make sure you are logged into Heroku at CLI with `heroku login`.  If you exited your CSIL shell (logged out) and logged back in again, you have to login to Heroku again.  Then repeat the commands.
2. Try, try running `heroku apps`.  Make sure the `<appname>app-name-goes-here</appname>` element in the `heroku-maven-plugin` section of your `pom.xml` matches the name of your heroku app exactly.
3. If it does, try `heroku logs --app appname` (substitute the name of your app where you see `appname`).  You'll see the log output of that app on Heroku.   
   * You may find it helpful to open a second Terminal, login to CSIL and the Heroku CLI, and use `heroku logs --app appname --tail`, which keeps the log output running continously.
   * You can also see your logs in a web browser at: <https://dashboard.heroku.com/apps/app-name/logs> (note that you need to put your `app-name` in the URL instead of `app-name`.  
   * You can navigate to this from <https://dashboard.heroku.com/> by selecting your app, clicking on it,  selecting the `More` menu at upper right, and the selecting `Logs`.

# Step 8: Changing what is shown on the page

Go into the Java source code under `src` and locate the file `/src/main/java/hello/HelloController.java` 

In this file, locate the line of code that says:

```java
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
```

This method returns the contents of the home page (`"/"`) for the webapp.

Change that code to the following.  Be sure to replace `mygithubid` with your own github id:

```
String html = "<h1>Hello World!</h1>\n" +
    "<p>This web app is powered by \n" +
    "<a href='https://github.com/ucsb-cs56-w20/lab02-mygithubid'>this github repo</a></p>\n";
return html;
```


Then:
* use `mvn compile` to make sure your code still compiles
* (optional, but suggested in case you need to debug)
   * use `mvn spring-boot:run` to test locally, perhaps with `curl http://localhost:8080`
* Use git add, git commit, and git push to push your changes to github.
* Visit the Heroku Dashboard, and you should see that your changes are deploying to the web.

If it works, then the words "this github repo" should become clickable links.  
* The words "this github repo" should link to your github repo

Ok, so far, we haven't really done anything we couldn't have done with a static web page.  But we have gotten a working Java web app running on Heroku, so it's start we can build on.  


# Step 9: The test cases

You'll see that when you run "mvn test" that there are test cases, some of which are now failing.

The test cases are in these files:
* `src/test/java/hello/HelloControllerTest.java` (Unit Test)

Run the tests and see them fail.

Then modify them so that they pass.   Note that we are doing TDD "wrong" this time; to do it "the right way", 
we should have modified the tests first, and then modified the code so that the tests pass.   We'll pivot to this
style of working once we have a better grasp on all the moving parts here.

# Step 10: Publishing the javadoc

As in lab01, we want to publish the javadoc, and put links to the javadoc and the repo in your README.md

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

* (10 pts) Having a repo with the correct name in the correct organization
* (20 pts) Having a running web app at <tt>https://cs56-{{site.qxx}}-<i>githubid</i>-{{page.num}}.herokuapp.com</tt>
* (20 pts) Running web app has the correct "new" content as specified in Step 6
* (20 pts) Test cases are updated for new content, and they pass (Step 7)
* (10 pts) There is a post on Gauchospace that has the correct content
* (10 pts) The links on Gauchospace are clickable links (to make it easier to test your app)
* (10 pts) README has links to javadoc, and javadoc is accesssible on GitHub pages


