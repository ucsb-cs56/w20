---
layout: lab
num: lab07b
labnum: lab07
ready: true
desc: "Spring Boot Skills Part 2"
assigned: 2019-11-14 17:00
due: 2019-11-20 23:59
github_org: "ucsb-cs56-f19"
org: "ucsb-cs56-f19"
gauchospace_url: "https://gauchospace.ucsb.edu/courses/mod/assign/view.php?id=TBD"
prev: lab07a
starter: 
---

<div style="display:none" >
Look here for formatted version: http://ucsb-cs56.github.io/f19/lab/lab07b
</div>

This lab builds on your work from {{page.prev}}.

# What if I didn't finish {{page.prev}}

If you were not successful in completing {{page.prev}}, you should go
back and complete any unfinished steps from {{page.prev}} first.  You
will get partial credit for this lab simply for doing the unfinished
steps from {{page.prev}}, even if you missed the deadline for
{{page.prev}}

# Individual lab

This is an **individual** lab on the topic of Java web apps on Heroku.

You may cooperate with one or more pair partners from your team to help in debugging and understanding the lab, but each person should complete the lab separately for themselves.


# Goals

See Goals section of  [lab07a](lab07a).

# Picking up from step 5 of [lab07a](lab07a).

Please return to your same repo:

* <tt>{{page.labnum}}-githubid</tt>.

We will work with this repo, and with the Heroku app you configured for {{lab.prev}}

   
# Step by step instructions


## Step 6: Set up repo for Travis-CI

We'll start this lab by setting up our repo on Travis-CI.

Setting up our repo on Travis-CI will set it up for "continuous integration"&mdash;that's what CI stands for.

Continuous integration means that we try to integrate new code into the code base early and often, and that we run all of the tests of our code base each time we do that.

With Travis-CI setup, each time you push code to GitHub, or do a pull request, a server in the cloud (at the Travis-CI.org website) will pull your repo, and run all of your JUnit tests.  You'll get an indication on the GitHub site for your repo whether the tests passed or not.


### Step 6a: Add a `.travis.yml` file to your repo

