---
layout: lab
num: proj01
package: earthquakes
ready: true
desc: "Individual Lab Track Project, part 1"
assigned: 2019-11-22 17:00
due: 2019-12-03 23:59
github_org: "ucsb-cs56-f19"
org: "ucsb-cs56-f19"
gauchospace_url: "https://gauchospace.ucsb.edu/courses/mod/assign/view.php?id=TBD"
---

<div style="display:none" >
Look here for formatted version: http://ucsb-cs56.github.io/f19/lab/proj01
Look here for formatted version: http://ucsb-cs56.github.io/f19/labWIP/proj01
</div>


# This project is only for the individual lab track

This project is only for those on the "individual lab" track.  It counts towards the project portion of your grade (20% of your final grade, rather than the lab component of your grade.)

You may cooperate with one or more pair partners from your team to help in debugging and understanding the project, but each person should complete the work separately for themselves.

Late submissions will be accepted only up until grading is complete; submissions received after grading is complete will NOT be accepted for regrades.   To ensure that your submission is accepted, be sure that you complete it and submit on Gradescope before the published deadline.


# Goals

The goal of this project is to determine whether you can apply the skills covered in lab07a, lab07b and lab07c in a new context, and to see if you can apply those skills in a new context.

We'll add the ability for the user to search for earthquakes at some distance from any location, not just the hardcoded latitude and longitude of the UCSB campus.

In Part 1 of this project, we will:
* Start with your `lab07` code, deploying it to a new Heroku instance
* Do some refactoring to get the code base ready for a larger application
* Add a form where you the user can enter a location
* Add interaction with a separate API that looks up latitude and longitude of locations (also known as "geocoding").
* Add the ability to list all of the matching locations returned by the API.

In later steps of the project, we'll:
* Add the ability to look up Earthquakes from any of those locations.
* Add a database table for locations, and the ability to store locations in that table

# Step by step instructions

## Step 1:  Create a public {{page.num}}-githubid repo

* Create a repo in the <tt>{{page.org}}</tt> organization with the name <tt>{{page.num}}-<i>githubid</i></tt>, substituting
your github id in place of <tt><i>githubid</i></tt>
* Add a remote that points to your `lab07c` code.
* Pull in the `lab07c` code as your starter code.
* Push to `origin master`
* Set up your repo to publish to Travis-CI (see lab07b Step 6)
* Set up GitHub Pages on your repo, and generate javadoc and jacoco report 

As a reminder, that last part goes like this:
   
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

## Step 2:  Set up your new project on Heroku at <br/ > <tt>cs56-f19-{{page.num}}-<i>github</i></tt>

* Create a Heroku app with the name <tt>cs56-f19-{{page.num}}-<i>github</i></tt>.
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

Now, add links to your Heroku App, your app's GitHub pages home page, and your Travis-CI page into your README.md.
* You may make that change directly on the master branch.

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
    * Then, delete the old `src/main/java/hello/WebController.java`, since all of its code is now in the other two controllers.
    
    This may break some of your existing code, but you'll be able to figure out how to fix it.  
    * Remember that the classes need to be named the same as the files they are in.
    * The first line should now be `package hello.controllers;`
    * Since the package is now `hello.controllers` that are in different packages (e.g. `hello`)  such as `EarthquakeQueryService` 
      might now need an `import` statement
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
    
    A proper name would be <tt>edu.ucsb.cs56.f19.{{page.package}}</tt>.  But 
    we'll keep it simple and settle on  <tt>{{page.package}}</tt>.
    
    Change the name of your `src/main/java/hello` directory to <tt>src/main/java/{{page.package}}</tt>.
    
    Then, change the package name `hello` in all of your source code to <tt>{{page.package}}</tt>.   
    * That means also changing, for example, <tt>hello.entities</tt> to <tt>{{page.package}}.entities</tt> 
    
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
    which used to be in the <tt>hello</tt> package, but is now in the <tt>{{page.package}}</tt> package.)
    
When this all seems to work, do a pull request, and merge this branch into master.    

## Step 4: Add a location search form to your app

In this step, you'll add a location search to your app.

The user story for this feature is:

* As a user, <br/>
  I want to be able to search for a location and find it's latitude and longitude <br/>
  So that I can eventually search for Earthquakes close to anyplace (not just the hardcoded lat/long of UCSB).

We'll add another form with just one field: one where the user can enter a place name (location).   

