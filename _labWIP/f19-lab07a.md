---
assigned: 2020-02-18 17:00
desc: "Spring Boot Skills, Part 1"
due: 2020-02-24 23:59
gauchospace_url: tbd
github_org: ucsb-cs56-w20
labnum: lab07
layout: lab
num: lab07a
org: ucsb-cs56-f19
prev: lab06
ready: false
starter: https://github.com/ucsb-cs56-w20/STARTER_lab07

---

<div style="display:none" >
    <p>Look here for formatted version: http://ucsb-cs56.github.io/w20/labWIP/lab07a</p>
    <p>Look here for formatted version: http://ucsb-cs56.github.io/w20/lab/lab07a</p>
</div>


# Individual lab

This is an **individual** lab on the topic of Java web apps on Heroku.

You may cooperate with one or more pair partners from your team to help in debugging and understanding the lab, but each person should complete the lab separately for themselves.


# Goals

You will learn:

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

   * <tt>{{page.labnum}}-githubid</tt>.

   We are making the repo public so that we can set up Travis-CI; the
   free tier requires the repo to be public.

2. We'll then add a remote the starter code.

3. We'll then get the app running on both localhost and Heroku.  We'll have to set up 
   OAuth as we did in 
   {{prev.num}}, but this time instead of GitHub, we are setting up OAuth for Google.

4. We'll now create feature branch to add a form for location search to the app.

   ```
   git checkout -b abLocationSearch
   ```

   where `ab` is your initials, and `LocationSearch` describes what you are going to do 
   on the branch.  Use your own initials, not `ab` (unless your initials literally *are* `ab`).
   
   On this branch, we will create a simple HTML form using Thymeleaf.

   We'll need to add a controller method that routes the user to this form,
   and be sure that a link on the navigation menu routes to this page.

   That page will gather some information from the user, and then
   echo that information back on a second "results" page.

   This is an intermediate step; echoing the information back is not
   useful in and of itself.  In the next step, we'll do something useful
   with that information.

   This step will end with a pull request.  You should then merge
   that pull request into master.

5. In this step, we'll make yet another 
   branch where we do something useful
   with the information on the results page.  We'll make a call to an API
   that provides information in JSON format.

   In this step we'll only
   echo that JSON information on the page; it won't yet be in a format
   that is pleasing to an end user.   But we'll be able to see that we
   are making progress.

6. In this final step, we'll learn how 
    to transform that JSON string into
    usable Java objects, and use those Java objects to put useful information
    on the page.

    There will be one final pull request at this stage, and we'll be done with
    lab07a.
   
# Step by step instructions (lab07a)

## Step 1: New empty public repo

We will start with a new, empty, public repo called:

* <tt>{{page.labnum}}-githubid</tt>.

We are making the repo public so that we can set up Travis-CI; the
free tier requires the repo to be public.

## Step 2. Add remote for {{page.prev}}

Now add a remote for the starer code.

<tt markdown="1">git remote add starter {{page.starter}}</tt>

Then, to pull the code from your {{page.prev}}  work into this new repo, use:

```
git pull starter master
git push origin master
```

Check that you see your code on github under the  repo name <tt>{{page.labnum}}-githubid</tt>.


## Step 3: Deploy app on localhost and heroku

Now, we'll get the app running on localhost first, then heroku.

### Step 3a: Running on localhost

To get it running on localhost:
1. Copy from `localhost.json.SAMPLE` to `localhost.json`.   
2. Follow the instructions in the README.md to setup OAuth for Google, and put the correct values into the
   `localhost.json` for client-id and client-secret.
3. Type `source env.sh`
4.  Type `mvn spring-boot:run` and see if you can access the web app, login, and logout.

### Step 3b: Add yourself as an admin in `application.properties`

As shown in lecture, the file `src/main/resources/application.properties` has this line of code:

```
app.admin.emails=phtcon@ucsb.edu,scottpchow@ucsb.edu,zsisco@ucsb.edu,pingyuan@ucsb.edu
```

This is a comma-separated list of emails of users that are designated as admins in the app.  Add your email to this list (leaving the emails for your instructor and TAs intact).

Then restart the app, login again, and see if you are now an admin user.  You should be able to see the Admin and User menus.

Commit this change with an appropriate commit message, e.g.

```
git commit -m "cg - added my email to app.admin.emails in application.properties
```

Push this change to the master branch.

### Step 3c: Running on Heroku

Next we'll try getting the app running on Heroku. 

Create a new heroku app called `cs56-w20-lab07-githubid`

That is, your app URL should be:

`cs56-w20-lab07-githubid.herokuapp.com`.

Link this with your Github Repo.

You'll have to set up a new Google OAuth app.  You can find the instructions in the README.md for the starter code from {{page.prev}} which you copied into your current lab.    The client-id and client-secret have to be different, because the callback-url has to be different.

Then, run the command

```
./setHerokuEnv.py -a cs56-w20-lab07-githubid
```

replacing `cs56-w20-lab07-githubid` with your app name.

You should then be able to deploy on heroku and get the app working.


### FROM HERE

# Final Step: Submitting your work for grading

When you have a running web app, visit <{{page.gauchospace_url}}> and make a submission.

In the text area, enter something like this, substituting your repo name and your Heroku app name:

<div style="font-family:monospace;">
repo name: https://github.com/chrislee123/spring-boot-minimal-webapp<br>
on heroku: https://cs56-{{site.qxx}}-{{page.labnum}}-chrislee123.herokuapp.com<br>
</div>

Then, **and this is super important**, please make both of those URLs **clickable urls**.

The instructions for doing so are here: <https://ucsb-cs56.github.io/topics/gauchospace_clickable_urls/>


# Grading Rubric:

TBA.  It will be 100 points divided across the  steps in the lab.


| Rubric Item | Points |
|-------------|--------|
| All items TBA | 100 |

