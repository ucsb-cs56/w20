---
assigned: 2020-02-19 17:00
assigned2: 2020-02-18 17:00
desc: "Understanding your Epic"
due: 2020-02-20 23:59
due2: 2020-02-19 23:59
layout: lab
num: lab08
org: ucsb-cs56-w20
ready: false
---

# This lab requires in-person participation

This lab requires you to attend and participate with your team in your assigned lab section on either {{page.due}} or {{page.due2}}
and cannot be made up, except by participation with your team over Slack.

If you are not able to attend lab, you should be in touch with your team and your team mentor (as explained many previous times during the quarter.)

# The teams you are on now are a starting point

If your team wants to stay together and work on the epic to which you have been assigned, you have that option.

Today, you'll learn what that project would be like.

You may also have a few other options, possibly including:

* Switching to another team within the same discussion section, if that team has room (max 6 per team)
* Choosing not be on a team, and instead doing a series of conventional individual labs.  
  * These labs will continue in a manner
    similar to lab07, and are NOT associated with any code that continues beyond the end of the course.
  * Choosing this option means that you lose the opportunity to work on a legacy code project

This will be explained in more detail on the Slack and in lecture on 
*  Thursday 11am 2/20/2020 for the TR folks
*  Monday 12:30pm 2/24/2020 for the MW folks

# This lab has a quick turn around time

This lab should be completed MOSTLY within your 50 minute discussion section, though there is somework that you might need to continue over the subsequent 24 hours.

It needs to be finished, however, before:
* lecture Thursday 11am 2/20/2020 for the TR folks
* lecture Monday 12:30pm 2/24/2020 for the MW folks

The reason is that on those days in lecture we'll be assigning final project teams.  

This lab helps you to prepare for that.

# Step 1: Locate your teams's Epic (:00 to :10)

Each team has been assigned an "Epic" which is a collection of user stories from one of the three class legacy-code projects.

Navigate to: <https://ucsb-cs56.github.io/w20/info/projects/> and locate your team's epic.

You'll discover that your Epic is associated one of three projects:

* Open Lab Scheduler
* UCSB Courses Search
* Mapache Search

Meet with your team and your mentor for a 10 minute briefing about the big picture purpose of the project, and the
purpose of your particular epic.  Your mentor will explain to you what your epic is about.

Take notes.  You'll need to write down a few things about your project and your epic in a later step.

Time box this to the first 10 minutes of lab (you need to be finished by 10 minutes after the hour.)

# Step 2: Explore the Kanban board (:10 to :20)

Ask your mentor to walk you through the Kanban board for your Epic, and explain how a Kanban board works.

Go through each of the stories at a high level, so you have a general sense of the Epic and what is involved.

Again, take notes.  Time box this 

# Step 3: Groom the first story (:20 to :30)


Then take the first story on the board.

* Does the story have clear acceptance criteria?
   * Acceptance criteria are a list of things that should be true if the story is finished.
   * The list should be complete enough that if all of the acceptance criteria are met, the story is definitely finished.
* If it does not, as team, lead by your mentor, add clear acceptance criteria to the story.

The process of adding clear acceptance criteria to stories is sometimes called "Grooming".

Time box this: 

# Step 4: Get app running on localhost (:30 to :50)

In order to be able to work with this app, you need to be able to get it running on localhost.

Find the repo for your teams project, and clone it.   Configure it for OAuth on localhost.

Run `mvn spring-boot:run` and explore the app.  Ask your mentor for help with understanding the current features.


# Step 5:  Record your observations (after lab)

This is an individual step to be done after lab is over.

Your team has a repo called, for example `TEAM-w-5pm-b`.

This [web search](https://github.com/ucsb-cs56-w20?utf8=%E2%9C%93&q=TEAM&type=&language=) will bring up a list of all of these `TEAM` repos.  Locate the one for your team.

In a previous class meeting you should have been added to a team (as a "maintainer") that has admin access to this repo.  If that's not the case, then find someone on your team that can fix that now.

Next, clone this TEAM repo on your computer or your account.  Create a branch called <tt>lab08-<i>githubid</i></tt> 
(where <tt><i>githubid</i></tt> is your githubid).  You may also collaborate with up to one pair partner, in which case, use  <tt>lab08-<i>githubid1-githubid2</i></tt> 

On this branch, create a directory called lab08 in the repo, and under that directory, create a file called <tt>lab08-<i>githubid</i>.md</tt>  (or <tt>lab08-<i>githubid</i>-<i>githubid2</i>.md</tt>in which you put the following headings:

```
# Name(s): (your name(s))

# Project Description

Link to project here: 

(Briefly describe the purpose of the project to which your team is assigned.)

# Our Epic

Link to epic here: 

(Briefly describe the epic to which your team is assigned.)

# Observations

(OPTIONAL: Add any observations you have about the project, and/or the epic to which you were assigned.)

```

Then, fill in the file, replacing the placeholder text with your own answers and observations.

* For reference here are the projects and the epics:  <https://ucsb-cs56.github.io/w20/info/projects/>

Commit that and make a pull request to your TEAM repo.   Do not merge the pull request.   Later on, your mentor will accept the pull request and give you credit for the the assignment.

# Rubric

* (20 pts) There is a pull request in the correct repo for the correct file
* (40 pts) There is a description of the project
* (40 pts) There is a description of the epic
