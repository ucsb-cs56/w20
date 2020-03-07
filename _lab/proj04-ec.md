---
layout: lab
num: proj04-ec
package: earthquakes
ready: false
desc: "Individual Lab Track Project, Extra Credit"
assigned: 2020-03-06 17:00
due: 2020-03-14 13:00
github_org: "ucsb-cs56-w20"
org: "ucsb-cs56-w20"
gauchospace_url: "https://gauchospace.ucsb.edu/courses/mod/assign/view.php?id=3288526&forceview=1"
---

<div style="display:none" >
Look here for formatted version: http://ucsb-cs56.github.io/w20/lab/proj04-ec
</div>


# This is the EXTRA CREDIT opportunity for the individual lab track

For those on the individual lab track, the three projects (proj01, proj02, proj03) are collectively the project portion of your grade (20% of your final grade).   This project, proj04, if completed, is worth up to 100 points of "extra credit" towards the 1000 points of project points, i.e. 2% extra credit on your final course grade.

You may cooperate with one or more pair partners from your team to help in debugging and understanding the project, but each person should complete the work separately for themselves.

NO Late submissions will be accepted for the extra credit portion.  The extra credit portion is available if and only if ALL of proj01, proj02, proj03 and proj04 are submitted by their respective due dates.  

# Step by step instructions

Continue with the repo and Heroku app you used for the previous steps in the project, i.e.:

* Github repo: <tt>project-githubid</tt>
* Heroku App:  <tt>cs56-w20-project-<i>github</i></tt>

You don't need to create a new repo and Heroku app for proj04.   

# Extra Credit options

To earn the extra credit:

* Complete Step 13 below.
* When finished, do a pull request, and merge that request into master.
* Submit on Gauchospace.

Again, there are NO EXTENSIONS for the extra credit portion.  You must have completed and submitted all of proj01, proj02, proj03 
on time (not late) plus proj04 on time (not late) to be eligible to earn the extra credit. 

# Step 13: Personal Favorites

In this option, you make it so that each individual user that logs in your application has their own version of the "favorites" (this is more like what would be the case in a real application of this type.)

