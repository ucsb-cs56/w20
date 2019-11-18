---
num: Lecture 15
lecture_date: 2019-11-18
desc: More Spring Boot Skills
ready: false
pdfurl:
---


# The `network` tab on Github

Let's take a look at the Network tab on Github.

You'll find it under "Insights"

Example:
* <https://github.com/ucsb-cs56-f19/STAFF-lab07-dev-WIP/network>

This is helpful for tracking what branches you have and what state they are on, and whether they've been merged into `master` or not.

# Adding an Inceptor

* First let's review the "Request/Repsonse" cycle of how web apps work
   * This is not just in Spring Boot, but across all web frameworks.
   * It's the basic action of the HTTP protocol
   * Therefore, you see it represented in every web framework, somehow
* Then let's look at how you add an Interceptor in Spring Boot.

An interceptor allows you to run some code before, and after each of the web controller methods runs.

Most web frameworks have some way of doing this.

It allows you to factor out things that should "always happen" at the beginning and the end.

# While we are at it, let's look at at ControllerAdvice as well.

