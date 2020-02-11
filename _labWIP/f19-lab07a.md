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

For this step, I recommend that you proceed in this manner:

1. Create a feature branch.  Do that by typing:

   ```
   git checkout -b abLocationSearch`
   ```

   where `ab` is your initials, and `LocationSearch` describes what you are going to do 
   on the branch.  Use your own initials, not `ab` (unless your initials literally *are* `ab`).

2. In `src/main/resources/templates/` create a directory called `locations`.  
   * In it, create an HTML form for your location search `search.html`.  
   * You can find the code that you need in this file at this location: TODO FILL THIS IN.
  
3. Create a directory named `src/main/java/edu/ucsb/cs56/w20/lab07/formbeans`.

   * In that directory, put a file called `LocSearch.java`   The contents of that file can be found here; TODO FILL THIS IN.
   * The object represented by this class will have only one field, a `String` field called `location`.
   
4. In the `controllers` directory, create a new controller called `LocationsController.java`.  

   * A starting point for the code for `LocationsController.java` can be found here; TODO FILL THIS IN.
   * This code is not entirely complete; there are some parts you have to fill in.
  
5. Add a menu item called "Locations" that routes to the form.

   You do this by editing the file `/src/main/resources/templates/bootstrap_nav_header.html`
   
   Find this section:
   
   ```html
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
            <li th:if="${isAdmin}" class="nav-item ">
                <a class="nav-link" href="/users" id="navbarDropdown" role="button">
                    Users
                </a>
            </li>
            <li th:if="${isAdmin}" class="nav-item ">
                <a class="nav-link" href="/admin" id="navbarDropdown" role="button">
                    Admin
                </a>
            </li>
        </ul>
   ```

   And insert another `<li>` element in front of the other two, so it looks like this:
   
   
   ```html
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
            <li class="nav-item ">
                <a class="nav-link" href="/location" id="navbarDropdown" role="button">
                    Location Search
                </a>
            </li>
            <li th:if="${isAdmin}" class="nav-item ">
                <a class="nav-link" href="/users" id="navbarDropdown" role="button">
                    Users
                </a>
            </li>
            <li th:if="${isAdmin}" class="nav-item ">
                <a class="nav-link" href="/admin" id="navbarDropdown" role="button">
                    Admin
                </a>
            </li>
        </ul>
   ```

   Try running the application and see if the new menu item takes you to the Location Search page.

7. Now add a route for the results, and a view for the results (steps 8f and 8g) of lab7b, but as appropriate for 
   your location search.  The view will initially just echo back the location we entered.   Later we'll add the results after
   we have a service that looks up the JSON.   

   Note that the advice for `results.html` from steps 8f and 8g of lab07b is likely
   more useful as a model than the current code in `earthquakes/results.html` at this stage.  


Once you've tested this all thoroughly, this is enough for a pull request.

Do a pull request on this branch, merge it into master, and deploy it to Heroku and test there.

## Step 5: Implement a Location Query Service

We now start with a new feature branch.

When creating a new feature branch, we should typically start with a fresh copy of the master branch:

```
git checkout master
git pull origin master
```

Then, make our new feature branch, substituting your initials in place of `ab`

```
   git checkout -b abLocationQueryService
```

## Step 5a: Fake Location Query Service


It's a  good idea to create a "fake" location query service first, in order to be sure that you've "wired everything up correctly", i.e. that the data flows from one object to the next, and that the web page returns the results you expect.

Then, when you go to focus on the part where you try to get results from the real API, you are focusing on only *one* thing, and have only a *single* reason why the app might not work. You'll have already ruled out any other problems (for the most part.)

So: in the `services` directory, create a file called `LocationQueryService.java` 
containing code like the following: TODO FILL THIS IN

Your first version of it should return fake JSON.

Test this and do a commit.

## Step 5b: Real Location Query Service

Now we are ready for the real service.  This just involves replacing the fake code inside the `getJSON` method of `LocationQueryService`
with real code that gets location information from an API.

Here is the code we are going to use:  TODO INSERT CODE HERE.

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

(Note that the `%20` in the URL shown is the result of converting the space between `Santa` and `Barbara` into something escaped for a URL)

You'll see that for "Santa Barbara" we get not only Santa Barbara, California, but several other places in the world with that name.  We'll eventually show all of these to the user, and let the user choose which result they want to work with.

If you need more information about the `RestTemplate` object, you can start here: <https://ucsb-cs56.github.io/topics/spring_boot_resttemplate/>.

Once you've implemented the service, test it with some queries such as "Santa Barbara", "Goleta", etc. and see what comes up.  When you are satisfied that you are getting
good results for the JSON returned, **commit this, do a pull request, and merge into master**.

Note that each element in the JSON returned has a `license` element. That requires us both legally and ethically to so some things to comply with the license requirements. Fortunately, these requirements are fairly easy to comply with (they are noted here: <https://www.openstreetmap.org/copyright>).   We'll take care of that in the next step. 



## Step 6: Licensing, and JSON to Objects for Locations

Now we are ready to parse the JSON into actual objects.  But first, let's take care of the license requirements.

With a fresh copy of master, create a feature branch for the JSON parsing.

```
   git checkout -b abLocationJSONParsing
```

### Step 6a: Licensing Footer


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

### Step 6b: JSON to Objects for Locations

With that dealt with, now lets turn our attention to deserializing the JSON. 

Start by creating a new package, i.e. a new directory, called `src/main/java/osm`.  This will be a package for objects
that represent data from the "Open Street Map" project.


Note that the object returned is an array of objects at the very top.  As a result, we'll need to map that to a `List<>`, so
we'll create a class (a Java Bean) called `Place.java` that represents one element of this array, e.g. an object such as:

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

That code will require this import:

```
import com.fasterxml.jackson.core.type.TypeReference;
```

Once you've written the `listFromJSON` method, you have code that deserializes.  We now need to put a call to that code in the appropriate endpoint in the `LocationController`, along with code that adds the `List<Places>` value as an attribute in the model, along with code that allows us to display locations in the view.

When we are done, we should be able to see a table in the view similar to the one that comes up here in the working version of the staff's implementation:

* <https://cs56-f19-staff-proj01.herokuapp.com/locations/results?location=Santa+Barbara>


### Step 6c: Remove temporary raw JSON display from the view

If you get those results, then at this point, you should take out the part of the view that displays the raw JSON on the results page, just as we did in Step 10i.  It was only there as temporary scaffolding to help us make sure things were working as we built out the real table of results.

When you have the ability to type in locations and see them displayed, and the page looks nice and clean, commit these changes,
do a pull request, and merge them into master.

Then, deploy the master branch to Heroku, and make sure everything works there too.  If so, you are done with part 1 of the project.




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