There will be a search button.   Eventually, when that button is clicked, a location API will be used to find a list of 
matching locations.  The latitude and longitude of each of those will then be displayed.  For now, though, we'll just echo back
the location entered.

For this step, I recommend that you proceed in a manner similar to Step 8 lab07b. 

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

## Step 5: Implement Location Query Service

This step will proceed in a manner similar to step 9 of lab07b.

First, Create a feature branch with an appropriate name.



In the `services` directory, create a `LocationQueryService` similar to the `EarthquakeQueryService`. 
* Your first version of it should return fake JSON, just as we did in lab7b (step 9b).
* Also add some code into the results form that displays the json, so that you know it is coming through.
* Manually test doing a location search, and making sure that when you search for "Santa Barbara" or "Goleta" then you see
  the fake json returned by the service.

Test this and do a commit.

Now we are ready for the real service.  The API we are using is documented here:
* <https://nominatim.org/release-docs/develop/api/Search/>

The api endpoint we are using has this URL syntax:
* <tt>https://nominatim.org/release-docs/develop/api/Search/<i><b>search-string</b></i>?format=json</tt>

You should do a GET request on that endpoint, putting the location entered by the user in where <tt><i><b>search-string</b></i></tt> appears.

The JSON that should come back for a query to "Santa Barbara" is shown at either of these links below:
* A snapshot of that on Github: <https://github.com/ucsb-cs56/f19/blob/master/_lab/proj01/Santa%20Barbara.json>
* The "live" raw result: <https://nominatim.openstreetmap.org/search/Santa%20Barbara?format=json>

(Note that the `%20` in the URL shown is the result of converting the space between `Santa` and `Barbara` into something escaped for a URL)

You'll see that for "Santa Barbara" we get not only Santa Barbara, California, but several other places in the world with that name.  We'll eventually show all of these to the user, and let the user choose which result they want to work with.

Test with some queries such as "Santa Barbara", "Goleta", etc. and see what comes up.  When you are satisfied that you are getting
good results, commit this, do a pull request, and merge into master.

