---
assigned: 2020-02-26 12:30
assigned2: 2020-02-26 12:30
desc: Individual Lab Track Project, part 1
due: 2020-03-04 23:59
due2: 2020-03-04 23:59
gauchospace_url: https://gauchospace.ucsb.edu/courses/mod/assign/view.php?id=3261011
github_org: ucsb-cs56-w20
layout: lab
num: proj01
org: ucsb-cs56-w20
package: earthquakes
ready: true
prev_lab: lab07
---

<div style="display:none" >
Look here for formatted version: http://ucsb-cs56.github.io/w20/labWIP/proj01
</div>


<div style="display:none" >
Look here for formatted version: http://ucsb-cs56.github.io/w20/lab/proj01
</div>


# This project is only for the individual lab track

This project is only for those on the "individual lab" track.  It counts towards the project portion of your grade (20% of your final grade, rather than the lab component of your grade.)

You may cooperate with one or more pair partners from your team to help in debugging and understanding the project, but each person should complete the work separately for themselves.

Late submissions will be accepted only up until grading is complete; submissions received after grading is complete will NOT be accepted for regrades.   To ensure that your submission is accepted, be sure that you complete it and submit on Gradescope before the published deadline.


# Goals

The goal of this project is:
* determine whether you can apply the skills covered in {{page.prev_lab}} in a new context
* learn some additional skills, and also see if you can apply those in a new context as well.

We'll add the ability for the user to search for earthquakes at some distance from any location, not just the hardcoded latitude and longitude of the UCSB campus.

In Part 1 of this project, we will:
* Start with your <tt>{{page.prev_lab}}</tt> code, deploying it to a new Heroku instance
* Do some refactoring to get the code base ready for a larger application
* Add a form where you the user can enter a location
* Add interaction with a separate API that looks up latitude and longitude of locations (also known as "geocoding").
* Add the ability to list all of the matching locations returned by the API.

A working version of the app you'll build in this stage can be seen here:
* TODO: BUILD AND DEPLOY THIS <https://cs56-w20-staff-proj01.herokuapp.com/>

In later steps of the project, we'll:
* Add the ability to look up Earthquakes from any of those locations.
* Add a database table for locations, and the ability to store locations in that table

# Step by step instructions

## Step 1:  Create a private `project-githubid` repo

* Create a repo in the <tt>{{page.org}}</tt> organization with the name <tt>project-<i>githubid</i></tt>, substituting
your github id in place of <tt><i>githubid</i></tt>
* Add a remote that points to your <tt>{{page.prev_lab}}</tt> code.
* Pull in the <tt>{{page.prev_lab}}</tt> code as your starter code.
* Push to `origin master`
* Set up GitHub Pages on your repo, and generate javadoc and jacoco report 

As a reminder, that last part goes like this:
   
```
mvn clean
mvn javadoc:javadoc
mvn javadoc:test-javadoc
mvn test
mvn site
mvn site:deploy
git add docs
git commit -m "xx - update javadoc and jacoco report"
git push origin master
```

## Step 2: Set up new project on Heroku

* Create a Heroku app with the name <tt>cs56-w20-project-<i>github</i></tt>.
* Provision that Heroku app with a Heroku Postgres database.
* Configure this a Google OAuth client-id/client-secret for this Heroku app. 
   * Note that you won't be able to reuse the client-id/client-secret from previous Heroku apps, because
     the URL is different.
   * Remember that you'll need to copy `heroku.json.SAMPLE` to `heroku.json`, put in the values, and then
     run:

     ```
     setHerokuEnv.py --app APPNAME
     ```

     to load those values into Heroku in the `SPRING_APPLICATION_JSON` environment variable.
 * Deploy your master branch to your Heroku app and make sure that is loads and operates correctly.

Now, add links to your Heroku App, your app's GitHub pages home page, into your README.md.
* You may make that change directly on the master branch.


## Step 3: Add a location search form to your app

In this step, you'll add a location search to your app.

The user story for this feature is:

* As a user, <br/>
  I want to be able to search for a location and find it's latitude and longitude <br/>
  So that I can eventually search for Earthquakes close to anyplace (not just the hardcoded lat/long of UCSB).

We'll add another form with just one field: one where the user can enter a place name (location).   

There will be a search button.   Eventually, when that button is clicked, a location API will be used to find a list of 
matching locations.  The latitude and longitude of each of those will then be displayed.  For now, though, we'll just echo back
the location entered.

