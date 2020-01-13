---
num: Lecture 5
lecture_date: 2020-01-13
desc: "ic01 (ux for courses search) and lab02 (Spring Boot, Heroku)"
ready: true
pdfurl:
---

# You should be on Slack

It's important to be registered on the Slack.  One of the important skills we're learning in this course is professional communication, as it relates to software development.  The Slack channel will be our primary vehicle for that.

Important information will be disseminated there.   

There are clients for [iOS](https://slack.com/downloads/ios), [Android](https://slack.com/downloads/android), [Windows](https://slack.com/downloads/windows), [MacOS](https://slack.com/downloads/mac) and [Linux (.deb and .rpm)](https://slack.com/downloads/linux).

Or you can just use the web at <https://ucsb-cs56-w20.slack.com>

If you aren't on the Slack, get on the Slack!

# Setting up your laptop for Java

What you need:

* `git` 
* Java JDK 11 (not less than 11, not more than 11)
* Maven (version 3.x), i.e. any version that starts with 3
* Ant (only if you want to work on lab01; won't need it the rest of the quarter)

Optional, but encouaged:
* Visual Studio Code

Set up instructions:

* Mac <https://ucsb-cs56.github.io/topics/macos/>
* Windows <https://ucsb-cs56.github.io/topics/windows/>  Note that this one is a work in progress.   Stay tuned.

We can set up slack channels for MacOS and Windows as well.

Linux users "usually know what to do", but we can set up a channel for them too, if desired, collect their wisdom, and deposit it on a page for future students.

# lab02: Hello World for Spring Boot, Heroku

In this lab, we'll get a web app up and running on both localhost and Heroku

* <https://ucsb-cs56.github.io/w20/lab/lab02>

A few things that the lab covers and that we might discuss briefly in lecture

* web page/site vs. web app
* port numbers
* localhost vs. Heroku
* Ant vs. Maven

# Server-side rendering (e.g. Thymeleaf) vs. Client-Side Rendering (e.g. React)

We'll discuss the difference first.

Then:
* Server-side rendering is kind of old school.
* There are a lot of benefits to client-side rendering.
* The course is already really, really full of material


But some folks might be willing to take on the extra work for the extra benefit.

Is there interest in also learning React?   If so, register that interest by joining the the `#react` channel on the Slack.

We might offer this as an optional track if there is enough interest, and we can do so without making the course too complicated.

# Some raw notes from today's lecture

```
169-231-151-162:ucsb-cs56-w20 pconrad$ git clone git@github.com:ucsb-cs56-w20/lab02-pconrad.git
Cloning into 'lab02-pconrad'...
warning: You appear to have cloned an empty repository.
169-231-151-162:ucsb-cs56-w20 pconrad$ ls
LECTURE_0108				STAFF-react-calculator-prototype
LECTURE_0109				lab02-pconrad
169-231-151-162:ucsb-cs56-w20 pconrad$ cd lab02-pconrad/
169-231-151-162:lab02-pconrad pconrad$ ls
169-231-151-162:lab02-pconrad pconrad$ ls -a
.	..	.git
169-231-151-162:lab02-pconrad pconrad$ # git remote add origin git@github.com:ucsb-cs56-w20/lab02-pconrad.git
169-231-151-162:lab02-pconrad pconrad$ # git pull origin master
169-231-151-162:lab02-pconrad pconrad$ # A good exam question: what are the four things
169-231-151-162:lab02-pconrad pconrad$ # that git clone does?
169-231-151-162:lab02-pconrad pconrad$ # Answer: mkdir, git init, git remote add ... , git pull origin master
169-231-151-162:lab02-pconrad pconrad$ 
```