1. Create a feature branch with an appropriate name.
1. Add a field `uid` to the locations table.  (You do this by adding the field, and it's getters and setters to the appropriate `@Entity` class.  It should have the same data type as the existing `uid` field in the `AppUser` entity.)
2. Whenever you add an item to the locations table, be sure the uid of the currently logged in user is stored in the uid field.  Figuring out where this code goes, and how to write it, is mostly left as an exercise to you.   Just these hints:
   * On any controller method, you can add `OAuth2AuthenticationToken token` as a parameter, and a token with information about the
     currently logged in user becomes available inside that controller method.   Spring Boot takes care of initializing that parameter
     for you.
   * There is example code somewhere in your application already that can compute the `uid` from an `OAuth2AuthenticationToken`; you
     just need to find it and do likewise in the appropriate spot.
3. In a later step, we are going to restrict the listing of favorites to only show favorites for the currently logged in user.
   But, "Admin" users should still be able to see all of the favorites for all users.
   
   Your app should already have an "Admin" menu that is from the `STARTER_lab07` code.   We are going to turn that
   into a dropdown menu.   You will need to consult the documentation for Bootstrap Navigation Bars to determine how
   to do that.
   
   Your dropdown menu should have two items:
   * One is a menu item for the existing link to the list of admins.  In the user interface, call this "Update Admins".  It
     should link to the same menu item that the old "Admin" meny item linked to before we turned it into a dropdown.
   * The second is a new menu item that will take us to a page that lists the Favorites for all users, not just the 
     logged in user.  Call that one "All Favorites".   

     The "All Favorites" feature should use a different view;    Call this view `locations/admin` 
     (i.e. `src/main/resources/templates/locations/admin.html`).  (Of course, this will need
     a controller endpoint; the code will eventually be similar to the `/locations` controller endpoint.  But for now,
     it can just be an `@GetMapping` that returns the name of the `.html` file relative to `src/main/resources/templates`,
     i.e. `locations/admin`.   

     For completing this step, `locations/admin.html` can be just a blank placeholder page; 
     you can add stuff onto that page in the next step.
     
4. The admin menu, as well as the two endpoints linked to by the admin menu should be available only to Admin users.  
   You can find code in the app already that 
   handles this restriction.   That code redirects non-Admin users and displays a flash message; your app should
   do the same.
   
   Note that it is not enough to just hide the menu item in the navbar for non-admins.  You also need to protect the
   endpoint from folks that type it into the URL bar by hand.   Again, you can find code to handle this case 
   in the existing code (it has been there since the `STARTER_lab07` code.)
       

5. Now modify the `locations/admin` page to be a view that is similar to the view for `Favorites`, except that this
   one also includes the `uid` values.

   To test this feature, you'll need users to login with two different
   Google accounts.  Some suggestions on how to do that appear below
   in the section "Testing with two different Google Accounts".

   You want to check whether different users can have different favorites.
   Eventually (though perhaps not yet at this step):

   * All users show up on the Users menu (for Admins only) as having logged in
     (this should already be true)
   * Admins can see all users favorites on the "Admin/All Favorites" menu
     item, each with different uid values.  (This should already be true)
   * Each user sees only their *own* Favorites when logged in under their account (this should not be true yet; we are implementing this at the next step.)


4. Now, to prepare for showing only the individual users favorites,
   add a `findByUid` method to the `LocationRepository` interface.
   Note that you don't have to write any code for it; you just have to
   specify the method with the correct naming convention, and Spring
   Boot will make sure that the proper code gets generated.  You need
   to make sure the return value and parameter types and names are
   correct though.  See other examples of Spring Boot database
   repositories for examples.
   
5. Now, in the controller method for the the list of favorite
   locations at the `/locations` endpoint, use the `findByUid` instead
   of the `findAll` method, and only show the favorites of the
   currently logged in user.  This view doesn't need the `uid` field,
   since it is implied that only favorite locations of the currently
   logged in user are being shown.

   Of course you'll need to know the uid of the currently logged in
   user so that you can pass it to `findByUid`. You already did that
   once in another controller method, so you'll need that same trick
   again.
   
6. At this point, if you've followed all the instructions and you are
   able to test, you'll see that things *almost* work, but there still
   may be a bug:
   
   * When selected, the Admin menu correctly shows all locations for all users
   * When selected, the Favorites menu correctly shows only locations added by the current user
   * BUT, when a user adds a new favorite, they are redirected to a page showing ALL of the favorite locations for all users.
   
   Locate the root cause of this bug, and fix it.  To do that, you'll
   need to trace through the code that handles the case of adding a
   new favorite.  Since you wrote that code in an earlier part of the
   project, you should be able to find it, and know how to fix it.
   

When all seems to be working, merge into master (if not already done), and proceed to the "final steps" below.
   
# Testing with another Google Account

There are several options for testing with multiple Google Accounts to
be sure that your app works properly.


1. If you yourself have both an `@ucsb.edu` Google Account, as well
   as a non-ucsb Google account, you can test that way, logging in yourself
   with the two different accounts.

2. If you don't have more than one Google Account yourself, you can
   try to find another UCSB student that can help you test.  If there is
   one handy in person, ask them.

3. If neither options 2 or 3 work, then you can post on the Slack in the
   `#individual-project-track` channel and ask for someone to try testing
   your app. It's nice if you can volunteer to test their app as well in
   return.

   For this approach, you need to:

   * do a pull request and accept it to deploy your app to Heroku.
   * post a link to your running Heroku app, and indicate you need
     someone to login, try add a "favorite location" or two, and then
     ping you back on Slack.

4. Finally, try coming to either open or closed lab hours, and ask a
   fellow student, mentor or TA in person.


Whether running on `localhost` you'll want to either:
* logout and login again under the other account
* use a different browser to login to the second account
* use a "Private Browsing" or "Incognito" window to login to the second account.

On Heroku, you have all the options above, plus the option of being on another computer.

# Final Steps

## Final Step 0: A few things to check

1. Look over the staff's working version here:

   * <https://cs56-f19-proj04-v1-pconrad.herokuapp.com/> TODO: Not yet updated... 

   Compare it to your working version on Heroku.
   
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

Note that we are omitting the javadoc and jacoco report step for this project.

## Final Step 2: Check your README.md

Check that your README.md has links to
* your the repo for your app
* your app running on Heroku

## Final Step 4: Submit on Gauchospace

Then, finally visit <{{page.gauchospace_url}}> and make a submission.

In the text area, enter something like this, substituting your repo name and your Heroku app name:

<div style="font-family:monospace;">
repo name: https://github.com/chrislee123/project-githubid<br>
on heroku: https://cs56-{{site.qxx}}-project-chrislee123.herokuapp.com<br>
</div>

Then, **and this is super important**, please make both of those URLs **clickable urls**.

The instructions for doing so are here: <https://ucsb-cs56.github.io/topics/gauchospace_clickable_urls/>


# Grading Rubric:

| item | points | description |
|------|--------|-------------|
| (a) |	20 | You can login and add a favorite |
| (b) |	20 | On the favorites menu you only see your own favorites (the ones for the logged in user |
| (c) |	10 | After adding a new favorite, the page to which you are redirected still shows only your own favorites |
| (d) |	10 | The admin menu item on the navigation bar is now a drop down that has two items on it |
| (e) |	10 | The two items on the admin menu are `Update Admins` and `All Favorites` | 
| (f) |	10 | The `Update Admins` meny item takes you to the old admin table |
| (g) |	20 | The `All Favorites` menu item takes you to a list of favorites for ALL users |





    
