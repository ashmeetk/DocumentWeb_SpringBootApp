# DocumentWeb_SpringBootApp
This Spring Web MVC project shows how to upload documents, save them in MySQL database, and download them when needed.

Database:
We use MySQL Database(projectdb) to perform link our Document java entity with document table in database.
Refer document.sql for all SQL statements.

Functionality:
To start the app, click Run as Spring Boot in STS.
To go to the form where we can upload documents, type url:http://localhost:8080/documentweb/displayUpload in chrome. 
On web we can upload any document, and clicking on confirm will show it in the same page(below).
You can also download any document shown in the table below.

Technicality:
LOGIC: We have specified implementation of all URL's in the DocumentController.java. The first url to be rendered is: displayUpload
VIEW: All the UI view is written in documentationUpload.jsp.
JPA: All the CRUD operations are performed on the MySQL database using DocumentRepository.java interface.