To set up your repo for Travis-CI, the first step is to copy the two line file `.travis.yml` from the starter code repo <{{page.starter}}> into the root of your repo.  (For this change, we'll make an exception and just do it directly on the master branch.)

So, please copy that file in to your repo, and do:

```
git add .travis.yml
git commit -m "xx - add .travis.yml for Travis-CI"
git push origin master
```

### Step 6b: Fix unit tests so they work without OAuth

> NOTE: This step was missing in early versions of the lab.  If you are are coming back to this step, 
> it's ok to make the following changes directly on the master branch.  It's also fine to make a
> separate `xxFixTravisCI` branch and do a pull request.  Your choice.

The next step is to adjust our unit tests so that they can run without the OAuth secrets and the integration with GitHub.

When we run on Travis-CI, we don't have access to the client-id and client-secret that is defined in our `localhost.json` and `heroku.json` files.

While it is possible to set up Travis-CI to have access to those, there is a better way.  It involves "mocking" some of the parts of our application that are not being tested.

To do that, make the following changes to your code.

1. In `pom.xml`, add this dependency.  It can go anywhere 
   in the `<dependencies>` section, in between two other `<dependency>` elements:

   ```xml
     <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <version>2.22.0</version>
            <scope>test</scope>
     </dependency>
   ```

   This update `Mockito`, which is a piece of software that lets us create "Mock Objects" for testing.

2. In the file `src/test/java/hello/HomePageTest.java`, we are going to make a few changes.  Here's the first.

   First add these three imports:
   ```java
   import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
   import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
   import org.springframework.boot.test.mock.mockito.MockBean;
   ```

3. Next, find these lines of code:

   First add this import:
   ```java
   @RunWith(SpringRunner.class)
   @SpringBootTest
   @AutoConfigureMockMvc
   public class HomePageTest {
   ```

   And change them to:

   First add this import:
   ```java
   @RunWith(SpringRunner.class)
   @WebMvcTest(WebController.class)
   public class HomePageTest {
   ```
   
4. Finally, find these two lines of code:

   ```java
     @Autowired
     private MockMvc mvc;
   ```

   and immediately after, add these:

   ```java
    @MockBean
    private AuthControllerAdvice aca;

    @MockBean
    private ClientRegistrationRepository crr;
   ```

5. Next, try running your unit tests with `mvn test` in a new terminal window where you did NOT do `source env.sh`.
   If they pass, then you know you did this step correctly, and your tests should pass on Travis-CI.

6. Finally, commit all of these changes with a commit message that says something like: 

   `xx - mock dependencies for unit testing`

It's ok to commit this directly on the master branch.

NOTE: If you are doing step 6b *after* already setting up Travis-CI, now is the time to try to 
manually trigger a build and see if it gives you a green check.
   

### Step 6c: Set up repo on `travis-ci.org` website

The next step is to visit the following website, and login with your GitHub account:

<https://travis-ci.org>

Once there, at the upper left hand corner of the dashboard, you should see a small plus sign next to the text "My Repositories".  You want to click this `+` sign as shown in this image:

![Travis Dashboard + sign](travis-dashboard-click-plus-50.png)

That takes you to a page where you can add the <tt>{{page.org}}</tt> GitHub organization to your authorized organizations for Travis-CI.

You might have to scroll down the left column where the text says:

> MISSING AN ORGANIZATION? <br>
> <u>Review and add</u> your authorized organizations

On the page, the text "review and add" is a link; if you click it, you should be able to enable Travis-CI access for <tt>{{page.org}}</tt>.

Once you do that, you should be able to see the organization 
<tt>{{page.org}}</tt> in the left hand column.   If you click on it, you should then be able to see your repo, and enable it for Travis-CI by clicking the small button next to the repo name. 

It can be a bit confusing, but if you are patient with yourself and the site, you'll figure it out.  If after trying for a while, you are still having difficulty, ask a mentor, TA or the instructor for assistance.

Once you've got the repo enabled for Travis-CI, there will a web page specifically for your repo, with the url:

* <https://travis-ci.org/{{page.org}}/{{page.labnum}}-githubid>

where <tt>{{page.org}}/{{page.labnum}}-githubid</tt> is the name of your repo.   On that page, at the upper right, you should be able to find a button with the text "More Options". Click on this reveals the following menu:

![More Options Menu](travis-more-options-50.png)

Clicking on the "Trigger Build" option will bring up this pop-up:

![Trigger Build](travis-trigger-build-50.png)

Here, you can trigger a build for any branch, with the master branch being the default.  Go ahead and trigger a build for your master branch.

You should be able to see on the Travis-Ci page for your repo that the branch build successfully, all the test cases pass, and that you end up "green on master".   (The color green indicates success, i.e. that all the tests passed.)

If not, try to determine what's wrong first by checking these things:
* When you type `mvn test` locally, do all the tests pass?
* Do you have a `.travis.yml` committed on the master branch?

If you aren't able to figure out what is wrong, seek out help from a mentor, TA, or instructor.

## Step 7: First feature branch: `xxSmallUIFixes`

### Step 7a: pull from master

Now, to be sure you have the latest code (in case you changed anything on another computer, or on github), do this in your terminal before proceeding:

```
git pull origin master
```

### Step 7b: create a feature branch

We will now create a feature branch. The first two letters should be your initials, e.g. `pc`, `ab`, etc.  

The rest should be `SmallUIFixes`.  So the branch name will be something like `pcSmallUIFixes` or `abSmallUIFixes`.

Type this (but not literally `xx` unless your first and last name both start with `x`)

```
git checkout -b xxSmallUIFixes
```

### Step 7c: Write a failing test

Now on this branch, the change we want to make is to change the
text for the "brand", which is the item at the upper left hand corner
of the web page (the thing you click to get home).  

* The current text is "My-Web-App".  
* We want to change that to "lab07"

So the first thing we do is write a failing test.  The test should:
* load the home page
* find that HTML element
* assert that the contents are `"lab07"`

When we run that test, it should say that it expected `lab07` but found `My-Web-App`.

Here's how an experienced developer would write this test:
1.  Locate a similar test that already exists in the code.

    In this case, the test `getHomePage_hasCorrectTitle()` in the file
    `src/test/java/hello/HomePageTest.java` is a good candidate.
    
2.  Find the XPath expression for the HTML element 
    containing `My-Web-App`.  We can do this by right 
    clicking in the browser 
    on the text `My-Web-App` and choose "Inspect".

    In Chrome, at least, this brings up a pane where you can 
    right click on the element, and there is a menu for "Copy".
    If you go to that menu, one of the options is "Copy XPath".

    The XPath for this element happens to be:
    `/html/body/div/nav/a`.

3.  We can now copy/paste the `getHomePage_hasCorrectTitle()` test,
    rename the copied test to `getHomePage_hasCorrectBrand()`.

    We can then change the code as follows:

    From:

    ```
    .andExpect(xpath("//title").exists())
    .andExpect(xpath("//title").string("CS56 Spring Boot Practice App"));
    ```

    To:

    ```
    .andExpect(xpath("/html/body/div/nav/a").exists())
    .andExpect(xpath("/html/body/div/nav/a").string("lab07"));
    ```

This test should fail if we run it, with something like:

```
java.lang.AssertionError: XPath /html/body/div/nav/a expected:<lab07> but was:<My-Web-App>
```

### Step 7d: Make the test pass

Then, we can make the test pass by replacing the text in that element.

That text lives inside the file `src/main/resources/templates/bootstrap/bootstrap_nav_header.html`, which is where all of the HTML code for the navigation header can be found.

We can change:

```
 <a class="navbar-brand" href="/">My-Web-App</a>
```

to 

```
 <a class="navbar-brand" href="/">lab07</a>
```

After this, the test should pass.

We can then commit both the change and the test together in a single commit.   *This is professional standard practice*.    

Commit this change.

### Step 7e: Do it again

Now, we want to change the text on the first link from "Page 1" to "Earthquakes".

Write a test that fails.  Then make that test pass.

Use the same technique.  

Once you have this test passing, make another commit on the `xxSmallUIFixes` branch.

### Step 7f: Make a pull request

Now make a pull request for this branch.

When you make the pull request, if Travis-Ci is working properly,
you should see a small yellow circle that eventually turns into a green check 
on the list of commits.    

When the pull request shows that the tests have passed, 
merge the pull request into master.

# Step 8: Next feature branch: `xxCreateForm`

We'll now create a second branch for creating a form.

Before we create the branch, we need to be sure we are working 
with a fresh copy of master.  So do:

```
git checkout master
git pull origin master
```

# Step 8a: Next feature branch: `xxCreateForm`

Then create a new branch called `xxCreateForm` (as always, `xx` should be your actual initials, not literally `xx`, unless your name
is, for example, "Xavier Xie".)

# Step 8a: Create the form

On this branch, we will create a simple HTML form using Thymeleaf.

The form will have two fields in it.  It will be a form that allows the user
to specify parameters for searching for earthquakes in the last 30 days.

The two parameters will be:

* a distance in kilometers from the UCSB campus (defined for our purposes as
  Latitude 34.4140° N, Longitude 119.8489° W)
* a minimum magnitude.  

Under `src/main/resources/templates` make a folder called `earthquakes` so that you have:
`src/main/resources/templates/earthquakes`.

Copy the file `page1.html` to a file under `src/main/resources/templates/earthquakes` called `search.html`.

In `search.html`, replace this line of code:

```html
    <title>Title of your page goes here</title>
```

with this:

```html
    <title>Earthquake Search</title>
```

Find the part of the page that reads like this:

```html
<h1>Page 1</h1>

<p>This page is a placeholder.</p>
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

# Step 8b: Add a bean that corresponds to the form

Thymeleaf and Spring Boot work with Java Beans to move form information around.

So we need a Java Bean that corresponds to this form.

Create a Java class in the same directory as your other Java code called EqSearch.java.

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

Finally, be sure this `EqSearch.java` file is in the same package as the rest of your code.   That package is currently `hello`.


# Step 8c: Add a controller method for the form

In order to be able to see this form in the webapp, we need a controller method for it.

In the file `WebController.java`, add this code:

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

Test this by running `mvn spring-boot:run` and by hand entering the web address <http://localhost:8080/earthquakes/search> and you should see the form.  Clicking on it won't work yet; making that work is a separate step.  One step at a time.

# Step 8d: Add a menu item that routes to the form.

To make it easier to get to this form, we'll add a link to it to our navigation bar.

In the file `src/main/resources/templates/bootstrap/bootstrap_nav_header.html` you should find this code:

```html
            <li class="nav-item active">
                <a class="nav-link" href="/">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/page1">Page 1</a>
            </li>
```

This is two `<li>` elements (list items), each of which:
* starts with `<li>` (the `li` open tag)
* ends with `</li>` (the `li` close tag)

In case we haven't mentioned it before: it is important to understand that an HTML element starts with an open tag, ends with a close tag, and everything in between is the elements "content".   

What you'll be doing is adding a new `<li>` element in between those two, so that the code looks like this:

```html
            <li class="nav-item active">
                <a class="nav-link" href="/">Home <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/earthquakes/search">Earthquake Search</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/page1">Page 1</a>
            </li>`
```

Run this, and you should see that there is now a link on the navigation bar that takes you to your page.


# Step 8e: Add a controller method for the form results.

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

# Step 8f: Add a view for the results page.

The view page `results.html` will be very similar to the page `search.html`.  Create it in the same directory,
i.e. `src/main/resources/templates/earthquakes`.   Start by copying all of the code from `search.html`. 

Then:
* Change the `title` element and the `h1` element to be `Earthquake Search Results`.
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

If that works, we are ready to add some tests.

# Step 8g: Add tests

In this case, we wrote the code before we wrote the tests.

Ideally, you write the tests first.  But it isn't always feasible, especially when you are learning a new framework.

A thorough job of testing is a whole lab unto itself, so we'll just add a few small tests for now.   

Let me stress it again: the code here in this step is *an inadequate job of testing* the code that we've added in this step.
But it's at least a start.

First, let's add a test that makes sure that there is indeed a page at the address `/earthquakes/search` and that we can
retrieve that page without the server crashing.   To do that, we can use the following code, which we'll put into a file called `/src/test/java/hello/EarthquakeSearchTest.java`

```java
package hello;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EarthquakeSearchTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getEarthquakeSearch() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/earthquakes/search").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(xpath("//title").exists())
                .andExpect(xpath("//title").string("Earthquakes Search"));
    }           
}
```

# Step 8h: Pull request

Do a pull request from your `xxCreateForm` branch into `master`.

You should then merge that pull request into master.

# Step 9: Next feature branch: `xxCallAPI`

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

### Step 9b: Add an `EarthquakeQueryService` that generate fake data for now.

For this step, we will use the idea that a commit can be more than just a way of organizing your
changes to a project.   Rather walk you through the changes you need to make,
I will refer you to a commit that shows you the changes needed.

That commit is here:
* <https://github.com/ucsb-cs56-f19/STAFF-lab07-dev-WIP/pull/3/commits/4581f91f90cd03ef76a95970b55f2e2fb78d6461>

In this commit, you see that:
* There is a new file called `src/main/java/hello/EarthquakeQueryService.java`.  You should 
  create a file like this one and add it to your code base.  This is a placeholder for the 
  code that will get the Earthquake data in JSON format.  (At a later step, we'll add in the
  code that retrieves the information.)
* There are also some changes to `src/main/java/hello/WebController.java`. These changes 
  call out to the new `EarthquakeService` object and retrieve the information in JSON 
  format.  We store that into an attribute in the model called `json`.
* Finally, we see that we've modified the `src/main/resources/templates/earthquakes/results.html` file
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

Under the directory `src/main/java/hello`, create a subdirectory called `geojson`.

We will be putting the classes that represent the JSON objects into a package called `hello.geojson`.

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
package hello.geojson;

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

Back in `WebController.java`, find these lines of code:

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
import hello.geojson.FeatureCollection;
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

Here, we are referencing `featureCollection` which was added to the model by this code in `WebController.java`:

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

We can create an class called `Metadata` in a file `src/main/java/hello/geojson/Metadata.java` with the appropriate fields of the appropriate types:

```java
package hello.geojson;

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
later as they are needed.  Put this in a file `Properties.java` under the `hello.geojson` package:

```java
package hello.geojson;

public class Properties {
  public double mag;
  public String place;
  public String type;
  public String title;
}
```

The `Feature.java` file will be similar:

```java
package hello.geojson;

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
* Combine the `distance` and `minmag` fields into the same table with the metadata,
  and remove the first table.
* On the opening `<table>` tags, change them to `<table class="table">`.  This will bring in the Boostrap CSS
  that makes the tables look much nicer.
* Remove `Page 1` and `Page 2` from the Navigation header.  You don't have to remove the template files
  and the controller methods; just remove the links to them in the file where the navigation header is 
  defined.  (If you've forgotten how, look back at earlier steps.)
 
Do a commit for these cleanup items.  

### Step 10j: Pull Request

Finally, do a pull request from the `xxJavaObjects` branch.

Accept the pull request.  Then, you are almost done!

# Step 11: A small fix to `application.properties`

There is one final change to make (if you haven't done it already).

The GitHub login/logout is supposed to show your status in the GitHub organization <tt>{{page.org}}</tt>, either
as an `admin`, a `member` or a `guest`.   However, this doesn't work properly unless you add this line into your
`application.properties` file:

CORRECT: 
```
spring.security.oauth2.client.registration.github.scope: user,read:org
```

This should replace this incorrect line which may be there in the starter code:

INCORRECT:
```
spring.security.oauth2.client.registration.github.scope: "read:user", "read:org"
```


Add this in.  For this small change, you may just do a commit directly on the master branch.

If you'd like to understand more about what this change means, you can read more about OAuth Scopes here: <https://developer.github.com/apps/building-oauth-apps/understanding-scopes-for-oauth-apps/>.

Before you do, you should accept your previous pull request(s), and then do:

```
git checkout master
git pull origin master
```

Test it, and make sure that when you logout and login, you see `member` by your username.

You can ask a TA, mentor or instructor to try your app as well. They should see `admin` when they login.

# Step 11: Login into Heroku and deploy your Master Branch

Back in lab07a, you created a Heroku app for lab07.

Login into the Heroku dashboard, find that app, and deploy your master branch from Github to that app.

Then visit the app on Heroku and make sure it works there as well.

# Step 12: Update your javadoc and jacoco report

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

Check that your README.md has a link to your GitHub pages webpage, and that the webpage is published.

# Lucky Step 13: Go back to step 6b if you didn't do it yet

Chances are you didn't do Step 6b because it didn't exist at the time you started the lab.

Step 6b fixes the code so that the unit tests pass on Travis-CI and we get a nice green check instead of the ugly red X.

It only takes a moment.  Please go back and do that now.

# Final Step: Submitting your work for grading

When you have a running web app, visit <{{page.gauchospace_url}}> and make a submission.

In the text area, enter something like this, substituting your repo name and your Heroku app name:

<div style="font-family:monospace;">
repo name: https://github.com/chrislee123/spring-boot-minimal-webapp<br>
on heroku: https://cs56-{{site.qxx}}-{{page.num}}-chrislee123.herokuapp.com<br>
</div>

Then, **and this is super important**, please make both of those URLs **clickable urls**.

The instructions for doing so are here: <https://ucsb-cs56.github.io/topics/gauchospace_clickable_urls/>


# Grading Rubric:

TBA



