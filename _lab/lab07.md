---
assigned: 2020-02-12 17:00
assigned2: 2020-02-11 17:00
desc: "Spring Boot Skills"
due: 2020-02-20 23:59
due2: 2020-02-20 23:59
gauchospace_url: https://gauchospace.ucsb.edu/courses/mod/assign/view.php?id=3201596
github_org: ucsb-cs56-w20
labnum: lab07
layout: lab
num: lab07
org: ucsb-cs56-w20
prev: lab06
ready: true
starter: https://github.com/ucsb-cs56-w20/STARTER_lab07

---

<div style="display:none" >
   <p>Look here for formatted version: http://ucsb-cs56.github.io/w20/lab/lab07</p>
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

# Step by step instructions (lab07a)

## Step 1: New empty <s>public</s> private repo

We will start with a new, empty, <s>public</s> private repo called:

* <tt>{{page.labnum}}-githubid</tt>.

<s>We are making the repo public so that we can set up Travis-CI; the
free tier requires the repo to be public.</s>

No, just kidding!  Now that we are using GitHub Actions as our CI system instead of Travis-CI, all our repos can be private.

## Step 2. Add remote for starter code

Now add a remote for the starer code.

<tt markdown="1">git remote add starter {{page.starter}}</tt>

Then, pull the starter code into your repo, and push it back to master:

```
git pull starter master
git push origin master
```

Check that you see your code on github under the  repo name <tt>{{page.labnum}}-githubid</tt> on GitHub.


## Step 3: Deploy on Localhost

Now, we'll get the app running on localhost.


To get it running on localhost:
1. Copy from `localhost.json.SAMPLE` to `localhost.json`.   
2. Follow the instructions in the README.md to setup OAuth for Google, and put the correct values into the
   `localhost.json` for client-id and client-secret.
3. Type `source env.sh`
4.  Type `mvn spring-boot:run` and see if you can access the web app, login, and logout.

## Step 4:  Add yourself as an admin in `application.properties`

As shown in lecture, the file `src/main/resources/application.properties` has this line of code:

```
app.admin.emails=phtcon@ucsb.edu,scottpchow@ucsb.edu,zsisco@ucsb.edu,pingyuan@ucsb.edu
```

This is a comma-separated list of emails of users that are designated as admins in the app.  Add your email to this list (leaving the emails for your instructor and TAs intact).

Then restart the app, login again, and see if you are now an admin user.  You should be able to see the Admin and User menus.

Commit this change with an appropriate commit message, e.g.

```
git commit -m "cg - added my email to app.admin.emails in application.properties"
```

Push this change to the master branch.

## Step 5: Running on Heroku

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


## Step 6: Create a Feature Branch: `xxCreateForm`

We'll now create a feature branch for creating a form.

Before we create the branch, we need to be sure we are working 
with a fresh copy of master.  So do:

```
git checkout master
git pull origin master
```

Then, to create a feature branch, type this command, substituting your initials in place of `xx`.  `xx` should be your actual initials, not literally `xx`, unless your name
is, for example, "Xavier Xie".)

```
git checkout -b xxCreateForm
```

## Step 7: Create the form

On this branch, we will create a simple HTML form using Thymeleaf.

The form will have two fields in it.  It will be a form that allows the user
to specify parameters for searching for earthquakes in the last 30 days.

The two parameters will be:

* a distance in kilometers from the UCSB campus
* a minimum magnitude.  

For the UCSB campus location, we are using 34.4140°N, 119.8489°W as the latitude and longitude.
* By convention, degrees N are positive, and degrees west are negative
* So in the code, it's `34.4140, -119.8489`
* That probably shouldn't be hard coded, but that can be a refactor for another time.

Under `src/main/resources/templates` make a folder called `earthquakes` so that you have:
`src/main/resources/templates/earthquakes`.

