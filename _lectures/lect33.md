---
num: Lecture 33
lecture_date: 2020-03-04
desc:
desc: "What is JSON?"
ready: false
pdfurl:
---

# Today: Announcements, then JSON, then time to work

# Announcements

## Individual Track

* Strongly encourgaed to subscribe to '#individual-project-track` on Slack
* proj03 is up.
* proj04 and proj05 coming soon.  Deadlines will be one week after project drops, but no later than last day of week 10.
* The `<plugin>` element for jacoco has started causing an unknown error when running `mvn test`
   * For now: COMMENT IT OUT
   * That should fix `mvn test` and CI
   * Ignore any instructions that ask you to run `mvn jacoco:report`
   * There are further details on Slack 

## Project Track

* Deadline for pull requests is 5pm Sunday
* Extra Conrad office hours 1pm-5pm Saturday and Sunday
* If building is locked, use `#general` channel on Slack, or call up to Windows outside room 3525 to get someone to come 
* DO NOT PROP OPEN DOORS.  I REPEAT, DO NOT PROP OPEN DOORS.

# JSON

JSON is used in a few places in the course, and widely throughout real-world software development.

Let's talk a little about what JSON is, and how its used.

# JSON in a Nutshell

* JSON stands for "JavaScript Object Notation" but it's used far more widely than just in JavaScript
* JSON has six data types: string, number, boolean, null, object, array

This page explains the six types quite nicely: <https://restfulapi.net/json-data-types/>

# A few tips

* JSON looks a lot like Python syntax for lists and dictionaries, but one difference is that in JSON you *must* use double quotes, not single quotes.
* If you load json in a browser, depending on browser settings and extensions, it might or might not "format" it.
* If you get a bunch of JSON and it looks awful and hard to read, there are solutions:
   * Online "pretty-printers" such as: <https://jsonformatter.org/json-pretty-print>
   * Load into an editor such as VSCode, load a JSON formatting extension, and hit reformat 
      * Reformat: Windows Shift + Alt + F,  Mac Shift + Option + F, Ubuntu Ctrl + Shift + I.
      

# Where JSON is used in CS56 this quarter
* `localhost.json` and `heroku.json` for the secrets file in all the projects that use GitHub or Google OAuth secrets
   * It stores json data that we load into an environment variable `SPRING_APPLICATION_JSON` that can override values in 
     the `application.properties` files
* lab07 and the individual project track for API access
   * USGS Earthquakes API, e.g. <https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&minmagnitude=2&maxradiuskm=100&latitude=34.414000&longitude=-119.848900>
   * Location Search API
* [ucsb-courses-search](https://github.com/ucsb-cs56-w20/ucsb-courses-search) for:
  * the UCSB Curriculums API


# JSON in Java

Note that the following is general Java, NOT Spring Boot specific.

There are a variety of libraries for dealing with JSON in Java.  
* To the best of my knowledge, are all third-party, and thus require you to have appropriate `dependency` elements in your Maven `pom.xml` (or the equivalent for whatever build system you are using; in this course, it's always Maven for now.)

The library we are using is called Jackson. In the pom.xml you'll find:

```xml
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-core</artifactId>
            <version>2.10.0</version>
        </dependency>

        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.10.0</version>
        </dependency>
```

With these two libraries, we can write so-called *serializer/deserializer* code for JSON.

* Serializer code turns Java Objects in JSON
* Deserializer code turns JSON into Java Objects.

More generally: Serializer/deserializer code converts between:
* objects of any given programming language, and 
* a "serial format" that can be stored in a disk file, or sent over a network connection.

# Examples from our code bases: 


From UCSB Courses Search: <https://github.com/ucsb-cs56-w20/ucsb-courses-search/blob/8cb242874d34aec4e3f2bf62baf220b464655221/src/main/java/edu/ucsb/cs56/ucsbapi/academics/curriculums/v1/classes/CoursePage.java#L39>

From lab07: 

```
/**
     * Create a FeatureCollection object from json representation
     * 
     * @param json String of json returned by API endpoint {@code /classes/search}
     * @return a new FeatureCollection object
     * @see <a href=
     *      "https://tools.ietf.org/html/rfc7946">https://tools.ietf.org/html/rfc7946</a>
     */
    public static FeatureCollection fromJSON(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            FeatureCollection featureCollection = objectMapper.readValue(json, FeatureCollection.class);
            return featureCollection;
        } catch (JsonProcessingException jpe) {
            logger.error("JsonProcessingException:" + jpe);
            return null;
        } catch (Exception e) {
            logger.error("Exception:" + e);
            return null;
        }
    }
```    

