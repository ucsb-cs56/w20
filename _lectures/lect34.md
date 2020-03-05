---
num: Lecture 34
lecture_date: 2020-03-05
desc: "GET vs POST vs. all the others"
ready: false
pdfurl:
---

# The HTTP protocol

HTTP`, and it's secure cousin `HTTPS`, are *protocols* for communication between
* a web client (e.g. a browser, but also programs that are fetching data from APIs)
* a web server (anything that listening on an address that starts with `http://` or `https://`

HTTP is a "request/response" protocol.

* Requests have *method* types
   * GET
   * POST
   * many others
* Responses have response codes
   * 200 (success)
   * 404 (not found)
   * many others...
   
One key to understanding web interactions is understanding a bit about web requests, responses and 
status codes.

