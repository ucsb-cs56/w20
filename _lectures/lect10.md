---
num: Lecture 10
lecture_date: 2020-01-22
desc:
ready: false
pdfurl:
---

---
num: Lecture 10
lecture_date: 2019-10-30
desc:
ready: false
pdfurl:
---

Spring Boot With OAuth: <https://github.com/pconrad-webapps/spring-boot-thymeleaf-bootstrap-oauth>

UCSB Courses Search: <https://github.com/ucsb-cs56-f19/ucsb-courses-search>

# Terminology from today's lecture

* OAuth: a way of delegating authentication to, e.g. Github, Facebook, Google
* Bootstrap: CSS/JS framework for making our apps responsive and attractive(?)

# Chained Methods

In the `configure` method for Spring Security, we see code like this:

```
http
        .authorizeRequests()
            .antMatchers("/","/login**","/webjars/**","/error**")
            .permitAll()
        .anyRequest()
            .authenticated();
```

This is equivalent to:

```
http.authorizeRequests();
http.antMatchers("/","/login**","/webjars/**","/error**");
http.permitAll();
http.anyRequest();
http.authenticated();
```
           
           
We also discussed that the forEach and the for loop here are equivalent:

```
         Iterable<ClientRegistration> iterable;
         iterable = ((Iterable<ClientRegistration>) clientRegistrationRepository);
-        iterable.forEach(clientRegistration -> urls.put(clientRegistration.getClientName(),
-                "/oauth2/authorization/" + clientRegistration.getRegistrationId()));
+        // cr = clientRegistration
+        // iterable.forEach(cr -> urls.put(cr.getClientName(),"/oauth2/authorization/" + cr.getRegistrationId()));
+
+        for (var cr : iterable) {
+            urls.put(cr.getClientName(),"/oauth2/authorization/" + cr.getRegistrationId());
+        }
+
```
       
