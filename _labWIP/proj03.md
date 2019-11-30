---
layout: lab
num: proj03
package: earthquakes
ready: true
desc: "Individual Lab Track Project, part 3"
assigned: 2019-11-30 17:00
due: 2019-12-05 23:59
github_org: "ucsb-cs56-f19"
org: "ucsb-cs56-f19"
gauchospace_url: "https://gauchospace.ucsb.edu/courses/mod/assign/view.php?id=TBD"
---

<div style="display:none" >
Look here for formatted version: http://ucsb-cs56.github.io/f19/lab/proj03
Look here for formatted version: http://ucsb-cs56.github.io/f19/labWIP/proj03
</div>


# This project is only for the individual lab track

This project is only for those on the "individual lab" track.  It counts towards the project portion of your grade (20% of your final grade, rather than the lab component of your grade.)

You may cooperate with one or more pair partners from your team to help in debugging and understanding the project, but each person should complete the work separately for themselves.

Late submissions will be accepted only up until grading is complete; submissions received after grading is complete will NOT be accepted for regrades.   To ensure that your submission is accepted, be sure that you complete it and submit on Gradescope before the published deadline.


# This is a Continuation of Proj01 and Proj02

In the final step, we'll add a database table for locations, and the ability to store favorate locations in that table

# Step by step instructions

Continue with your:
* Github repo: <tt>{{page.num}}-githubid</tt>
* Heroku App:  <tt>cs56-f19-{{page.num}}-<i>github</i></tt>


## Step 9: Add Locations table to your application

In this step, you'll add a second database table to your application, one that stores favoriate locations.  We'll add a button to each of the search results that allows us to store that search result into the database, along with the id of the user that stored it.   We'll then be able to list both all locations, plus the "favorite" locations of the currently logged in user.   For each of these, we'll be able to go right to an Earthquake search for that location.

Create a feature branch with an appropriate name, prefixed with your initials, and do your work on the feature branch (not on `master`)

The database table will store locations, and it will have these fields
* `id`, which every SQL database table generally has
* `place_id`, the id that is returned by the location search API (e.g. `198134367` for the city of Santa Barbara) 
  when returned by the search at <https://cs56-f19-proj02-pconrad.herokuapp.com/locations/results?location=Santa+Barbara>
* `name`, the name of a location (e.g. `UCSB`, `Isla Vista`, `LAX Airport`, `Golden Gate Bridge`)
* `latitude`, the latitude of that location
* `longitude`, the longitude of that location

To add a new database table to your application, take the following steps:

1. Add an `@Entity` class.  Call this file `Location.java`, and add it under the `hello/entities` directory, 
   just as you did for `AppUser` in the lab07 series.    
   * Note that the `App` prefix on `AppUser` was just a hack
     because `user` is a reserved word in Postgres SQL.  Normally we would have just called that class `User.java`
   * So your class is just `Location.java` not `AppLocation.java`.
2. Add a `@Repository` class.  Call this file `LocationRepository.java`, and add it under the `hello/repositories` directory.
3. Add a `@Controller` class called `LocationsController.java` under `hello/controllers`.  
   * Initially, just start with an endpoint to list the locations, similar 
     to the `UsersController.java`.  
   * This endpoint should be similar to the one that lists all of the users in `UsersController`.
4. Add a view in a directory `src/main/resources/templates/locations` in a file `index.html`.  Put in an HTML table that
   has appropriate headers, and that will list each location in the locations table.
5. Add an item to the navigation menu that directs the user to the endpoint that lists locations.  Note that at present,
   since there are no locations in your database, you'll have an empty list. But at least you should get that, 
   and not an error.  We'll add code to put stuff in this table in a later step.

Make sure that the code you've added so far works, and that the test you added passes

Then merge that branch into master.  Deploy the master branch on Heroku, and make sure the code works on Heroku as well.


## Step 10: Add an "Add to Favorites" button on locations results page
 
In this step, we'll add an "add to favorites" button on each location result that comes up on the location list.

This button will be in a form that uses a "post" request.   We should use a "post" request because this request will
change the state on the server by adding a new record to the database.

Create a feature branch off of master for this step with an appropriate name.

1. We'll start by creating the endpoint in the `LocationsController` class.
   * The endpoint will look like this:

     ```java
         @PostMapping("/locations/add")
         public String add(Location location, Model model) {
           locationRepository.save(location);
           model.addAttribute("locations", locationsRepository.findAll());
           return "locations/index";
         }
     ```
   * Note that this requires a variable `locationRepository`.  You'll put this into the 
     `LocationsController` in a way similar to the `userRepository` is declared inside the 
     `UsersController`.    It is declared as a private variable, and then injected into the 
     constructor.   You should code this in a similar way.

   * The code here takes a parameter `location` of type `Location` as a parameter.  That will get passed in from the `th:object` 
     attribute passed in in the form we create in the next item below.  

     The code `locationRepository.save(location)` adds that location to the database.
     
     Then `model.addAttribute("locations", locationsRepository.findAll());` stores an attribute `locations` in the `model`, which
     we can then iterate over.   We then direct the user to the list of locations, using the same view we already created in Step&nbsp;9.
     
2. Next, we'll add the button that invokes the `/locations/add` endpoint.  That requires us to build a form that contains
   "hidden" fields that contain the values we want to add to the database.
   
   We'll want an instance of this form inside each row of the table of location results that gets returned by the `/locations/results` 
   view.
   
   So, inside `src/main/resources/template/locations/results.html` find the part that creates each row in the table of results.
   
   
