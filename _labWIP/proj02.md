---
layout: lab
num: proj02
name: earthquakes
ready: true
desc: "Individual Lab Track Project, part 1"
assigned: 2019-11-22 17:00
due: 2019-12-03 23:59
github_org: "ucsb-cs56-f19"
org: "ucsb-cs56-f19"
gauchospace_url: "https://gauchospace.ucsb.edu/courses/mod/assign/view.php?id=TBD"
---

<div style="display:none" >
Look here for formatted version: http://ucsb-cs56.github.io/f19/lab/proj02
Look here for formatted version: http://ucsb-cs56.github.io/f19/labWIP/proj02
</div>


# This project is only for the individual lab track

This project is only for those on the "individual lab" track.  It counts towards the project portion of your grade (20% of your final grade, rather than the lab component of your grade.)

You may cooperate with one or more pair partners from your team to help in debugging and understanding the project, but each person should complete the work separately for themselves.

Late submissions will be accepted only up until grading is complete; submissions received after grading is complete will NOT be accepted for regrades.   To ensure that your submission is accepted, be sure that you complete it and submit on Gradescope before the published deadline.


# This is a Continuation of Proj01

In Part 1 of this project, we 
* Started with your lab07 code, deploying it to a new Heroku instance
* Did some refactoring to get the code base ready for a larger application
* Added a form where you the user can enter a location
* Added interaction with a separate API that looks up latitude and longitude of locations (also known as "geocoding").
* Added the ability to list all of the matching locations returned by the API.

In this steps of the project, we'll:
* Add the ability to look up Earthquakes from any of those locations.

In the final step, we'll:
* Add a database table for locations, and the ability to store locations in that table

# Step by step instructions

Continue with your:
* Github repo: <tt>{{page.num}}-githubid</tt>
* Heroku App:  <tt>cs56-f19-{{page.num}}-<i>github</i><tt>

## Step n: Add parameter to Earthquake Search

Up until now, when searching for earthquakes, the latitude and longitude parameters were hardcoded.   Only the distance and minimum magnitude were specified as parameters.

However, we can modify our earthquake search endpoint so that the latitude and longitude are also specified as parameters, with UCSB as the default.

Once we do that, we can add buttons to each of our location search results so that we can directly do an earthquake search from any of those results.  After all, each of them already has a latitude and longitude.   

That's what we'll do in this step.


## Step 5: Add another database table to your application.

In this step, you'll add a second database table to your application, one that stores locations.  We'll add a button to each of the search results that allows us to store that search result into the database.  Locations that are stored in the database will then be Locations have a name, and a latitude and longitude.  This will enable you to calculate earthquake distances not only from UCSB (currently hard coded), but from any location you enter into your application.

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



 
