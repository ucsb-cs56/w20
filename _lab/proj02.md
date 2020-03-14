---
assigned: 2020-02-26 12:30
assigned2: 2020-02-26 12:30
desc: Individual Lab Track Project, part 2
due: 2020-03-06 23:59
due2: 2020-03-06 23:59
gauchospace_url: https://gauchospace.ucsb.edu/courses/mod/assign/view.php?id=3261021&forceview=1
github_org: ucsb-cs56-w20
layout: lab
num: proj02
org: ucsb-cs56-w20
package: earthquakes
ready: true
prev_lab: lab07
---


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

# A working version

Here are working versions of the staff's versions of the proj01 and proj02 code, so that you can see what the app is supposed to do when it is finished.  (Note that these are closed-source.  Sorry&mdash;you'll have to write your own code here.  If you need help, though,reach out on Slack, or come to open lab hours.)

TODO: FIX THESE

| Project | Working Version |
|-|-|
| [proj01]({{ '/lab/proj01' | relative_url }}) | <https://cs56-w20-staff-proj01.herokuapp.com> |
| [proj02]({{ '/lab/proj02' | relative_url }}) | <https://cs56-w20-staff-proj02.herokuapp.com> |
{:.table .table-sm .table-striped .table-bordered}


# Step by step instructions

Continue with the same repo and Heroku App that you started with in proj01:

* Github repo: <tt>project-githubid</tt>
* Heroku App:  <tt>cs56-w20-project-<i>github</i></tt>

## Step 7: Adding more query params

In this step, we are going to modify the existing Earthquake Search.  This search currentlyhas only two query params, namely:
* `distance` (distance in km from the earthquake)
* `minmag` (minimum magnitude).

The search is performed relative to the latitude and longitude of the UCSB campus.  This is because of hard coded values in the
`EarthquakeQueryService` class:

```java
     double ucsbLat = 34.4140;
     double ucsbLong = -119.8489;
```

We are going to modify the search so that the latitude, longitude, and location can be specified.   This will be a first step
towards being able to automatically link from the location search to an Earthquake search.

### Step 7a: Understanding GET request query params

We'll start by looking at your current application as it runs on localhost, or alternatively, the reference implementation
of proj1 running on heroku here:

* <https://cs56-w20-staff-proj01.herokuapp.com/>

Navigate to the page that performs an Earthquake search.
* Fill in the value 500 for distance and 2 for minimum magnitude.
* Click the Search button

You'll see that the URL that results has this structure:

* <https://cs56-w20-staff-proj01.herokuapp.com/earthquakes/results?distance=500&minmag=2>

Look at the part of the URL after the `?` and you'll see that it consists of key value pairs.
* `distance` is a key, and `500` is a value
* `minmag` is a key, and `2` is a value

These are called query parameters.  They are here because the form that we submitted to get to this URL, which can be found
in the file `src/main/resources/templates/earthquakes/search.html`, has:
* `/earthquakes/results` as the `action` parameter 
   * This is specified in Thymeleaf syntax as `th:action="@{/earthquakes/results}"`
* The form has two `input` elements with names `distance` and `minmag`
   * The first is specified by the Thymeleaf syntax: `th:field="*{distance}"` and `id="distance"`
   * The second is specified by the Thymeleaf syntax: `th:field="*{minmag}"` and `id="minmag"`

Experiment with modifying the values directly in the URL, e.g. changing `500` to `300`, and `2` to `1`.  
* You'll see that we don't actually need the form in order to get results from our results page.
* The form makes it convenient to submit these requests.  But we can actually submit requests simply by having a correctly formatted URL