Note that each element in the JSON returned has a `license` element. That requires us both legally and ethically to so some things to comply with the license requirements. Fortunately, these requirements are fairly easy to comply with (they are noted here: <https://www.openstreetmap.org/copyright>).   We'll take care of that in the next step. 

## Step 6: JSON to Objects for Locations

Now we are ready to parse the JSON into actual objects.  But first, let's take care of the license requirements.

Create a feature branch for the JSON parsing.

Then, go into the file 
* `src/main/resources/templates/bootstrap/bootstrap_footer.html`

You'll see that we have a `<p>` element that references CS56.  Leave that there.  But also add a second `<p>` element afterwards that reads a follows:

```html
 <p>
   Location data from the 
   <a href="https://nominatim.org/release-docs/develop/api/Overview/">Nominatum API</a> 
   is © OpenStreetMap contributors, and used under 
   <a href="https://www.openstreetmap.org/copyright">these license and copyright terms.</a>
  </p>
```

This should now appear at the bottom of each page in the application.  **I am not a lawyer**, but from my plain reading of the copyright notice linked to there, this appears to be a sufficient good faith effort to comply with the licensing terms.

While we are at it, even though it doesn't appear to be legally required, it is probably both good ethical practice and documentation
to add this additional paragraph:

```html
   <p>
        Earthquake data is from the <a href="https://earthquake.usgs.gov/fdsnws/event/1/">USGS API</a>.
   </p>
```

With that dealt with, now lets turn our attention to deserializing the JSON.  This will be similar to step 10 of lab07b.

Just as we created a separate package `geojson` for the objects that deserialized the earthquake data, let's create a package `osm` (for "Open Street Map") for the objects that deserialize the Open Street Map data.  That's a new directory under <tt>src/main/java/{{page.num}}</tt>.

Note that the object returned is an array of objects at the very top.  As a result, we'll need to map that to a `List<>`, which is a bit different from how we dealt with deserializing the JSON for the Earthquake Search Results object, where the top level JSON object was indeed an plain old object.

So, our first class will be an object (Java Bean) that represents one element of this array, e.g. an object such as:

```json
 {
        "place_id": 198134367,
        "licence": "Data © OpenStreetMap contributors, ODbL 1.0. https://osm.org/copyright",
        "osm_type": "relation",
        "osm_id": 112224,
        "boundingbox": [
            "34.336029",
            "34.463922",
            "-119.859791",
            "-119.639946"
        ],
        "lat": "34.4221319",
        "lon": "-119.7026673",
        "display_name": "Santa Barbara, Santa Barbara County, California, United States",
        "class": "place",
        "type": "city",
        "importance": 0.7840479382888761,
        "icon": "https://nominatim.openstreetmap.org/images/mapicons/poi_place_city.p.20.png"
    },
```

The only fields that we'll need for now are `place_id`, `lat`, `lon`, `display_name` and `type`.  Accordingly, we won't need more than one object.  We can call the class `Place`, taking a cue from the `place_id` field. 

The field `place_id` appears to be numeric.  We could use `String` anyway, but I'd suggest using a Java `long` since the number might exceed the capacity of a regular java `int`, but is unlikely to exceed that of a `long`.   For `lat` and `lon`, it appears that `double` is appropriate.

For deserializing, for Earthquakes, we put a `fromJSON` method in our top level object that took json as a `String`, and returned a `FeatureCollection`  

To deal with the fact that our top level JSON object is a `List<Place>` rather than a `Place`, we'll need to get some slightly different code from that we used for deseralizing the Earthquake JSON into a `FeatureCollection`.

In the `Place.java` class, make a method with this signature:
```java
  public static List<Place> listFromJson(String json)
```

The `listFromJSON` method will be SIMILAR to the `public static FeatureCollection fromJSON(String json)` method of `FeatureCollection`, 
BUT you'll need the trick from this Stack Overflow answer to make the `readValue` method of `ObjectMapper` return a
`List<Place>` instead of a `Place`
* <https://stackoverflow.com/a/6349488>

We now have code that deserializes.  We now need to put a call to that code in the appropriate endpoint in the `LocationController`, along with code that adds the `locations` as an attribute in the model, along with code that allows us to display locations in the view.

When we are done, we should be able to see a table in the view after putting in `Santa Barbara` as our search query that looks 
something like this (the formatting may be slightly different, but the structure of the table should be similar):

| Place Id | Latitude | Longitude | Display Name | City | 
|-|-|-|-|-|
| 198134367 | 34.4221319 | -119.7026673 | Santa Barbara, Santa Barbara County, California, United States | city |
| 198096124 | 34.7136533 | -119.9858232 | Santa Barbara County, California, United States | administrative | 
| 198409263 | -19.959444 | -43.415278   | Santa Bárbara, Microrregião Itabira, Mesorregião Metropolitana de Belo Horizonte, Minas Gerais, Southeast Region, Brazil | administrative |  
| 198048004 | -11.9543757 | -38.9736385 | Santa Bárbara, Microrregião de Feira de Santana, Região Metropolitana de Feira de Santana, Mesorregião do Centro-Norte baiano, Bahia, Northeast Region, Brazil | administrative | 
| 198106447 | 5.87213125| -75.5777766103402 | Santa Bárbara, Suroeste, Antioquia Department, Colombia | administrative | 
| 199345766 | 15.0944971 | -88.3723217829678 | Santa Bárbara, Honduras |administrative| 
| 184478708 | 42.8268225 | -2.3675025 | Santa Barbara, Agurain/Salvatierra, Arabako lautada/Llanada Alavesa, Álava, Basque Country, 01207, Spain | stream | 
| 186194797 | 42.8475616 | -2.3854288 | stream | 
| 199351520 | 10.0824578" | -84.1462968555599 | Cantón Santa Bárbara, Heredia Province, Costa Rica | administrative |
| 198656652 | 0.60369345 | -77.5194447232037 | Santa Bárbara, Sucumbíos, Sucumbíos Province, Ecuador | administrative |
{:.table}

At this point, you can take out the part of the view that displays the JSON on the results page, just as we did in Step 10i.

When you have the ability to type in locations and see them displayed, and the page looks nice and clean, commit these changes,
do a pull request, and merge them into master.

Then, deploy the master branch to Heroku, and make sure everything works there too.  If so, you are done with part 1 of the project.


# Final Steps

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
repo name: https://github.com/chrislee123/spring-boot-minimal-webapp<br>
on heroku: https://cs56-{{site.qxx}}-{{page.num}}-chrislee123.herokuapp.com<br>
</div>

Then, **and this is super important**, please make both of those URLs **clickable urls**.

The instructions for doing so are here: <https://ucsb-cs56.github.io/topics/gauchospace_clickable_urls/>


# Grading Rubric:

TBA