* Hint: At command line, you can do this with a single command:
  ```
  mkdir -p src/main/resources/templates/earthquakes
  ```
  The `-p` flag means *path* and it makes the whole path at once.

Copy the file `src/main/resources/templates/index.html` to a file under `src/main/resources/templates/earthquakes` called `search.html`.

In `search.html`

Find the part of the page that reads like this:

```html
 <p>This is the home page placeholder.</p>
```

Replace it with this code, which is a heading and a Thymeleaf form:

```html

    <h1>Earthquake Search</h1>

    <form action="#" th:action="@{/earthquakes/results}" th:object="${eqSearch}" method="get">
      <table>
        <tr class="form-group">
          <th><label for="distance" class="col-form-label">Distance (km)</label></th>
          <td><input type="number" th:field="*{distance}" class="form-control" id="distance"></td>
        </tr>
        <tr class="form-group">
          <th><label for="minmag" class="col-form-label">Minimum Magnitude</label></th>
          <td><input type="number" th:field="*{minmag}" class="form-control" id="minmag"></td>
        </tr>
      </table>

      <input type="submit" class="btn btn-primary" value="Search">
    </form>
```

## Step 8: Making the form work

To be able to use this form we need:

* a Java Bean that corresponds to this form 
* a Controller to route to this form
* a menu item to get to the form

### Step 8a: Add a bean that corresponds to the form

Thymeleaf and Spring Boot work with Java Beans to move form information around.  So we'll need to make a "Java Bean" class
that corresponds to the information in our form.

Make a new directory called `src/main/java/edu/ucsb/cs56/w20/lab07/formbeans`
* Hint: remember the `mkdir -p` command from earlier

In that directory, create a Java class called `EqSearch.java`.

At the top, the package should be:

```
package edu.ucsb.cs56.w20.lab07.formbeans;
```

It should be a plain old Java class with these private data members:

```java
 private int distance;
 private int minmag;
```

It should also have a no-arg constructor, and getters and setters for each of these fields.

That makes it a "Java Bean".

Note that the Java Bean naming standards are very strict. With names `distance` and `minmag`:

* The getters must be `public int` and named: `getDistance` and `getMinmag`
* The setters must be `public void` and named `setDistance` and `setMinmag`

If you vary from this, you'll have to be sure that the Thymeleaf code is changed accoringly, so be sure that 
you follow the conventions strictly.  Everything has to match, or it just won't work.

For example, don't be tempted to use: `getMinMag` unless you are prepared to make that change everywhere in 
all of the Thymeleaf and Java code, consistently.

We won't commit this yet; let's add the controller first to make sure that the bean and the controller work together.

### Step 8b: Add a controller method for the form

In order to be able to see this form in the webapp, we need a controller method for it.

Make a new controller class called `EarthquakesController.java` in the controllers directory.

Here's the starting point:

```java
package edu.ucsb.cs56.w20.lab07.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EarthquakesController {

   
}
```

You'll want to then add this controller method:

```java
 @GetMapping("/earthquakes/search")
    public String getEarthquakesSearch(Model model, OAuth2AuthenticationToken oAuth2AuthenticationToken,
            EqSearch eqSearch) {
        return "earthquakes/search";
    }
```

This code says
* we are going to have an endpoint in the application at the web address `/earthquakes/search`
* that endpoint will use the HTTP `GET` method (rather than `POST`, for example).  
  A `GET` request is the normal way of loading
  a web page from a URL or a link.
* That endpoint takes three parameters
   * The first two are needed to support the navigation bar and login/logout; we won't worry about those for now.
   * The third one is the Java Bean that is referred to in the form, `eqsearch`.  
   * The fields of that Bean must correspond to the fields in the form (`distance` and `minmag`).
* The return value corresponds to the HTML template that we defined, without the trailing `.html`, i.e. `earthquakes/search.html` inside `src/main/resources/templates/`.

You'll need to import:
* the `EqSearch` object since it is in a different package (you should be able to figure this one out yourself).