For this step, I recommend that you proceed in a manner similar to <s>Step 8 of lab07b</s><span>Steps 7 and 8 of lab07</span>. 

1. Create a feature branch off of master with an appropriate name.
2. In `src/main/resources/templates/` create a directory called `locations`.  
   * In it, create a form for your location search (similar to the form you created in <s>Step 8b of lab07b</s><span>Steps 7 and 8 of lab07</span>), called `search.html`.  You can use `earthquakes/search.html` as your example to follow.
   * It will have just one field on it called `location`.
   * I'm not going to walk you through every change that you'll need to make to the form.  Go through it line by line, and make the changes that seem obvious. 
   * I will however, give you two hints: 
      * For `location`, you should use: `input type="text"`, not `input type="number"` 
      * For `th:object` you specify the name of the instance of a Java Bean.  If you look ahead to the next step, you'll get a clue.
3. In the `searches` directory, create a bean called `LocSearch` similar to the `EqSearch` bean we created in lab07.
   * The object represented by this class will have only one field, a `String` field called `location`.
4. In the `controllers` directory, create a new controller called `LocationsController.java`.  
   You can use `EarthquakesController.java` as an example to follow.  

   As for what code goes in this controller, my advice to you is to look both at the code you have for the `EarthquakesController`,
   as well as the code that you used to *start out* the `EarthquakesController` in lab07.   That code started much
   simpler at first, and we added to it a bit a time.   

   At first, you'll only want a controller method that routes to your search form, just as in lab07.
  
5. Add a menu item called "Locations" that routes to the form, just like you did in lab07.  

 
   Add it between Earthquake Search and Users, so that your menu items look like this:

   ```
           <li class="nav-item">
                <a class="nav-link" href="/earthquakes/search">Earthquake Search</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/locations/search">Locations</a>
            </li>
            <li th:if="${isAdmin}" class="nav-item ">
                <a class="nav-link" href="/users" id="navbarDropdown" role="button">
                    Users
                </a>
            </li>  
   ```

   Try clicking on the new menu item and see whether it works properly.

6. Now add tests in `src/test/java/earthquakes/HomePageTest.java` that tests check whether these menu items are there.  Here is
   one example of such a test:
  
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

   You should have three test methods like this in `HomePageTest.java`, one for each of the three menu items.
   
   In case you are wondering whether this testing is very superficial, the answer is **yes, of course**.  So far, we have
   only written tests that show whether a link to some page exists.  We haven't even started to test whether the link does
   what it is supposed to do when you click on it!

   If time
   permits, we'll get into deeper testing of the functionality of our web application.  Be warned though that the
   deeper you go in, the more complicated it gets.   So, having a bit of comfort and 
   experience with some of these superficial tests
   is helpful as the testing gets more challenging.  That's why I'm making you get comfortable with these tests now,
   even though they may seem superficial.

7. Now add a route for the results, and a view for the results (steps 8f and 8g) of lab7b, but as appropriate for 
   your location search.  The view will initially just echo back the location we entered.   Later we'll add the results after
   we have a service that looks up the JSON.   

   Note that the advice for `results.html` from steps 8f and 8g of lab07b is likely
   more useful as a model than the current code in `earthquakes/results.html` at this stage.  


Make sure that:
* `mvn test` still works
* that `mvn -P localhost spring-boot:run` still works, for all your major functions.

TIPS:
* If you get the supremely unhelpful error message: 
  ```
  2019-11-23 18:05:09.319 ERROR 16924 --- [nio-8080-exec-6] org.thymeleaf.TemplateEngine             : [THYMELEAF][http-nio-8080-exec-6] 
  Exception processing template "locations/search": An error happened during template parsing 
  (template: "class path resource [templates/locations/search.html]")
  ```
  then you should **start** with the `locations/search.html` file.   But, don't only look there.  Remember that `locations/search.html` is the **view** that goes along with the controller `controllers/LocationsController.java` and the **model** `searchs/LocSearch`.   If the model, view and controller are not all in sync, then bad stuff happens when you try to render the view.   
* A common source of errors is using one model/view/controller trio (e.g. the ones for earthquakes) as a starting point, and then forgetting to change part of it as you go through.   For instance, mine didn't work the first time because I had `EqSearch locSearch` as one of my parameters to a controller method in my `LocationsController` class.  This will *compile* just fine, but it certainly won't run.   So watch out for stuff such as that.


Once you've tested this all thoroughly, this is enough for a pull request, just like it was in lab07.   

