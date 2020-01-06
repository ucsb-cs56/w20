---
num: Lecture 13
lecture_date: 2020-01-28
desc:
ready: false
pdfurl:
---

---
num: Lecture 13
lecture_date: 2019-11-11
desc:
ready: false
pdfurl:
---

# Midterm 2 is cancelled

The points for midterm 2 will be replaced with the higher of:
* your score on Midterm 1, or
* your score on the Final Exam.

That means that your final exam average will be either:
* ⅓ Midterm 1 + ⅔ Midterm 2, OR
* ⅔ Midterm 1 + ⅓ Midterm 2, WHICHEVER IS HIGHER.

# Why are we cancelling Midterm 2

With:
* losing a lecture to Veterans Day
* losing a lecture to Thanksgiving
* being behind on getting the project started

We need all the lecture time we can get.

# A few updates on lab07a

## Jacoco report will have lots of red.  It's ok.

As I mentioned on Slack, in response to a question from a student:

> And yes, we know there is a lot of test coverage missing in this app.  
> 
> That's not a mistake, it's just "unfinished business" (unfinished business that you are not responsible for in lab07a.)
> 
> All you are responsible for in lab07a regarding jacoco is making sure that the report is published.
> The reason behind that is to make sure that you know how to generate a jacoco report, 
> so that later on, when doing your own apps, you know how to check the test coverage.    
> Knowing how to check it is the first step towards being able to improve it.
> But there are many other steps too, and we aren't quite there yet.

So, it's fine if the jacoco report shows lots of red.

You can, if you like, start to think about how you would fix that.  But fixing that is *not* part of lab07a.

## This change will fix a dead link on your project website

On the project website on GitHub pages, the link to `Javadoc (test code)` may be a dead link.

Although not required for lab07a, you can fix this by doing:

```
mvn javadoc:test-javadoc
mvn site:deploy
git add docs
```

and then committing the changes.

Generating the javadoc for the files under `src/test/java` is a separate step from generating those under `src/main/java` as it turns out.  

## This change, though not required, may help you with debugging

Here is a change you can make in the file `src/main/resources/application.properties` that, while not *required* for lab07a,
will reduce the number of errors that you are seeing in the log.  

That may
* help you feel more confident that you are doing things correctly
* help you distinguish between real problems, and ones that are nothing to worry about.

This change *will* be part of lab07b, but it's easy, and so you might as well make it now.  It will also make the login 
stop saying "guest" and start saying "member", which is nice.

In  `src/main/resources/application.properties` change this line:

INCORRECT:
```
spring.security.oauth2.client.registration.github.scope: "read:user", "read:org"
```

so that it reads like this instead:

CORRECT: 
```
spring.security.oauth2.client.registration.github.scope: user,read:org
```

Once you do this, you will need to stop and restart the running application with:

```
mvn spring-boot:run
```

Then, if you click Logout and Login again, it should reprompt you to verify that you give your app permission to your GitHub account.  The reason it's reverifying is that this line (now correctly) specifies the "OAuth Scope" for your application.

The OAuth Scope defines how much information you are giving the application permission to grab from your GitHub account.

By default, you were only able to get a limited amount of information.  Now, you are giving the application permission to see what organizations you belong to.

This is a way for us to define four permission levels in our Spring Boot apps without having to build permissions 
into the apps themselves.  We define a GitHub organization (in this case `ucsb-cs56-f19`) and then we use the permission
levels of that app to define four levels of access:

* Not logged in 
* `Guest`: Logged in but not a member of the GitHub org
* `Member`: Logged in, and a member of the GitHub org
* `Admin`: Logged in, and an admin in the GitHub org

Later on, we'll show how we can protect certain pages in our app to be accessible only to certain permission levels.

Once you make this change, instead of saying "Guest" when you login, it should say "Member", provided you are a member of the `ucsb-cs56-f19` GitHub organization, which you all should be.

You can also redefine the variable `app-github-org` in the `localhost.json` file to be any other GitHub org of your own choosing (including one that you make yourself).  If you make your own github org, you'll be an admin in that org.  Then you can test whether the `admin` level works properly on your `localhost` version of the app.  Leave the setting of `app-github-org` on the Heroku version to be `ucsb-cs56-f19` though.



