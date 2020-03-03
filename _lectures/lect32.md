---
num: Lecture 32
lecture_date: 2020-03-03
desc: "What is JSON?"
ready: false
pdfurl:
---


JSON is used in a few places in the course, and widely throughout real-world software development.

Let's talk a little about what JSON is, and how its used.

# JSON in a Nutshell

* JSON stands for "JavaScript Object Notation" but it's used far more widely than just in JavaScript
* JSON has six data types: string, number, boolean, null, object, array

This page explains the six types quite nicely: <https://restfulapi.net/json-data-types/>

# A few tips

* JSON looks a lot like Python syntax for lists and dictionaries, but one difference is that in JSON you *must* use double quotes, not single quotes.
* If you load json in a browser, depending on browser settings and extensions, it might or might not "format" it.
* If you get a bunch of JSON and it looks awful and hard to read, there are solutions:
   * Online "pretty-printers" such as: <https://jsonformatter.org/json-pretty-print>
   * Load into an editor such as VSCode, load a JSON formatting extension, and hit reformat 
      * Reformat: Windows Shift + Alt + F,  Mac Shift + Option + F, Ubuntu Ctrl + Shift + I.
      

# Where JSON is used in CS56 this quarter
* `localhost.json` and `heroku.json` for the secrets file in all the projects that use GitHub or Google OAuth secrets
   * It stores json data that we load into an environment variable `SPRING_APPLICATION_JSON` that can override values in 
     the `application.properties` files
* lab07 and the individual project track for API access
   * USGS Earthquakes API
   * Location Search API
* [ucsb-courses-search](https://github.com/ucsb-cs56-w20/ucsb-courses-search) for:
  * the UCSB Curriculums API