Test this by running `mvn spring-boot:run` and by hand entering the web address <http://localhost:8080/earthquakes/search> and you should see the form.  

If this works, do a commit with an appropriate commit message.    I've suggested one below, but you can make up your own as long as it is a reasonable descrition of the code you added in this step:

```
git commit -m "xx - added form for search at url /earthquakes/search"
git push origin xxCreateForm
```

Clicking on the button on the form won't work yet; making that work is a separate step.  One step at a time.

### Step 8c: Add a menu item that routes to the form.

To make it easier to get to this form, we'll add a link to it to our navigation bar.

In the file `src/main/resources/templates/bootstrap/bootstrap_nav_header.html` you should find this code:

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

This are two `li` elements (list items), each of which:
* starts with `<li>` (the `li` open tag)
* ends with `</li>` (the `li` close tag)

In case we haven't mentioned it before: it is important to understand that an HTML element starts with an open tag, ends with a close tag, and everything in between is the elements "content".   

What you'll be doing is adding a new `li` element just before the other two that looks like this:

```html
            <li class="nav-item">
                <a class="nav-link" href="/earthquakes/search">Earthquake Search</a>
            </li>
```

This new `li` element should be placed immediately after the `<ul class="navbar-nav mr-auto mt-2 mt-lg-0">` line.

Run this, and you should see that there is now a link on the navigation bar that takes you to your page.

If so, do a commit with an appropriate commit message.  The commit message should describe what you did in this commit
(adding a form to enter information to search for earthquakes).

```
git commit -m "xx - your commit messsage goes here"
git push origin xxCreateForm
```

### Step 8d: Add a controller method for the form results.

Now we need a controller method for displaying the results.

That controller method will look like this:

```java
 @GetMapping("/earthquakes/results")
    public String getEarthquakesResults(Model model, OAuth2AuthenticationToken oAuth2AuthenticationToken,
            EqSearch eqSearch) {
        model.addAttribute("eqSearch", eqSearch);
        // TODO: Actually do the search here and add results to the model
        return "earthquakes/results";
    }
```

The additional step in this controller method is that we have this line of code:

```java
        model.addAttribute("eqSearch", eqSearch);
```

This allows us to send information to the view (i.e. to the Thymeleaf HTML file) that we can display on the results page.

For now all we are doing is echoing back the information that the user entered.  But in a later step, we will replace this comment:

```java       
// TODO: Actually do the search here and add results to the model

```

That comment will be replace with code that actually goes out to the web to get information on earthquakes.  We'll retreive that information and add it to the model.   That will make it available in the view.

### Step 8e: Add a view for the results page.

The view page `results.html` will be very similar to the page `search.html`.  Create it in the same directory,
i.e. `src/main/resources/templates/earthquakes`.   Start by copying all of the code from `search.html`. 

Then:
* Change the `h1` element to be `Earthquake Search Results`.
* Remove the `form` element entirely.
* Replace it with this `table` element:

```html
         <table>
            <tr>
                <th>Distance (km)</th>
                <td th:text="${eqSearch.distance}"></td>
            </tr>
            <tr>
                <th>Minimum Magnitude</th>
                <td th:text="${eqSearch.minmag}"></td>
            </tr>
        </table>
```

Now try running (you should restart), clicking on your `Earthquake Search` link and entering some numbers.

When you click the `Search` button, you should see the numbers echoed back to you.

If that works, we are ready for a final commit on this branch and then a pull request.

### Step 8f: Pull request

Do a pull request from your `xxCreateForm` branch into `master`.

You should then merge that pull request into master.

## Step 9: Next feature branch: `xxCallAPI`

In this step, we'll make yet another branch where we do something useful
with the information on the results page.  We'll make a call to an API
that provides information in JSON format.

In this step we'll only
echo that JSON information on the page; it won't yet be in a format
that is pleasing to an end user.   But we'll be able to see that we
are making progress.

