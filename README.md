# spring-boot-multiple-jndi-datasources
Spring boot application that connects to multiple databases using multiple JNDI dataSources configured on tomcat server.

### Technologies Used
* Spring Boot
* Spring Data JPA
* MySql DB
* Hibernate
* Maven

### Prerequisites
Java 1.8 or greater, Spring boot 2.0 or greater, Maven, IntelliJ or Eclipse, Apache tomcat server or any application server.

### Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes:
* Create 2 databases called `'user'`, `'booking'` which we will connect to both of them using jndi datasources.
* For creating jndi datasource in tomcat server go to *`TomcatHomeDirectory/conf`* folder.
* Add below code in the server *`server.xml`* file. The code should be added in the *`GlobalNamingResources`* element:

`
<Resource name="jdbc/userJNDI" 
      global="jdbc/userJNDI" 
      auth="Container" 
      type="javax.sql.DataSource" 
      driverClassName="com.mysql.cj.jdbc.Driver" 
      url="jdbc:mysql://localhost:3306/user" 
      username="root" 
      password="root" 
      maxActive="100" 
      maxIdle="20" 
      minIdle="5" 
      maxWait="10000"/>
` 
* There should be another *`Resource`* element for the booking database the same as the code above with changing the *`name, global, url`* attributes.
* Add below code in the server *`context.xml`* file:

`
<ResourceLink name="jdbc/userJNDI"
                global="jdbc/userJNDI"
                auth="Container"
                type="javax.sql.DataSource" />
`
* There should be another *`ResourceLink`* element for the booking database the same as the code above with changing the *`name, global`* attributes.
* import the project in IntelliJ or Eclipse.
* Or build the project using maven to get a jar file.
* Use any rest client like postman to test the end points for the applications.

### Important note
* **The JNDI datasource configurations are only applicable for tomcat server and change per any other application server.**