---
num: Lecture 34
lecture_date: 2020-03-05
desc: "MVC and GET vs POST vs. all the others"
ready: false
pdfurl:
---

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