### Step 9a: Create `xxCallAPI` branch.

To start, you need to checkout master and make a new branch.  As always, your initials, not `xx`.

```
git checkout master
git pull origin master
git checkout -b xxCallAPI
```

We have to checkout master and do `git pull origin master` to pick up the changes that were
made in the previous pull request for `xxCreateForm`.  

Do a `git log` command to be sure that you see all of those commits before proceeding.

### Step 9b: Add an `EarthquakeQueryService` that generates fake data for now.

As we discussed in lecture, a commit can be more than just a way of organizing your
changes to a project.  

* **It can also serve as documentation of how to do something in the code**
* It is a common industry practice to refer co-workers to a commit that documents a technique that they 
  will then imitate in some other part of the code.
* So Rather walk you through the changes you need to make, I will refer you to a commit that shows you the changes needed.

That commit is here:
* <https://github.com/ucsb-cs56-f19/STAFF-lab07-dev-WIP/pull/3/commits/4581f91f90cd03ef76a95970b55f2e2fb78d6461>

Note that the `package` in this commit is different (`package hello`) vs. the package in your code base, but otherwise
it is pretty much on point with what you need in your next step.

In this commit, you see several changes that need to be made:

1. There is a new file called `src/main/java/hello/EarthquakeQueryService.java`.  You should 
   create a file like this one and add it to your code base.  
  
   * HOWEVER, please create your file under `src/main/java/edu/ucsb/cs56/w20/lab07/services`
   * And, use `package edu.ucsb.cs56.w20.lab07.services` as the package.
  
2. The class `EarthquakeQueryService` is a placeholder for the 
   code that will get the Earthquake data in JSON format.  (At a later step, we'll add in the
   code that retrieves the information.)
3. There are also some changes to the controller methods.   In the original commit these
   were in the file `WebController.java` but in our code base, those will go into
  `EarthquakesController.java`.
  
   These changes 
   call out to the new `EarthquakeService` object and retrieve the information in JSON 
   format.  We store that into an attribute in the model called `json`.
4. Finally, we see that we've modified the `src/main/resources/templates/earthquakes/results.html` file
   by adding in a `<pre>` element with the `th:text` attribute.   The `th:text` attribute value
   of `"${json}"` will fill the `<pre>` element with the value of the `json` attribute in the model,
   replacing the `This is placeholder` text. 
  
Make all of these changes to your code, and then run the application.  You should see that
when you type in values in the search form, you now get a results page that shows the "fake json"
returned by the `EarthquakeQueryService` object.

Do a commit of these changes.  Use an appropriate commit message that explains what you did, and why.

Note: `"I made the changes the lab said to make"` is not what we are looking for here.

Something more like:
* `xx - Added placeholder EarthquakeQueryService and wired it up to results form`
* `xx - Added service that will eventually get earthquake data`

Describe the changes in whatever way makes sense to you, and would convey to a reader
what the purpose of the changes is.  Of course you have to understand the purpose of the
changes before you can do that.

### Step 9c: Make the `EarthquakeQueryService` retrieve real data.

The next step is to make the `EarthquakeQueryService` retrieve real data.

The way to do that is illustrated in this commit:

* <https://github.com/ucsb-cs56-f19/STAFF-lab07-dev-WIP/pull/3/commits/19029b2f00fd3a83262865b3904af5bee894f133>

Here we see that we are using the `RestTemplate` object that is built into Spring Boot to 
do an API call, and return the JSON string.    

If you would like more information on what is happening in this code, the following article
provides more details.  You are encouraged to look over that as/when you need to adapt
the code here for accessing other APIs. 

* <https://ucsb-cs56.github.io/topics/spring_boot_resttemplate/>

For now, though, just make the indicated changes.    You should see that with these changes,
when you run the application, it now retrieves JSON for earthquakes at the specified distance
and minimum magnitude, and displays that JSON in the results form.