If our URL allowed us to specify the latitude and longitude, as well as a location, we could build links on each our the search results
that could search for Earthquakes in those locations (though we'd have to choose default values for `distance` and `minmag`).

We'll add the ability to do that in this step.

### Step 7b: Understanding the big picture

The query params correspond to the `EqSearch` object (Java Bean) in our application.   We can add additional query params by doing the following steps.  

**Don't start coding these yet**; just read over this list and try to understand the big picture.  We'll then take
each of these steps one at a time.

For our new query params `lat`, `lon` and `location`, we will:

* Add them to the `EqSearch` object (Java Bean)
* Add them to the HTML form for the search (`src/main/resources/templates/earthquakes/search.html`)
   * Since the HTML form uses an instance of `EqSearch` as the `th:object="${eqSearch}"` parameter in the `<form>` element,
     each field in the form must correspond to a field in the `EqSearch` java bean.
* Modify the results form so that we can see the latitude, longitude and location passed in.
* Modify the `EarthquakeQueryService` to take the `lat` and lon` params, rather than using hard coded values for UCSB.
   * The `EarthquakeQueryService` doesn't need the `location` parameter, but we will want that for the display
* Modify the code inside the `EarthquakeQueryService` to use the `lat` and `lon` passed in instead of the hard coded values for UCSB.
* Modify the place that we call the `EarthquakeQueryService` (in the `EarthquakesController`) to pass the values from the form
  into the service and use them.

When all of that is done, we should be able to test and see that we are able to search for Earthquakes at any latitude and longitude.  Temporarily, we can use our location search to find the latitude and longitude of some interesting place (e.g. `Storke Tower` or `Isla Vista`) and search for Earthquakes near there.   Of course it will be inconvenient to have to write down the lat/lon, or copy/paste it from one window to the other.  So then we'll connect these services together:


* We'll use Thymeleaf's features to generate a URL that links to the earthquake search page from every location result, passing in the appropriate values for `lat`, `lon` and `location`.

Ok, to get started, create a feature branch (off of master) for adding fields to the earthquake search form. Give it a suitable name.

### Step 7c: Add fields to bean and forms

On your feature branch:

1. Add `lat`, `lon` and `location` to the `EqSearch` object (Java Bean), with appropriate data types.
2. Add these to the HTML form for the Earthquake search  (`src/main/resources/templates/earthquakes/search.html`)
   * Note that for `<input>` fields with `type="number"`, to allow arbitrary decimal points instead of just integers, you need
     to add the attribute `step="any"`
3. Modify the results view   (`src/main/resources/templates/earthquakes/results.html`) so that we can see the latitude, longitude,
   and the location as part of the results.  At this point, it is up to you to decide how to format the page.   Format it in some
   way that the results will be easy to read and understand.
   
At this point, you should be able to enter latitude, longitude, and location on the search results form, and see those values both in the URL, as well as on the results page.   At this stage, the actual search will still be using the hard coded latitude and longitude of UCSB.  Nevertheless, do a commit of these changes, with a note in the commit that indicates both what *is* and is *not* done yet.

### Step 7d: Use the fields in the service

Now we'll do a second commit

* Modify the `EarthquakeQueryService` so that the `getJSON` method takes the `lat` and `lon` params in addition to the params
  for distance and minmag.
* Modify the place that we call the `EarthquakeQueryService` (in the `EarthquakesController`) to pass the values from the form
  into the service.
  
With these two changes, the app should compile and run; make sure that it does before moving on to the final step.

When it does, you can now modify the code inside the `getJSON` method of `EarthquakeQueryService` so that the `lat` and `lon` values
passed into `getJSON` are actually used to do the search.

Test this. When it works, you should do another commit, and then a pull request.  Merge the pull request into master.  

### Step 7f: A link on the Earthquake results

Now, in each row of the table for `Place` values, add another column.

* Add a table header (`<th>`) element in the row for the table headers with the label 'Get Earthquakes'
* Add a table data (`<td>`) element in the row for the table data.  
* Inside this `<td>` element, you'll want a link to the earthquake data.  

That link will look like this:

```html
  <a th:href="@{/earthquakes/search/(lat=${p.lat},lon=${p.lon},location=${p.display_name})}">Get Earthquakes</a>
```

In case you are wondering, the syntax of this link comes from the the Thymeleaf Tutorial, [Section 4.4 on Link URLs](https://www.thymeleaf.org/doc/tutorials/2.1/usingthymeleaf.html#link-urls).  This generates a link that is
* automatically prefixed with the correct root (e.g. `http://localhost:8080`, or `https://cs56-w20-project-githubid.herokuapp.com`)
* has the appropriate query params appended (all of the key values pairs inside the parentheses), in the correct syntax for URLs.

To test this out, try doing a search on a location.  You should find that there is a link in each row of the location results that takes you to the earthquakes search page, where you can then do an earthquakes search on that latitude, and longitude, and location.  You'll still have to specify the distance and the minimum magnitude on that page.

If/when it works, do a commit, and then do a pull request and merge it.


# Final Steps

## Final Step 0: A few things to check

1. Look over the staff's working version here:

   TODO: FIX THIS

   * <https://cs56-w20-proj02-pconrad.herokuapp.com/>
   
   Compare it to your working version on Heroku.  If you see differences, try to determine which of these is true:
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
repo name: https://github.com/chrislee123/project-githubid<br>
on heroku: https://cs56-{{site.qxx}}-project-chrislee123.herokuapp.com<br>
</div>


Then, **and this is super important**, please make both of those URLs **clickable urls**.

The instructions for doing so are here: <https://ucsb-cs56.github.io/topics/gauchospace_clickable_urls/>


# Grading Rubric:

# Grading Rubric:

| item | points | description |
|-|-|-|
| (a) | 40 | The Earthquakes page has query params for latitude, longitude, and location |
| (b) | 40 | You can click on "Get Earthquakes" and get earthquakes for a location result |
| (c) | 20 | README.md has links to github pages webpage, app on Heroku |








