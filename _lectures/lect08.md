---
num: Lecture 8
lecture_date: 2020-01-16
desc:
ready: false
pdfurl:
---

---
num: Lecture 8
lecture_date: 2019-10-23
desc:
ready: false
pdfurl:
---

# Immutability of `String` in Java

Objects of type `String` in Java are immutable.

They cannot be changed after they are initially created.

So, if I write:

```java
String name = "Phill";
name = name + "ip";
```

Now, `name` refers to a String "Phillip".

* Did any String object change in this example?
* Does this show that String is actually mutable?

No.  `String` is still immutable.

How do we explain this?
