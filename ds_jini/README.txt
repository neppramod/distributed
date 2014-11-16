Note: Ignore Contact class (because this was from the template, we can remove it anytime)


1. It assumes mysql database with username:"root", password:"". Create a blank database called ds_jini.
$ mysql -u root
mysql> create database ds_jini;

2. To run the project install maven 3 and execute following commands.

$ mvn clean
#$ mvn package # Next command should call this
$ mvn jetty:run

or

$ mvn clean package jetty:run

1. You can import this maven projects in IDE like Intellij IDEA or (eclipse)
3. After the database tables are created, if you make any changes to the model files (under cs.ds.model packages), they would be reflected into the database

This is a spring web project. It runs on jetty web server (It is apache tomcat). It will run perfectly on apache tomcat. If we don't use web as the frontend, we can still use the data generation feature of hibernate.




To learn about the project, See the vertical structure of Contact structure. Like 
a. cs.ds.domain.Contact (this is where we put our model classes)
b. cs.ds.mvc.controller.ContactController (this interacts with our frontend web pages in webapp/WEB-INF/jsp/contacts). It uses other service layers like cs.ds.mvc.service.ContactServiceImpl and cs.ds.mvc.dao.ContactDAOImpl through their interfaces inside cs.ds.mvc.service.interfaces.ContactService and cs.ds.mvc.dao.ContactDAO respectively. Since these service layers use Generic abstract classes for all the CRUD operations. If you don't need anything more than CRUD operations, you just have to copy paste Contact files (service, dao etc) and just change the name.

Service classes seems like an overhead, but if we need to interact with more than one dao for some services, they abstract away the ugliness from the Controller class (which will be where we do most of our frontend stuff).