* TIP: To get some earthquakes to show up, use 200 as the minimum distance, and 0 as the minimum magnitude.  There are almost always
  a reasonably large number of earthquakes within 200km of Isla Vista on any given day.

Commit this code with an appropriate commit message that explains what change you made and why.
Again, it should be a message that reflects an understanding of the code:

* Not Good: `"xx - made step 9c changes"`
* Better: `"xx - added code to retrieve earthquake data from USGS web service"`

### Step 9d: Pull Request

Do a pull request for this branch to master.  In the description of the pull request,
enter a brief explanation of what these two commits, *as a group*, do to improve the code
and/or the working website.

Then accept the pull request.

# Step 10: Next feature branch: `xxJavaObjects`

In this final step, we'll learn how to transform that JSON string into
usable Java objects, and use those Java objects to put useful information
on the page.


### Step 10a: Create object for the top level GeoJSON returned

Under the directory `src/main/java/`, create a subdirectory called `geojson`.

We will be putting the classes that represent the JSON objects into a package called `geojson`.

Putting them into a separate package is a way of keeping them together, and keeping the code organized.

The JSON returned is a specific format called GeoJSON, described here: <https://geojson.org/>
and in this specification: <https://tools.ietf.org/html/rfc7946>.

As explained in that specification, there are three kinds of GeoJSON objects:
* Geometry
* Feature
* Collection of Features

The JSON we get back is this last kind, a "FeatureCollection".   We'll write a plain old 
Java Object that represents this FeatureCollection.

Our job initially is to just represent the top level fields in this object that are basic data types: `int`, `String`, `boolean`, etc.   Anything that is an array or an object, we'll leave to a later stage.

We are also, (perhaps controversially), initially going to just use public data members rather than writing getters and setters for each field.  This simplifies our coding greatly.

So, the Java class will look like this:

```java
package geojson;

public class FeatureCollection {
  public String type;
}
```

That's it!   Everything else at the top level of the JSON returned is an array or an object, so
we'll leave that off for now.

We won't commit this yet.  We have a bit more code to write first.
 
### Step 10b: Write method to convert JSON to Object

When converting JSON to Java Objects, we only need to write a method for the top level object.

`FeatureCollection` will be our top level object.  So we'll add a method to the `FeatureColleciton` class to convert
the JSON representation of a Feature Collection into a `FeatureCollection` object.

We'll need to add these imports into `FeatureCollection.java`

```java
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
```

We'll also need this additional depenedency in our `pom.xml`

```xml
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-core</artifactId>
            <version>2.10.0</version>
        </dependency>

        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.10.0</version>
        </dependency>
```

We may also find it helpful to add logging into this class, so let's add the code for that.

We need these two imports:

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
```

And this declaration just inside the top of the `FeatureCollection` class.  This declaration
gets us a `logger` instance to which we can log information.   The `getLogger` method always takes
the name of the class in which it appears, followed by `.class`, so in this case `FeatureCollection.class`:

```java
    private static Logger logger = LoggerFactory.getLogger(FeatureCollection.class);
```

Now, we add a method to convert JSON into a `FeatureCollection` object.  That method looks like this:

```java
 /**
     * Create a FeatureCollection object from json representation
     * 
     * @param json String of json returned by API endpoint {@code /classes/search}
     * @return a new FeatureCollection object
     * @see <a href=
     *      "https://tools.ietf.org/html/rfc7946">https://tools.ietf.org/html/rfc7946</a>
     */
    public static FeatureCollection fromJSON(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            FeatureCollection featureCollection = objectMapper.readValue(json, FeatureCollection.class);
            return featureCollection;
        } catch (JsonProcessingException jpe) {
            logger.error("JsonProcessingException:" + jpe);
            return null;
        } catch (Exception e) {
            logger.error("Exception:" + e);
            return null;
        }
    }
```

At this point, we should be able to convert a JSON String into a FeatureCollection object.

One of the key lines of code here is this one:

```java
      objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
