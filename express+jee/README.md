# exress+jee
This example project demonstrates the use of GraalVM to wrap Payara Micro in an NodeJS Express application. 

Opening http://localhost:8088 will trigger a route in Express that will retrieve a message from a stateless session EJB.

Using this approach, you can front-end a Java application, leveraging all the security and resource control features of Jakarata EE (Java EE) with a Node JS Express application.