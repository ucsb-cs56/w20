---
desc: Getting Started

assigned2: 2020-01-07 17:00
assigned: 2020-01-08 17:00
due2: 2020-01-13 23:59
due: 2020-01-13 23:59
layout: lab
num: lab00
ready: true
signup_app: https://ucsb-cs-github-linker.herokuapp.com/
---

If you find typos or problems with the lab instructions, please report
them on the #typos channel on Slack

# Goals

This lab checks that you can succesfully edit, compile, run and submit a simple
`HelloWorld.java` to Gradescope for grading.

If all goes well, it will only take a few minutes, and then you get
started on the next lab immediately.  That one will actually take some
work.


Step 0: Getting oriented
========================

Chances are you are already familiar with all of the following because
you already took CS courses that used this stuff (i.e. CS8, CS16,
CS24, CS32).

If not--if for example, you are a transfer student and this is your first 
CS course using the CSIL Lab resources, please let your TA know and he/she 
will help you find resources to come up to speed on these things. Or, your 
TA can assign you a pair partner that knows this stuff and doesn't mind 
helping you come up to speed.

-   knowing your College of Engineering/CSIL computer account username/password--and having an active working account.
-   knowing how to login to the systems in Phelps 3525 and the CSIL lab, and bring up both a web browser, and a terminal window.
-   knowing that "CSIL" is both a server you can log into, as well as a physical room full of computers--and knowing where to find that physical room, and what hours it is open.
-   knowing how to use a **basic text editor such as emacs or vim** to edit files on the Linux systems in Phelps 3525 and CSIL.
-   knowing basic Unix/Linux commands to create directories, change directory, manipulate files, etc., e.g. mkdir, cd, pwd, mv, rm, ls.

The rest of these instructions will assume you know all of the
above. If not, then let your TA know, and we'll point you to resources
where you can come up to speed.

Here, for example, is some [basic instruction on vim](https://ucsb-cs56.github.io/topics/vim)

As a separate item, you should also know how to connect to CSIL from your own computer (WindowsNN/Mac/Linux)

* [Windows](https://ucsb-cs56.github.io/topics/csil_via_ssh_from_windows/)
* [Mac](https://ucsb-cs56.github.io/topics/csil_via_ssh_from_macos/)
* [Linux](https://ucsb-cs56.github.io/topics/csil_via_ssh_from_linux/)

But, you don't need that for today's lab---so let's continue.

The rest of the lab: Step-by-Step
=================================

## Step 1: Create a CoE account if you don't have one already

For this first assignment, we'll encourage you to login to the machines in the
to the machines in the Computer Science labs, or to connect
remotely, and do the assignment on CSIL.

Later in the course, for a variety of reasons, if you have your own laptop, it
may be more effective to do your work there.   We'll try to always offer an option
to work on the CSIL machines though, and we'd like *everyone* to start there so that
we all have a common platform we can share.

To do this you will need a **College of Engineering
account**. You can create an account online at
<https://accounts.engr.ucsb.edu/create> if you don't already have one.

If you are enrolled in <i>any</i> CoE course this quarter (including CS56), you
should be able to create your account immediately.

If you are not able to do so, you will need to contact the ECI Help Desk at <a href="mailto:help@engineering.ucsb.edu">help@engineering.ucsb.edu</a>.

## Step 2: Get setup with github and add yourself to our organization

We will be using github.com in this course. We have created an
organization called {{site.github_org}} on github.com where you can
create repositories (repos) for your assignments in this course.

The advantage of creating private repos under this organization is
that the course staff (your instructors and TAs) will be able to see
your code and provide you with help, without you having to do anything
special.

To join this organization, you need to do three things.

1. If you don't already have a github.com account, create one on the
"free" plan. Visit [https://github.com/](https://github.com/)

2. If you don't already have your `@ucsb.edu` email address
associated with your github.com account. go to "settings", add that
email, and confirm that email address.  (If you have an `@umail.ucsb.edu` address registered there, that also works.)

3. Visit our Github Sign Up Tool at <{{page.signup_app}}>.   Navigate to <{{page.signup_app}}>.  Login with your github.com account. Click "Home", find this course ({{site.course}}, {{site.qtr}}), and click the "Join course button".   That will automatically send you an invitation to join the course organization on github.

4. There should be a link to the invitation for the GitHub organization for this course (<https://github.com/{{site.github_org}}>). Click on the invitation link and accept it. You can also go straight to <https://github.com/{{site.github_org}}> and see the invitation there (if you're logged in). Accept the invitation that appears in your browser (from step 3) or log into your account on [https://github.com/](https://github.com/) to accept the invitation.

## Step 3: Get setup with gradescope

We will use gradescope to grade all your homeworks, exams and lab/programming assignments. I have manually added everyone (using your @umail.ucsb.edu accounts) currently enrolled in the course to the Gradescope system. You should have received an email notification with instructions about logging into gradescope. Once you follow the instructions to set your password, you should have access to our course on Gradescope. You should see {{site.course}} in your {{site.qtr}} courses.

The lab assignment {{page.num}} should appear in your Gradescope dashboard in {{site.course}}. You will need to submit your code for {{page.num}} using this page.

## Step 4: Some Java coding

1.   Login to your CSIL account, and create a ~/cs56 subdirectory.

2.   In that directory, use your favorite text editor (e.g. `vim`, `emacs`) to create a file containing
   the following code.  Call the file `Hello.java`.  Put your name instead of `Your Name Here`.

   (Side note: if you prefer it, two new editors are now available on CSIL staring Summer 2018: [atom](https://ucsb-cs16.github.io/topics/atom/) and [visual studio code](https://ucsb-cs16.github.io/topics/code/).  We may be making considerable use of Visual Studio Code, so if you haven't used either, and would like to try one, we would steer you towards VS Code.  You can type `code` at the command line on the CSIL machines to access VS Code.  This typically works well only when you are sitting at one of the machines in Phelps 3525 or CSIL though; it doesn't work so well over remote X11 forwarding).

   ```java
   /**
     @author Your Name Here
   */

   public class Hello {

     public static void main(String[] args) {
        System.out.println("Hello World!");
     }

   }
   ```

   NOTE: Please don't put literally `Your Name Here` in the code above.  Write your actual name.  Thanks!

   4. Compile the file with the command `javac Hello.java`

   5. Run the file with the command `java Hello`

   6. Navigate to <https://gradescope.com>.   You should have an account invitation in your email.  If you don't, ask an instructor, TA or mentor for assistance.

   7. Upload your work to Gradescope.com for grading.    If you are working from your own machine (i.e. ssh'ing into CSIL), you'll need to transfer the file to your own machine before you can upload it for grading.    
      * If you aren't sure how, there is a link on the CS16 web page that explains [how to copy files between CSIL and your own machine](https://ucsb-cs16.github.io/topics/csil_copying_files/).

   8. Once you see that you have a score of 100 for {{page.num}} on Gradescope, you are *done* with {{page.num}}, BUT there is STILL MORE TO DO TODAY!

      Find a pair partner for lab01 from the students on your own team.
      (The team that you were placed on in the first lecture, and that
      you should be sitting together with in lab.)

      If there is an odd number of students on your team (or an odd number
      that has shown up today for lab), then check in with your team's mentor.

      In that case, they'll help you find a pair partner, or in rare cases,
      you may be given permission to work alone on lab01.

      Then get started on [lab01](/w20/lab/lab01/).

