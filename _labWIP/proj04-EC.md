---
layout: lab
num: proj04
package: earthquakes
ready: true
desc: "Individual Lab Track Project, Extra Credit"
assigned: 2019-12-01 17:00
due: 2019-12-05 23:59
github_org: "ucsb-cs56-f19"
org: "ucsb-cs56-f19"
gauchospace_url: "https://gauchospace.ucsb.edu/courses/mod/assign/view.php?id=TBD"
---

<div style="display:none" >
Look here for formatted version: http://ucsb-cs56.github.io/f19/lab/proj04-EC
Look here for formatted version: http://ucsb-cs56.github.io/f19/labWIP/proj04-EC
</div>


# This is the EXTRA CREDIT opportunity for the individual lab track

For those on the individual lab track, the three projects (proj01, proj02, proj03) are collectively the project portion of your grade (20% of your final grade).   This project, proj04, if completed, is worth up to 100 points of "extra credit" towards the 1000 points of project points, i.e. 2% extra credit on your final course grade.

You may cooperate with one or more pair partners from your team to help in debugging and understanding the project, but each person should complete the work separately for themselves.

NO Late submissions will be accepted for the extra credit portion.  The extra credit portion is available if and only if ALL of proj01, proj02, proj03 and proj04 are submitted by their respective due dates.  

# Step by step instructions

Continue with the repo and Heroku app you used for the previous steps in the project, i.e. either:

* Github repo: <tt>proj01-githubid</tt>
* Heroku App:  <tt>cs56-f19-proj01-<i>github</i></tt>

OR

* Github repo: <tt>proj02-githubid</tt>
* Heroku App:  <tt>cs56-f19-proj02-<i>github</i></tt>

Either way is fine.  You don't need to create a new repo and Heroku app for proj03.   

# Extra Credit options

There are two options for the extra credit.  Choose *one* of them.  You don't need to do more than one, and while you may get extra learning by doing more than one, you will not get "extra" extra credit by doing more than one.

To earn the extra credit:
* Choose either option 1 or option 2
* Create a feature branch with an appropriate name
* Do all of option 1, or all of option 2 (or as much as you have time for), dividing your work into appropriate incremental commits
  with appropriate commit messages.
* When finished, do a pull request, and merge that request into master.
* Submit on Gauchospace.  In your submission on Gauchospace, tell us which option you completed.

# Option 1: Personal Favorites

In this option, you make it so that each individual user that logs in your application has their own version of the "favorites" (this is more like what would be the case in a real application of this type.)

1. Add a field `uid` to the locations table.  (You do this by adding the field, and it's getters and setters to the appropriate `@Entity` class.
2. Whenever you add an item to the locations table, be sure the uid of the currently logged in user is stored in the uid field.
3. Add a `findByUid` method to the `LocationRepository` class.
4. In the list of favorite locations, use the `findByUid` instead of the `findAll` method, and only show the favorites of the 
   currently logged in user.
5. To make it clear whether the app is working or not, add an Admin menu item in the navigation bar that allows you to see
   all of the favorites, regardless of uid.  (In reality, that menu should probably be shown only to Admin users, 
   but we won't implement that in this version.)
   
# Option 2: Add User Settings

In this option, you add a User Settings menu to the drop down that appears when a user is logged in.  In that user settings menu, you allow the user to specify the default distance and minimum magnitude for earthquakes that is used for searches.

You can use some of the code in the codebase for the [ucsb-mapache-search](https://github.com/ucsb-cs56-f19/mapache-search) as a guide for how to add User Settings to an application.  In that application, the user setting is an API key for doing searches.   In your application, the User Settings will be the default distance and default minimum magnitude.  

Note however, that the code in the `ucsb-mapache-search` (at least as of the time I wrote this; it's an evolving code base) doesn't necessarily do everything right.  I'l note some of the ways you can improve on that code below.

1. Add additional fields called `defaultDistance` and `defaultMinMag` to the database `@Entity` for users (`AppUser`).  They should
   have the same data types as `distance` and `minmag` in the Earthquakes search.   When new users are created, those values should
   initially be zero.
2. Add an additional button for User Settings on the drop down menu that appears when users are logged in.  It should be a link to User Settings, and it should appear above the Logout button when the drop down menu is selected.   It should link to a new endpoint called `user/settings`
3. In the `UsersController` add endpoints for `/user/settings` and `user/settings/update`.  As a guide, you can adapt code from
   the `UserController` in the Mapache Search app linked to above.  Note that the sanitizing for removing whitespace doesn't really
   apply; that was intended to remove white space from a key.
4. You'll need a view for the user settings page; that view should provide the  update a form on which they can update default distance, as well as a form to update the default minimum magnitude.  
   * There should be a button that submits the form as a post request to the endpoint `user/settings/update`
   * There should also be a link that says "Cancel" that takes you to the home page of the application
   * The values of default distance and default minimum magnitude should be pre-filled with the values from the database before the
     user sees the form.  Note that the `user-mapache-search` code does not currently do that.  However, you might find code in the
     [ucsb-tutor-scheduler](https://github.com/ucsb-cs56-f19/ucsb-tutor-scheduler) app that *does* handle that case for updating
     various entities.
   * If/when the form is submitted to the `user/settings/update` endpoint, the values should be updated.  Those updated values should
     be seen if/when the user returns to the User Settings form
5. Finally, the Earthquake search should be modified so that when the `earthquakes/search` endpoint is loaded, the values of `Distance (km)` and `Minimum Magnitude` that show up on the form are pre-filled with the defaults from the User Settings, and those values are used in the Earthquake search unless the user changes them.


# Final Steps

## Final Step 0: A few things to check

1. Look over the staff's working versions here:

   * Option 1: <https://cs56-f19-proj04-v1-pconrad.herokuapp.com/>
   * Option 2: <https://cs56-f19-proj04-v2-pconrad.herokuapp.com/>

   Compare it to your working version on Heroku for the option you completed.  
   
   If you see differences, try to determine which of these is true:
   * Is is a minor difference that doesn't matter?
   * Did the staff miss part of the instructions?
   * Did you miss part of the instructions?
   
   If you aren't sure, ask questions on Slack.

## Final Step 1: Check that your code is all on master and Heroku

* Have you pushed all changes to your last feature branch?
* Have you done a final pull request?
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
* your repos Travis-CI status

## Final Step 4: Submit on Gauchospace

Then, finally visit <{{page.gauchospace_url}}> and make a submission.

In the text area, enter something like this, substituting your repo name and your Heroku app name:

<div style="font-family:monospace;">
repo name: https://github.com/chrislee123/proj01-githubid<br>
on heroku: https://cs56-{{site.qxx}}-proj01-chrislee123.herokuapp.com<br>
Option chosen: #1
</div>

(Use proj01 or proj02 as appropriate).

Then, **and this is super important**, please make both of those URLs **clickable urls**.

The instructions for doing so are here: <https://ucsb-cs56.github.io/topics/gauchospace_clickable_urls/>


# Grading Rubric:

TBA






    
