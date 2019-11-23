---
layout: lab
num: proj01
ready: true
desc: "Individual Lab Track Project 1"
assigned: 2019-11-22 17:00
due: 2019-12-03 23:59
github_org: "ucsb-cs56-f19"
org: "ucsb-cs56-f19"
gauchospace_url: "https://gauchospace.ucsb.edu/courses/mod/assign/view.php?id=TBD"
---

<div style="display:none" >
Look here for formatted version: http://ucsb-cs56.github.io/f19/lab/lab08
Look here for formatted version: http://ucsb-cs56.github.io/f19/labWIP/lab08
</div>


# This project is only for the individual lab track

This project is only for those on the "individual lab" track.  It counts towards the project portion of your grade (20% of your final grade, rather than the lab component of your grade.)

You may cooperate with one or more pair partners from your team to help in debugging and understanding the project, but each person should complete the work separately for themselves.

Late submissions will be accepted only up until grading is complete; submissions received after grading is complete will NOT be accepted for regrades.   To ensure that your submission is accepted, be sure that you complete it and submit on Gradescope before the published deadline.


# Goals

The goal of this project is to determine whether you can apply the skills covered in lab07a, lab07b and lab07c in a new context, and to see if you can apply those skills in a new context.

# Step by step instructions

## Step 1:  Create a public {{page.num}}-githubid repo

* Create a repo in the <tt>{{page.org}}</tt> organization with the name <tt>{{page.num}}-<i>githubid</i></tt>, substituting
your github id in place of <tt><i>githubid</i></tt>
* Add a remote that points to your `lab07c` code.
* Pull in the `lab07c` code as your starter code.
* Push to `origin master`

## Step 2:  Set up your new project on Heroku at <tt>cs56-f19-{{page.num}}-<i>github</i><tt>

* Create a Heroku app with the name <tt>cs56-f19-{{page.num}}-<i>github</i><tt>.
* Provision that Heroku app with a Heroku Postgres database.
* Configure this a GitHub OAuth client-id/client-secret for this Heroku app. 
   * Note that you won't be able to reuse the client-id/client-secret from previous Heroku apps, because
     the URL is different.
   * Remember that you'll need to copy `heroku.json.SAMPLE` to `heroku.json`, put in the values, and then
     run:
     ```
     setHerokuEnv.py --app APPNAME
     ```
     to load those values into Heroku in the `SPRING_APPLICATION_JSON` environment variable.
 * Deploy your master branch to your Heroku app and make sure that is loads and operates correctly.

## Step 3: Add another database table to your application.

In this step, you'll add a second database table to your application, one that stores locations.  Locations have a name, and a latitude and longitude.  This will enable you to calculate earthquake distances not only from UCSB (currently hard coded), but from any location you enter into your application.

Create a feature branch with an appropriate name, prefixed with your initials, and do your work on the feature branch (not on `master`)

The database table will store locations, and it will have these fields
* `id`, which every SQL database table generally has
* `name`, the name of a location (e.g. `UCSB`, `Isla Vista`, `LAX Airport`, `Golden Gate Bridge`)
* `latitude`, the latitude of that location
* `longitude`, the longitude of that location

To add a new database table to your application, you'll need to:
1. Add an `@Entity` class.  Call this file `Location.java`, and add it under the `hello/entities` directory, 
   just as you did for `AppUser` in the lab07 series.    
   * (Note that the `App` prefix on `AppUser` was just a hack
     because `user` is a reserved word in Postgres SQL.  Normally we would have just called that class `User.java`)
2. Add a `@Repository` class.  Call this file `LocationRepository.java`, and add it under the `hello/repositories` directory.
   Add a `@Controller` class called `LocationsController.java` under `hello/controllers`.  
   * Initially, just start with an endpoint to list the locations, similar 
    to the `UsersController.java`.
3. Add a view in a directory `src/main/resources/templates/locations` in a file `index.html`.  Put in an HTML table that
   has appropriate headers, and that will list each location in the locations table.
4. Add an item to the navigation menu that directs the user to the endpoint that lists locations.  Note that present,
   since there are no locations in your database, you'll have an empty list. But at least you should get that, 
   and not an error.  We'll add code to put stuff in this table in a later step.
5. Add a test in the file `src/test/java/hello/HomePageTest.java` that checks whether there is a link on the Navigation header
   called "Locations" takes the user to the endpoint for listing locations.
  
   It might look similar to this one that checks whether there is an endpoint for `Users`.  If you didn't add this test already
   in the lab07 sequence, add it now as well.  
  
   ```
   @Test
    public void getHomePage_hasUsersLink() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(xpath("/html/body/div/nav/div/ul[1]/li[3]/a").exists())
                .andExpect(xpath("/html/body/div/nav/div/ul[1]/li[3]/a").string("Users"));
    }
    ``` 
   
   Note that your XPath expressions may be different from the ones shown; the
   exact XPath expression depends on the structure of your HTML.  You can find the right XPath expression by using the developer
   tools of your browser.  For example:
   
   * Firefox: <https://stackoverflow.com/a/51665922>
   * Chrome: <https://stackoverflow.com/a/42194160>

   A primer on XPath is here: <https://www.w3schools.com/xml/xpath_intro.asp>

Make sure that the code you've added so far works, and that the test you added passes.

Then merge that branch into master.  Deploy the master branch on Heroku, and make sure the code works on Heroku as well.

## Step 4: Add a search for location to your app


