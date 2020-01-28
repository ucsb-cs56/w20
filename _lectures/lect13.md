---
num: Lecture 13
lecture_date: 2020-01-28
desc:
ready: true
pdfurl:
---

# Adding our teams on GitHub

* Navigate to <https://github.com/ucsb-cs56-w20>
* Click Teams button at top
   * This should take you to <https://github.com/orgs/ucsb-cs56-w20/teams>

* The staff has to add the first member of your team as a maintainer.
   * After that, the maintainer should add one other members as a maintainer.
   * Then the two of you together can add all the rest as maintainers.

# Create a repo for the team

One of you should now create a private repo called, for example:
* TEAM-t-5pm-a
* TEAM-t-5pm-b
etc.

All caps `TEAM` followed by hyphen and then your team name.

Then, go to settings, Collaborators and Teams, and give your team admin access to the repo.


# Now, divide up at laptops

* If each member has a laptop, they can work by themselves.
* Any member without a laptop, get with a pair partner that does.
* You may also choose to work in a pair, even if you both have laptops.

# Next, each team member should clone the repo.

Clone the repo onto your laptop, or alternatively ssh into CSIL and clone it into a directory under your CSIL account.

# Now, create a branch

You've cloned the repo, and done a `cd` into the directory for the repo.

Now you are going to create a branch just for your (or for you and your pair)

Your branch name will be 0128-githubid  or 0128-githubid1-githubid2, where githubid is your githubid.

E.g.

* 0128-cgaucho
* 0128-cgaucho-ldelplaya

To create the branch, type:

* `git checkout -b 0128-cgaucho-ldelplaya`

# Divide up these tasks among the branches

You now have between three and six individuals/pairs.

Divide up these tasks, in whatever way you see fit.   Only one individual/pair needs to do each of these.

You'll do each of these tasks in one of your branches.   Once you've edited the file, do:

```
git add filename
git commit -m "ab/cd - added file for attendance"
git push origin branch-name
```

(1) Add a file called `attendance.md`.  In this file, record who is present/absent by name.  For example

```
# 01/28 Attendance

Chris Gaucho (he)
Lauren del Playa (she)
Pat Sabado (they)

(others absent)

```

(2) Add a file called `dev-questions.md` 

In this file, put some questions that you might ask Developers about how real world practice goes when developing software.

(3) Add a file called `pm-questions.md` 

In this file put some questions that you might ask Product Managers about how real world practice goes when developing software.


(4) Add a file called `ux-questions.md` 

In this file put some questions that you might ask User Experience designers about how real world practice goes when developing software.


(5) Add a file called `qa-questions.md` 

In this file put some questions that you might ask Quality Assurance engineers about how real world practice goes when developing software.

(6) Add a file called `team-questions.md` 

In this file put some questions that you might ask any industry professional about team work practices and challenges in developing real world software.

# Make pull requests for each branch

# Merge pull requests into Master


* Midterm announcement coming Tuesday/Wednesday
* Please sit in teams on Tuesday/Wednesday

Today:

* Preview of an upcoming lab: <http://cs56-w20-lab04-pconrad.herokuapp.com/>
   * A web interface to your lab01 Rational class
   * What done:
      * The instructor reference solution is done
      * Still have to write up the instructions, and create the starter code.
      * That's my homework for tonight.
   * What you'll learn:
      * Thymeleaf templating, and "Form Beans"
      * Spring Boot Controllers
   * Not yet covered:
      * Login/logout
      * APIs 
      * Databases
* Interfaces (as on Fall 2019 Midterm Exam): <https://ucsb-cs56.github.io/f19/exam/e01/cs56-f19-e01/>
* Sorting (brief look at https://ucsb-cs56.github.io/tutorials/rational_ex15 )
* Lambdas (brief look at https://ucsb-cs56.github.io/tutorials/rational_ex14/ )
