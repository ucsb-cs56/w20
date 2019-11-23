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

We'll add the ability for the user to search for earthquakes at some distance from any location, not just the hardcoded latitude and longitude of the UCSB campus.

In this project, we will:
* Start with your lab07 code, deploying it to a new Heroku instance
* Do some refactoring to get the code base ready for a larger application
* Add a form where you the user can enter a location
* Add interaction with a separate API that looks up latitude and longitude of locations (also known as "geocoding").
* Add a database table for locations, and the ability to store locations in that table
* Add the ability to look up Earthquakes from any of those locations.

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

## Step 3: Refactoring

In this step, we'll do some refactoring to make it easier to add more feature to the code.

Create a feature branch called `xxRefactoring` (where `xx` are your initials).

Then, do these refactorings.  In general we want to put code into subdirectories (i.e. different packages) to make it easier to understand the structure of the application as it grows larger.   We'll also get rid of a few files that we are no longer using.

1.  Add two files under `src/main/java/hello/controllers`.  
    * Each of them should start as copies of `src/main/java/hello/WebController.java`.  
    * Note that as a result, each of them will be in the package `hello.controllers`, rather than `hello`
    * The first one should be called `src/main/java/hello/controllers/HomeControlller.java`. 
       * In this one, delete all of the methods except the ones for the `/` and `/login` endpoints.
    * The second one should be called `src/main/java/hello/controllers/EarthquakesControlller.java`. 
       * In this one, delete all of the methods except the ones for the `/earthquakes/search` and `/earthquakes/results` endpoints.
       
    This may break some of your existing code, but you'll be able to figure out how to fix it.  
    * Your controllers were in the package `hello`.
    * Now they are in the package `hello.controllers`
    * That means that any classes they might have been using, such as `EarthquakeQueryService` might need an `import` statement
      such as:
      ```
      import hello.EarthquakeQueryService;
      ```
      Figuring out whether there are any other such classes, and what they might be, is left as an exercise to you.
      
    Try running `mvn clean compile` to see what does and does not compile.
    
    Then, run your `mvn test` as well as testing with `mvn -P localhost spring-boot:run` to make sure everything still works.
    If so, do a commit with an appropriate message.
2.  The files `src/main/resources/templates/page1.html` and `src/main/resources/templates/page2.html` are no longer being used, 
    so remove them from the application.  You'll need to use `git rm filename` to make sure that the removal goes into a commit.
    
    Again, run `mvn test` and test the application on localhost.  If all is well, commit this change.
3.  Create a directory called `src/main/java/hello/services` 
    
    Move these files into the new `services` directory.  Change their package names as appropriate:
    * `src/main/java/hello/EarthquakeQueryService.java`
    * `src/main/java/hello/GithubOrgMembershipService.java`
    * `src/main/java/hello/MembershipService.java`

    Note that this will break some of your existing code for the same reason as before; we are changing which package these classes
    belong to.  Sort all of that out.

4.  Finally, create a directory called `src/main/java/hello/searches`.  Into this one we are going to put the file
    * `src/main/java/hello/EqSearch.java`
    
    Adjust package names as needed.
    
5.  Finally, we've lived with the name `hello` as our package name long enough.  That's just a package name we inherited from some
    "Hello World" type app that we used as the basis of this code originally.
    
    A proper name would be <tt>edu.ucsb.cs56.f19.{{page.num}}</tt>.  But 
    we'll keep it simple and settle on  <tt>{{page.num}}</tt>.
    
    Change the name of your `src/main/java/hello` directory to <tt>src/main/java/{{page.num}}</tt>.
    
    Then, change the package name `hello` in all of your source code to <tt>{{page.num}}</tt>.   
    * That means also changing, for example, <tt>hello.entities</tt> to <tt>{{page.num}}.entities</tt> 
    
    Before you panic, read through this advice:
    * If you are still editing individual files with `vim` or `emacs`, i.e. you aren't yet using an IDE that allows you to look at the whole project at once, this is a moment when the power of a "whole app" IDE such as VSCode, Atom, SublimeText, IntelliJ, etc. begins
      to be more clear.  Those tools have a way to do "search/replace" across the entire project fairly easily.  If you are using
      one of those IDEs, use a web search to learn about how to do search/replace across multiple files.
    * If you aren't there yet, there is a way to do it at the command line: <https://ucsb-cs56.github.io/topics/unix_search_and_replace_multiple_files/>
    * `vim` and `emacs` actually do have editing across multiple files, though it can be a bit complex.  You could learn about it, though, if you really want to stick with your old ways.
    * Or, you can just go through it file by file.  It isn't really that many files.
    
    After such a major change, you'll definitely need to do a `mvn clean compile` to make sure that everything still compiles before
    even trying to run.
    
    And before you try to run, you'll need to make this change in your `pom.xml`:
    
    Find the line that says:
    
    ```
      <mainClass>hello.Application</mainClass>
    ```

    And change it appropriately (hint: the `public static void main...` is currently in `Application.java`, 
    which used to be in the `hello` package, but is now in the `proj01` package.)
    
When this all seems to work, do a pull request, and merge this branch into master.    

## Step 4: Add a location search form to your app

In this step, you'll add a location search to your app.

The user story for this feature is:

* As a user, <br/>
  I want to be able to search for a location and find it's latitude and longitude <br/>
  So that I can eventually search for Earthquakes close to anyplace (not just the hardcoded lat/long of UCSB).

We'll add another form with just one field: one where the user can enter a place name (location).   

There will be a search button.   When that button is clicked, a location API will be used to find a list of 
matching locations.  The latitude and longitude of each of those will then be displayed.

This step just adds the form; the API interaction is in the step after this one.

In an even later step, we'll add a button beside each of these that allows it to be added into a database of known locations.  

Finally, that database of known locations will be used to allow the user to search for Earthquakes from any location in the database.

For this step, I recommend that you proceed in a manner similar to Steps 8 and 9 of lab07b.  We'll first concentrate just on Step 8.

1. Create a feature branch off of master.
2. In `src/main/resources/templates/` create a directory called `locations`.  
   * In it, create a form for your location search (similar to the form you created in Step 8b of lab07b). 
   * It will have just one field on it called `location`.
3. In the `searches` directory, create a bean called `LocSearch` similar to the `EqSearch` bean we created in Step 8c.
   * The object represented by this class will have only one field, a `String` field called `location`.
4. In the `controllers` directory, create a new controller called `LocationsController.java`.  You can use `EarthquakesController.java` as an example to follow.  You'll want a controller method that routes to your form, just as you created one in Step 8d of lab07b.
5. Add a menu item called "Locations" that routes to the form, just like you did in Step 8e of lab07b.
6. You guesssed it: Add a route for the results, and a view for the results (steps 8f and 8g) of lab7b, but as appropriate for 
   your location search.  The view will initially just echo back the location we entered.   Later we'll add the results after
   we have a service that looks up the JSON.
7. Add a file `/src/test/java/hello/LocationSearchTest.java` that tests whether there is a "Locations" menu item on the navigation bar
   that routes to your location search page.

This is all enough for a pull request, just like it was in lab07b.   Test all of this throughly with `mvn test` and on localhost.

Then, pull request, merge it into master, and deploy it to Heroku and test there.

## Step 4: Implement Location Lookup API



1. In the `services` directory, create a `LocationQueryService` similar to the `EarthquakeQueryService`. 
   * Your first version of it should return fake JSON, just as we did in lab7b (step 9b).
   * The fake JSON is enough for now.
3. 


## Step 5: Add another database table to your application.

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



