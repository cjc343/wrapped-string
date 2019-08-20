# APIDemo

How to start the APIDemo application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/wrapped-string-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:9080`

What this demonstrates
---

This sample app demonstrates two issues with the native Java 11 client.

1. The Content-Type is wrong, causing a 415
1. The payload is quoted

This sample app:

- Includes 4 test cases in FileApiResourceTest, 2 failing, which demonstrate the differences between the native client and a jersey2 client.
- Includes 2 test APIs in FileApiResource which allow you to experience the differences by issuing GET requests from your browser.