```

This line of code configures our `ObjectMapper` instance so that if there is a property in the JSON that doesn't match any
field in our Java object, it will just ignore that field instead of throwing an exception.   

The downside of this is that we might be missing some fields, or there could be mismatches that we don't catch.  But the advantage is that we can implement the fields in the JSON a little bit at a time, testing as we go, rather than having to do it all at once. 

### Step 10c: Use object to display results

We can now try using our `FeatureCollection` object.  It won't be very exciting because we won't yet be able to format a list of earthquakes.  But we will be able to check that all of the parts are wired up correctly.

Back in `EarthquakesController.java`, find these lines of code:

```java
        model.addAttribute("eqSearch", eqSearch);
        String json = e.getJSON(eqSearch.getDistance(), eqSearch.getMinmag());
        model.addAttribute("json", json);
        return "earthquakes/results";
```

We are going to put two more lines of code immediately before the `return`:

```java
        FeatureCollection featureCollection = FeatureCollection.fromJSON(json);
        model.addAttribute("featureCollection",featureCollection);
```

These two lines of code:
* convert the JSON into a Java object
* add that object into the model so that we can access it in the view

You will need to add an import for `FeatureCollection` since it is in a different package:

```java
import geojson.FeatureCollection;
```

To see if it worked, we need to add some code in the view that uses this.   Inside `results.html`, we'll add
this code right after the `<pre>` element where we display the JSON:

```html
        <h2> Results</h2>

        <table>
            <thead>
                <tr>
                    <th>Type</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td th:text="${featureCollection.type}"></td>
                </tr>
            </tbody>
        </table>
```

The part of this that is most important is the `<td>` element with the attribute `th:text`.

Here, we are referencing `featureCollection` which was added to the model by this code in `EarthquakesController.java`:

```java        
   model.addAttribute("featureCollection",featureCollection);
```

Because `featureCollection` is an instance of the class `FeatureCollection` and it has a public data member
called `type` (this would also work if it had a public `getType` method), we can 
use ${featureController.type} to access this value in the Thymeleaf HTML template.

Try it out and see if it works.  If it does, you should see the string `FeatureCollection` come up as the 
value of `${featureCollection.type}` when you view the results page.

The next step is to unpack the array of `Feature` objects.  But first, let's commit the code we already 
have.   

What we've done here is to set up a conversion from JSON objects to 
Java Objects, and that we've demonstrated that we can get values from it in the view.  
A good commit message would summarize that succintly.

### Step 10d: Create object for the Metadata and display it

The next step is pretty straightforward, and pretty magical.    

We just have to look at the structure of the JSON, and create Java objects that match that structure.

* Any time we see an JSON Object (i.e. a new set of `{}` with key/value pairs), we need to create a Java Object to match.
* Any time we see a JSON Array (i.e. a set of `[]` with values or object inside), we need to create a `java.util.List<>` of the appropriate object.

So, for example, in our `FeatureCollection` object, the second key in the object is `metadata`.  That key refers to an object, in particular, this one:

```json
"metadata" : {
   "generated":1573772980000,
   "url":"https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&minmagnitude=2&maxradiuskm=100&latitude=34.414000&longitude=-119.848900",
   "title":"USGS Earthquakes",
   "status":200,
   "api":"1.8.1",
   "count":29
}
```

We can create an class called `Metadata` in a file `src/main/java/geojson/Metadata.java` with the appropriate fields of the appropriate types:

```java
package geojson;

public class Metadata {
  public long generated; // need a long for very large ints
  public String url;
  public String title;
  public int status;
  public String api;
  public int count;
}
```

Once we've done that, we can add this field to the `FeatureCollection` class:

```java
   public Metadata metadata;