Do a pull request, merge it into master, and deploy it to Heroku and test there.

## Step 4: Implement a Location Query Service

This step will proceed in a manner similar to adding the EarthquakeQueryService in lab07.


## Step 4a: Fake Location Query Service

First, Create a feature branch with an appropriate name.

As in lab7b, it's a really good idea to create a "fake" location query service first, in order to be sure that you've "wired everything up correctly", i.e. that the data flows from one object to the next, and that the web page returns the results you expect.

Then, when you go to focus on the part where you try to get results from the real API, you are focusing on only *one* thing, and have only a *single* reason why the app might not work. You'll have already ruled out any other problems (for the most part.)

So: in the `services` directory, create a `LocationQueryService` similar to the `EarthquakeQueryService`. 

Your first version of it should return fake JSON, just as we did in lab07.

* You may want to just comment out all of the code inside the body of the `getJSON` method for now.
* The parameters should not be `int distance, int minmag`.   What should the parameter(s) be?  That's up to you to figure out.
  * Hint: Think about what this service is supposed to do.  We pass in the data the user provided in the location search form,
    pass that data to an API, the looks up information, and get back answers formatted as JSON, which we display on the results
    page.   
* As a reminder, the original "stub" version of `getJSON` in `EarthquakeQueryService` had this in the body of the method when
  we first wrote it in lab07.  I recommend exactly the same starting point here:

  ```java
        String fakeJson = "{ \"key\": \"value\" }";
        String json = fakeJson;
        logger.info("json=" + json);
        return json;
  ```

* Also add some code into the results form that displays the json, so that you know it is coming through.
  * This commit from a F19 lab shows how we did this for earthquake searches: <https://github.com/ucsb-cs56-f19/STAFF-lab07-dev-WIP/pull/3/commits/4581f91f90cd03ef76a95970b55f2e2fb78d6461> 
  * You need to do something parallel to that for location searches.
* Once you've made these changes, manually test doing a location search, and making sure that when you search for "Santa Barbara" or "Goleta" then you see the fake json returned by the service.

Test this and do a commit.

## Step 4b: Real Location Query Service

Now we are ready for the real service.  This just involves replacing the fake code inside the `getJSON` method of `LocationQueryService`
with real code that gets location information from an API.

There are many geolocation APIs, with one of the most well known being the one associated with Google Maps.  However, most of these cost money and require registration.  We are using one that requires no registration, and is free for reasonable, non-commerical usage such as our educational app.

The API we are using is documented here, and is part of the Open Street Map project:
* <https://nominatim.org/release-docs/develop/api/Search/>

The api endpoint we are using has this URL syntax:
* <tt>https://nominatim.openstreetmap.org/search/<i><b>search-string</b></i>?format=json</tt>

Examples:
* <https://nominatim.openstreetmap.org/search/Santa%20Barbara?format=json>
* <https://nominatim.openstreetmap.org/search/Golden%20Gate%20Bridge?format=json>
* <https://nominatim.openstreetmap.org/search/San%20Diego%20Zoo?format=json>
* <https://nominatim.openstreetmap.org/search/Staples%20Center?format=json>
* <https://nominatim.openstreetmap.org/search/Badaling%20Great%20Wall?format=json>
* <https://nominatim.openstreetmap.org/search/北京?format=json>
* <https://nominatim.openstreetmap.org/search/臺北市?format=json>


Your code should do a GET request on that endpoint, putting the `location` entered by the user in where <tt><i><b>search-string</b></i></tt> appears.

The JSON that should come back for a query to "Santa Barbara" is shown at either of these links below:
* A snapshot of that on Github: <https://github.com/ucsb-cs56/f19/blob/master/_lab/proj01/Santa%20Barbara.json>
* The "live" raw result: <https://nominatim.openstreetmap.org/search/Santa%20Barbara?format=json>


You'll see that for "Santa Barbara" we get not only Santa Barbara, California, but several other places in the world with that name.  We'll eventually show all of these to the user, and let the user choose which result they want to work with.

**Notes about URL escaping:** 
* Note that the `%20` in the URL shown is the result of converting the space between `Santa` and `Barbara` into 
  something   escaped for a URL
