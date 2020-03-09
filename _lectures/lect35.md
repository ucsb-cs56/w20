---
num: Lecture 35
lecture_date: 2020-03-09
desc: "Final Exam Review for MW, plus GET vs POST"
ready: true
pdfurl:
---

Final Exam for MW Lecture Students:

> Tuesday, March 17, 2020 12:00 PM - 3:00 PM

Yes:
* the MW lecture final exam is Tuesday
* the TR lecture final exam is Wednesday
* Please do not show up on the wrong day.
* We won't have enough chairs or exams if that happens.
* Don't miss your scheduled exam.

As for contingency plans for COVID-19:
* I don't know any more than you do.
* As of right now, everything is on as scheduled
* We'll follow the directions of the Chancellor as they are announced.  

# Course Evaluations

* The goal of this course, "Advanced Applications Programming" is to help you
  * move beyond the kind of programming you did in 16/24/32
  * into the kind of programming involved in real-world software development
* Real world software development is messy
  * You have to both determine "what problem to solve" as well as "how to solve it"
  * The messiness was exacerbated by trying to scale up the size of the course to accomodate the waitlist.
* I took on an extra load to try to help ensure that the waitlisted students would not have to wait until F20 to take the course.
  * As a result, the course was certainly less well organized that it otherwise would have been.
  * I hope that you will take that into consideration when deciding on ratings for
    * Course independent of Instructor (evaluate the *course*)
    * Instructor independent of Course (evaluate the *instructor*)

The University takes course evaluations seriously.   
* They affect pay and promotion for faculty
* If you want your comments to be taken seriously, avoid profanity.
* The written comments are just as important (if not more so) than the 
  numerical ratings.
* Comments that focus on what helped or detracted fron your *learning* are the most on point.

# Instructor must leave the room while evals are completed

* However, please do not use this time as an opportunity for "office hours".

# Review for Final

* [lab07](https://ucsb-cs56.github.io/w20/lab/lab07/) Make sure you understand all the steps
* [Lecture 31](https://ucsb-cs56.github.io/w20/lectures/lect31/) on Spring Boot Databases
* [Lecture 33](https://ucsb-cs56.github.io/w20/lectures/lect33/) on JSON
* [Lecture 35](https://ucsb-cs56.github.io/w20/lectures/lect35/) (today) MongoDB and GET vs POST
* Exceptions HFJ Chapter 11

# MongoDB ArchivedCourse table

* A brief real time update

# Model View Controller

Now that we have some Spring Boot experience, and in anticipation of the final, let's review Model View Controller

* Model: In general, Basic Java classes that don't necessarily reflect that they are part of a web app
   * Often includes the database, as well as "services" that just do basic computation, or lookup on APIs, etc.
   * More specifically in Spring Boot, `Model` is a key/value store used to send information to the view. 
* View: The Thymeleaf files that are the user interface of the application
* Controller: the part that links the model and the view

In a web app, Controllers typically:
* connects a web endpoint (e.g. `/` or `/search/bydept`) to the code that 
  * reads a request
  * produces a response
* The response is typically a "mashup" of data from a Model object and an HTML template.  
  
# An important principle: Don't do too much computation in the View!

If you are writing really complicated code in Thymeleaf, you are *probably doing something wrong*

Consider moving the complicated calculation into a method of an object in your model, and then
invoking that method.
  

# A whitelabel error message

![post-not-supported.png](/w20/lecture/lect34/post-not-supported.png)

# The HTTP protocol

HTTP`, and it's secure cousin `HTTPS`, are *protocols* for communication between
* a web client (e.g. a browser, but also programs that are fetching data from APIs)
* a web server (anything that listening on an address that starts with `http://` or `https://`

HTTP is a "request/response" protocol.

* Requests have *method* types
   * GET
   * POST
   * many others
* Responses have response codes
   * 200 (success)
   * 404 (not found)
   * many others...
   
One key to understanding web interactions is understanding a bit about web requests, responses and 
status codes.


# HTTP methods GET and POST

*Not to be confused with methods of a class in Java, or any other object-oriented language.)*

The main two HTTP methods are GET and POST.
* These are the only two that you'll commonly see coming from a `<form>` element in a browser.


GET:
* is typically used for just retrieving information, when nothing changes on the server
* puts all the parameters in the URL where they can be seen
   * pro: requests can be bookmarked along with their paramters
   * con: not so good if the params are long, or contain sensitive data

POST:
* is typically used when something is going to CHANGE on the server
   * e.g. you are going to update a database, or some other change to server state in some way
   * also used for login/logout because that changes the state of the users's session, which is stored on the server
* often has CSRF protection enabled; see Scott Chow's writeup here: <https://ucsb-cs56.github.io/topics/spring_boot_post_and_csrf/>

# Example of logout form using POST from lab07

* This [excerpt from lab07](https://github.com/ucsb-cs56-w20/STARTER_lab07/blob/f437a29da7534058c9edfc39ab00f07a1d479d25/src/main/resources/templates/bootstrap/bootstrap_nav_header.html#L36-L39)
  shows the Spring Boot CSRF protection in a logout form.
  ```html
  <form method="POST" action="/logout" class="form-inline" th:if="${isLoggedIn}">
    <button class="navbar-btn" type="submit">Logout</button>
    <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" />
  </form>
  ```

# For more detail

* <https://www.freecodecamp.org/news/http-and-everything-you-need-to-know-about-it/>


