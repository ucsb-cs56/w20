---
num: Lecture 5
lecture_date: 2019-10-14
desc:
ready: true
pdfurl:
---

# A little bit of Spring Boot

* <https://github.com/pconrad/spring-boot-minimal-webapp>
   * This is the one we used for lab02
* <https://github.com/pconrad-webapps/spring-boot-thymeleaf-bootstrap-from-cdn>
   * This is the logical next "tiny step"

Let's look at the `src/main/resources/templates` folder

# Graded Homework Update

* H00, H01, H02, and H03 are now graded, and the grades are posted to Gradescope.
* If you don't have a Grade on Gradescope for any of these, and you think you should, please contact the instructor
  via Slack message right away.
* Use [GradeScope Regrade Requests](https://ucsb-cs56.github.io/topics/gradescope_regrade_requests/) to ask grading questions about anything that was graded via Gradescope   
* lab00 and lab01 are updated and posted to Gradescope also
    

  
# You should be on Slack

It's important to be registered on the Slack.  One of the important skills we're learning in this course is professional communication, as it relates to software development.  The Slack channel will be our primary vehicle for that.

Important information will be disseminated there.   

There are clients for [iOS](https://slack.com/downloads/ios), [Android](https://slack.com/downloads/android), [Windows](https://slack.com/downloads/windows), [MacOS](https://slack.com/downloads/mac) and [Linux (.deb and .rpm)](https://slack.com/downloads/linux).

Or you can just use the web at <https://ucsb-cs56-f19.slack.com>

If you aren't on the Slack, get on the Slack!


# Lab03 Update 10/13/2019 

Last Monday and Wednesday 10/07/2019 and 10/09/2019 in lecture I mentioned that we were
not generating the javadoc for lab03.  However, both the instructions and the grading
rubric on Gradescope were sending a different message.

Accordingly, I've now updated the instructions, and am providing a few extra days
in case you want to review your submission.

If your score on Gradescope shows 100%, there is no action you need to take.

Otherwise, if you have a score less than 100% and want to try to increase your score,
you have until the deadline shown on Gradescope to try to get additional test
cases to pass, or troubleshoot whatever may be going awry.

To review: the reason we are not generating the javadoc is that when jacoco test
coverage reports are generated along side javadoc and published to Github pages,
it leakes the source code.  That is of course not a good idea for a closed source
assignment, since it may lead to a temptation of academic dishonesty.

We are looking into alternative means to publish javadoc and jacoco reports in a way
that makes it possible for the students authoring the repo and the course staff to
see them, but no-one else.

Also: if you had trouble with the `mvn jacoco:report` command:
* It might be because the lab instructions said to run:<br>
  `mvn jacoco:report site:deploy`.
* That is actually doing two commands in one:
  ```
  mvn jacoco:report
  mvn site:deploy
  ```
* The first one is fine, but the second one was disabled in the `pom.xml`
* If you just try `mvn jacoco:report` after doing `mvn test`, it should work fine.
* To see the resuts, look at `target/site/jacoco/index.html` in a browser.

# Lab04 Overview

* We'll discuss why the constructor takes an `int []` in order high to low
* But why the coefficients are stored in the `ArrayList<Integer>` low to high
* We'll also briefly discuss `has-a` vs. `is-a`
* We'll talk about the `toString` and `equals` methods and why debugging them with tests is so tricky
* Any other questions/answers about lab04

# == vs `.equals`

<https://ucsb-cs56.github.io/topics/java_double_equals_vs_dot_equals/>

# The .equals method from `cs56-rational-ex13`

We were looking at the test coverage in [cs56-rational-ex13](https://github.com/ucsb-cs56-pconrad/cs56-rational-ex13) on the branch `pcDemoTestCoverage`.

We got as far as the `.equals` method.

We might come back to that, but I want instead to skip forward to looking at 
* <https://ucsb-cs56.github.io/tutorials/rational_ex14/>
* <https://ucsb-cs56.github.io/tutorials/rational_ex15/>