```

With that, we should be able to add these new fields to the table in our view.  In this example,
I've added only two of them, but you can see how we could add the rest.  

The `<th>` elements are table header elements, and just contain the column headings.  The `<td>` elements
are table data elements and contain the actual data.

```html
        <table>
            <thead>
                <tr>
                    <th>Type</th>
                    <th>Url</th>
                    <th>Count</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td th:text="${featureCollection.type}"></td>
                    <td th:text="${featureCollection.metadata.url}"></td>
                    <td th:text="${featureCollection.metadata.count}"></td>

                </tr>
            </tbody>
        </table>
```

Commit these additional changes before moving on.

### Step 10e: Handle the list of features

Let's now do the same thing for the next field in our `FeatureCollection` object, which is `features` in the 
JSON.  That field is an array of feature objects.  Each feature object has these fields (I've actually omitted
some for space, and because we really don't need them all.)

```json
{ 
  "type":"Feature",
  "properties": {
    "mag":2.5899999999999999,
    "place":"13km SSW of Lompoc, CA",
    "time":1573538207960,
    "updated":1573702694943,
    "tz":-480,
    "url":"https://earthquake.usgs.gov/earthquakes/eventpage/ci38239258",
    "detail":"https://earthquake.usgs.gov/fdsnws/event/1/query?eventid=ci38239258&format=geojson",
    ...
    [SOME FIELDS OMITTED]
    ...
    "type":"earthquake",
    "title":"M 2.6 - 13km SSW of Lompoc, CA"
  },
  "geometry": {
    "type":"Point",
    "coordinates":[-120.52800000000001,34.536333300000003,5.4400000000000004]
  },
  "id":"ci38239258"
}
```

For the Feature object, most of the interesting stuff is inside the `Properties`.  So we'll need to make a Java
object for both the Feature and the Properties.

The Properties object will look very similar to the `Metadata` object, but this time, instead of putting in 
all of the properties, we are only going to put in the ones we have an immediate use for.  Others can be added
later as they are needed.  Put this in a file `Properties.java` under the `geojson` package:

```java
package geojson;

public class Properties {
  public double mag;
  public String place;
  public String type;
  public String title;
}
```

The `Feature.java` file will be similar:

```java
package geojson;

public class Feature {
  public String type;
  public Properties properties;
  public String id;
}
```

With these in place, we can put the features array into the `FeatureCollection` class.  Add this code
to `FeatureCollection.java`:

```java
   public List<Feature> features;
```

You'll need an import for `java.util.List` in `FeatureCollection.java`.

Now, we'll use these new fields to display some results.  We'll hold off on a commit until we
see whether that works.  That's in the next step.

### Step 10f: Use those objects to display results

For this step, we'll need to know how to loop in Thymeleaf. 

We are doing to make a separate table for the list of all of the earthquake results that will loop over
each of the `Feature` objects in the `List<Feature> features` member of `featureCollection`.

Here's what that table will look like.  We'll put this table in `results.html` after all of the other ones, right *before* this line of
code:

```html
        <div th:replace="bootstrap/bootstrap_footer.html"></div>
```

That line of code, by they way is the "footer" for the page, so clearly everything else should go before it.

Here's our Earthquake table:

```html
        <h3>Earthquakes</h3>
        <table>
            <thead>
                <tr>
                    <th>id</th>
                    <th>place</th>
                    <th>mag</th>
                </tr>
            </thead>
            <tbody>
                <tr th:each="f: ${featureCollection.features}"> 
                    <td th:text="${f.id}"></td>
                    <td th:text="${f.properties.place}"></td>
                    <td th:text="${f.properties.mag}"></td>
                </tr>
            </tbody>
        </table>
```

The most important new line of code here is this one:

```
              <tr th:each="f: ${featureCollection.features}"> 