* One way to do this is with the following code.  Some versions of this code may use a different way of accomplishing
  this, using a special plus symbol instead of `%20`:

  This method comes from [this web page](https://www.baeldung.com/java-url-encoding-decoding).
  ```java
  private String encodeValue(String value) {
    return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
  }
  ```
  You'll need imports for `URLEncoder` and `StandardCharsets`; you can look these up yourself.

With this information you should be able to adapt the code from the `EarthquakeQueryService` to the code you need for the `LocationQueryService.   If you need more information about the `RestTemplate` object, you can start here: <https://ucsb-cs56.github.io/topics/spring_boot_resttemplate/>.

Once you've implemented the service, test it with some queries such as "Santa Barbara", "Goleta", etc. and see what comes up.  When you are satisfied that you are getting
good results for the JSON returned, commit this, do a pull request, and merge into master.

Note that each element in the JSON returned has a `license` element. That requires us both legally and ethically to so some things to comply with the license requirements. Fortunately, these requirements are fairly easy to comply with (they are noted here: <https://www.openstreetmap.org/copyright>).   We'll take care of that in the next step. 

## Step 5: Licensing, and JSON to Objects for Locations

Now we are ready to parse the JSON into actual objects.  But first, let's take care of the license requirements.


### Step 5a: Licensing Footer

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

Do a commit with this.

### Step 5b: JSON to Objects for Locations

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

For the starting point code of `src/main/java/earthquakes/osm/Place.java` I suggest starting with the code for `src/main/java/earthquakes/geojson/FeatureCollection.java`, because we'll need code very similar to the `fromJSON` method, and many of the same imports for dealing with conversion from JSON to Java Objects.

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

That code will require this import:

```
import com.fasterxml.jackson.core.type.TypeReference;
```

Once you've written the `listFromJSON` method, you have code that deserializes.  We now need to put a call to that code in the appropriate endpoint in the `LocationController`, along with code that adds the `List<Places>` value as an attribute in the model, along with code that allows us to display locations in the view.

When we are done, we should be able to see a table in the view similar to the one that comes up here in the working version of the staff's implementation:

* <https://cs56-f19-staff-proj01.herokuapp.com/locations/results?location=Santa+Barbara>


### Step 5c: Remove temporary raw JSON display from the view

If you get those results, then at this point, you should take out the part of the view that displays the raw JSON on the results page, just as we did in Step 10i.  It was only there as temporary scaffolding to help us make sure things were working as we built out the real table of results.

When you have the ability to type in locations and see them displayed, and the page looks nice and clean, commit these changes,
do a pull request, and merge them into master.

Then, deploy the master branch to Heroku, and make sure everything works there too.  If so, you are done with part 1 of the project.


# Final Steps

## Final Step 0: A few things to check

1. Look over the staff's working version here:

   * <https://cs56-f19-staff-proj01.herokuapp.com/>
   
   Compare it to your working version on Heroku.  If you see differences, try to determine which of these is true:
   * Is is a minor difference that doesn't matter?
   * Did the staff miss part of the instructions?
   * Did you miss part of the instructions?
   
   If you aren't sure, ask questions on Slack.

2. Check that every instance of `Logger` (such as the one below) passes the correct class name to the `getLogger` factory
   method.  
   ```
   private Logger logger = LoggerFactory.getLogger(LocationQueryService.class);
   ```
   To check this, you can use this search from the main directory of your repo:

   ```
   grep -r src LoggerFactory.getLogger
   ```
   This should show you every line of code that invokes `LoggerFactory.getLogger` under `src`.  For each, you can check the the 
   filename reported by `grep` matches the name of the class passed to the constructor.

   Here is an example of what you *should* see, where the filename `FeatureCollection.java` matches the parameter of 
   `getLogger`, i.e.   `FeatureCollection.class`

   ```
   src/main/java/earthquakes/geojson/FeatureCollection.java:    
   private static Logger logger = LoggerFactory.getLogger(FeatureCollection.class);
   ```

   Here is an example of what you *not* see, where the filename `LocationQueryService.java` does *not* match the parameter 
   of `getLogger`, i.e. `EarthquakeQueryService.class`

   ```
   src/main/java/earthquakes/services/LocationQueryService.java:    
   private Logger logger = LoggerFactory.getLogger(EarthquakeQueryService.class);
   ```

   If you see any of these mismatches, fix them.

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

| item | points | description |
|-|-|-|
| (a) | 20 | there is a Locations search item that takes you to a form where you can enter searches |
| (b) | 20 | search results are returned for locations |
| (c) | 20 | Cleanup: the footer has the licensing information, the raw json is no longer there, links to javadoc work |
| (d) | 40 | tests pass, and there are tests for whether Earthquake Search, Locations and Users are in the nav header |




