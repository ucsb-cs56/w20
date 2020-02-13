---
layout: lab
num: lab06
ready: true
desc: "Spring Boot OAuth Hello World"
assigned2: 2020-02-04 17:00
assigned: 2020-02-05 12:30
due: 2020-02-13 23:59
due2: 2020-02-13 23:59
github_org: "ucsb-cs56-w20"
gauchospace_url: "https://gauchospace.ucsb.edu/courses/mod/assign/view.php?id=3183791"
starter: "https://github.com/ucsb-cs56-w20/STARTER_lab06"
prev: lab02
---


<div style="display:none" >
Look here for formatted version: http://ucsb-cs56.github.io/w20/lab/lab06
</div>

This is an **individual** lab on the topic of Java web apps on Heroku.

You may cooperate with one or more pair partners from your team to help in debugging and understanding the lab, but each person should complete the lab separately for themselves.


### What are we trying to accomplish again in this lab?

-   In this lab, we will <em>create a basic Spring Boot app that uses GitHub OAuth</em>
-   We will get that running first on localhost then on Heroku

See {{page.prev}} for information about Heroku.

If you are working on your own machine, revisit {{page.prev}} for software that you will need on your machine (Maven and the Heroku CLI).


# Step 1: Create your repo

Create a new repo
* UNDER the organization <tt>{{page.github_org}}</tt>
* call it `lab06-githubid` 
* make it **private**
* initially **empty** (no `README.md`, no `.gitignore`, no license)

Clone that repo somewhere and cd into.

Then add this remote:

<tt>git remote add starter {{page.starter}}</tt>

Then do:

```
git pull starter master
git push origin master
```

# Step 2: Get OAuth working on Localhost

1.  You *must first* configure a GitHub OAuth app for <http://localhost:8080> and obtain the client-id and client-secret.

    Follow the instructions here: <https://ucsb-cs56.github.io/topics/oauth_github_setup>.

    * For the application url, use <http://localhost:8080>
    * For the callback url, also use <http://localhost:8080>
    * Note that on localhost, you typically need use `http` not `https`
     
2.  You must then copy the file `localhost.json.SAMPLE` to the file `localhost.json`.

    * Note that you SHOULD NOT edit `localhost.json.SAMPLE` directly.
    * The copied file `localhost.json` will NOT be commited to GitHub; it's in the `.gitignore`

3.  Then, edit the `localhost.json` file and put in your client id and client secret in the places indicated.

4.  Finally, IN EACH terminal session where you are going to run `mvn spring-boot:run`, and EACH TIME after you
    change the values in `localhost.json`, execute this command to load those values into the Unix environment:

    ```
    . env.sh
    ```

    That's a dot followed by a space followed by `env.sh`, not a typo.  That means to source the contents of `env.sh` into the
    current shell.  That loads the contents of `localhost.json` into the environment variable `SPRING_APPLICATION_JSON`, which
    causes those values to override those in the `application.properties` file.


5.  Now you are ready to do `mvn spring-boot:run` as usual, and see the application on <http://localhost:8080>.

    Try logging in with your GitHub account.


# Step 3: Get OAuth working on Heroku

To run on Heroku, you must go BACK to GitHub and set up a DIFFERENT client id and client secret than the one you used for
localhost.


1.  Go to the heroku.com dashboard and create a new Heroku app with the name 
    <tt>cs56-{{site.qxx}}-{{page.num}}-<i>githubid</i></tt>, replacing <tt><i>githubid</i></tt> with your
    github id.

    If that name is too long, abbreviate as needed. 

1.  Now you must create an GitHub OAuth app for <tt>cs56-{{site.qxx}}-{{page.num}}-<i>githubid</i>.herokuapp.com</tt> and obtain the client-id and client-secret.

    Follow the instructions here: <https://ucsb-cs56.github.io/topics/oauth_github_setup>.

    In the urls below, substitute your actual Heroku app name in place of <tt>cs56-{{site.qxx}}-{{page.num}}-<i>githubid</i></tt>&mdash;if you shortened it in the step above,
    you need to be sure that what you put in matches your shortened name.  Do not literally put in `githubid`.
  
    * For the application url, use <tt>cs56-{{site.qxx}}-{{page.num}}-<i>githubid</i>.herokuapp.com</tt>
    * For the callback url, also use <tt>cs56-{{site.qxx}}-{{page.num}}-<i>githubid</i>.herokuapp.com</tt>
    * Note that on Heroku, you typically need use `https` not `http`
     
2.  You must then copy the file `heroku.json.SAMPLE` to the file `heroku.json`.

    * Note that you SHOULD NOT edit `heroku.json.SAMPLE` directly.
    * The copied file `heroku.json` will NOT be commited to GitHub; it's in the `.gitignore`

3.  Then, edit the `heroku.json` file and put in your client id and client secret in the places indicated.

4.  Now, you need either to be logged into CSIL where you can run the heroku command line tool, or you need the heroku
    command line (CLI) installed on your local system.

    Use `heroku login` to login to the command line tool.

    The run the following script from the repo.  You need to do this in the same directory where you entered
    the client id and client secret values into the `heroku.json` file.

    The name of the Heroku app should match yours (e.g. change `githubid` to your githubid)

    
    <tt>./setHerokuEnv.py --app cs56-{{site.qxx}}-{{page.num}}-<i>githubid</i></tt>
    
    * Note that it may be difficult to read, but <tt>--app</tt> is two hyphens followed by <tt>app</tt>
    * You can also just use <tt>-a</tt> which is a single hyphen followed by the letter <tt>a</tt>

    You should now be able to go to the Heroku Dashboard for your app online, e.g. this link (replacing 
    <tt>app-name-here</tt> with your appname, e.g. <tt>cs56-{{site.qxx}}-{{page.num}}-<i>githubid</i></tt>)

    * <https://dashboard.heroku.com/apps/app-name-here/settings>
      
    Click "Reveal Config Vars".   You should see a configuration variable called `SPRING_APPLICATION_JSON` that contains
    the values that you entered for client id and client secret (i.e the contents of `heroku.json`).
    

5.  Now you are ready to do the steps you did in lab02 to connect your Heroku App to your Github repo, and deploy your app to Heroku and see it running.

    Try logging in with your github account.


If you get all of that running, you are done with lab06, with the exception of doing some documentation on your links on Gauchospace (as you did for lab02.)

Lab06 only requires you to get this up and running on Heroku and gets you used to
configuring an OAuth app.

The next Spring Boot labs will move us into working with APIs and creating database tables.

# Step 4: Submitting your work for grading

When you have a running web app that you can login to and logout from, visit <{{page.gauchospace_url}}> and make a submission.

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