```

The `th:each` construct here is like a "foreach" loop that sets up `f` as a variable that loops over the `features` member of `featureCollection`.  The `<tr>` element in which it occurs will be repeated as many times as there are elements in the `List<Feature>` object to which `featureCollection.feature` refers.

In that way, we get a table of all of the earthquakes.

Try it out and see if it works.

If not, debug what is wrong.  If it does work, make a commit.


### Step 10g: Now you do some work: add a field

Now, see if you can add something without any instructions as to how to write the code, 
just working from what you've seen so far.

In the `table` element that contains the metadata on the `results.html` page, add another column for `title`.
This field is already in the `Metadata` java object; you just need more code in the view.

Make a commit for this that indicates that you added title to the metadata.


### Step 10h: A little more work: make id clickable

Next, in the table for the earthquakes, make the id field a clickable link.
To do this, you'll need to do two things:

* Add the `url` field from the Feature JSON in the appropriate place in the Java code.
  This field has a link to information about the earthquake.
* Replace the `<td>` element for the id field with some HTML that makes it a link.

Here is a hint.  The `<td>` element for `id` currently looks like this:

```
    <td th:text="${f.id}"></td>
```

Afterwards, it should look something like this 
(except I haven't told you what to put inside the `{    }`:

```
<td><a th:href="${    }" th:text="${f.id}"></a></td>
```

The `<a>` element makes a link.  The `th:href` is where the url goes.  The `th:text` is the
text that becomes the clickable link.

See if you can get this part working.  Once you do, make a commit with an appropriate 
message that indicates that the id is now a clickable link.

### Step 10i: Last cleanup things

A few last cleanup items:

* Remove the `<h2>` header for `JSON Results` and the `<pre> element for the JSON.  We don't need those anymore.
* In the metadata section, remove the `type` column.
* Move the title column to the first position (left most), and make it a clickable link to the url 
  (removing the url field) just like you did with the id field in the previous step.
  * Clarification: There are two tables.  One is the table with metadata, and the other is the table with one row
    per earthquake.   I'm talking here about the metadata table.  The idea is to move the "title" field to the left,
    and make it be a link that links to what the url field had in it.    The syntax is similar to what you did 
    in the table with the earthquake detail, when you made id a clickable link.
* Combine the `distance` and `minmag` fields into the same table with the metadata,
  and remove the first table.
* On the opening `<table>` tags, change them to `<table class="table">`.  This will bring in the Boostrap CSS
  that makes the tables look much nicer.

 
Do a commit for these cleanup items.  

### Step 10j: Pull Request

Finally, do a pull request from the `xxJavaObjects` branch.

Accept the pull request.  Then, you are almost done!


# Step 11: Login into Heroku and deploy your Master Branch

Login into the Heroku dashboard, find that app, and deploy your master branch from Github to that app.

Then visit the app on Heroku and make sure it works there as well.

# Step 12: Update your javadoc and jacoco report

Before updating your javadoc, pull some updates from the starter repo.  You might not see any updates if you started the lab on Wednesday, but if you started it on Tuesday, there are a few updates that fix javadoc formatting errors:

```
git pull starter master
```

Note that if you are working on a different computer from the one where you originally set up `starter` as a remote,
this won't work unless you first define `starer` as a remote, as we did back at Step 1.


<tt>git remote add starter {{page.starter}}</tt>


Then, to update your javadoc and jacoco report, do this:

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

Check that your README.md has a link to your GitHub pages webpage, and to repo, as well as your name
and your github it.   Be sure that the GitHub pages page is published.

# Final Step: Submitting your work for grading

Then, finally visit <{{page.gauchospace_url}}> and make a submission.

In the text area, enter something like this, substituting your repo name and your Heroku app name:

<div style="font-family:monospace;">
repo name: https://github.com/chrislee123/spring-boot-minimal-webapp<br>
on heroku: https://cs56-{{site.qxx}}-{{page.num}}-chrislee123.herokuapp.com<br>
</div>

Then, **and this is super important**, please make both of those URLs **clickable urls**.

The instructions for doing so are here: <https://ucsb-cs56.github.io/topics/gauchospace_clickable_urls/>


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

