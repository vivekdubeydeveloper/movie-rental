# Refactoring Java

The code creates an information slip about movie rentals.
Rewrite and improve the code after your own liking.

Think: you are responsible for the solution, this is a solution you will have to put your name on.


## Handing in the assignment

Reason how you have been thinking and the decisions you took. 
You can hand in the result any way you feel (git patch, pull-request or ZIP-file).
Note: the Git history must be included.

## Description

This application generates a rental statement for a customer based on the movies they have rented and the duration of the rental.
This application performs following steps
* The application takes customer name,movie ids and corresponding rental days as input
* The application validates input provided by user
* If any inputs are invalid application throws exception and flow is terminated
* If input are valid, the application calculates the charge and the frequent enter points for each movie
* The application uses charges and frequent enter points data and generate formatted rental statement

For better code flow understanding we can check below flow diagram

<img src="movie-rental-flow-diagram.png" alt="movie-rental-flow-diagram" width="700" height="600">


## Prerequisites
To build and run the application we need following tools
* JDK 21(https://www.oracle.com/se/java/technologies/downloads/#java21)
* Maven 3.9.4(https://maven.apache.org/download.cgi)(I have tested for this version,It should work with other versions also)
* Intellij(https://www.jetbrains.com/idea/download/?section=windows)
* GIT client (Optional)(https://git-scm.com/downloads)
**If the above software packages are not installed on your system, install them.**

## Before Build And Run
* Open command prompt on your machine 
* Check your Java version using below command
```
   java --version
   ```
* Check your maven version using below command,it will tell you java version for maven
```
   mvn --version
   ```
* If your java version is not 21 for maven set JAVA_HOME environment variable,if you want to set from command prompt temporarily use the below command
```
   set JAVA_HOME="<Path of JDK21>"
   ```
* You can refer the link https://confluence.atlassian.com/doc/setting-the-java_home-variable-in-windows-8895.html for setting JAVA_HOME
* Once JAVA_HOME is set run again mvn --version command to check if Java 21 has been updated for maven.
* If everything is working fine till here,now we can move forward for project setup.

**Note:The above steps,I have tried on Windows Machine,similar steps will work for other OS also.**

## Download/Checkout the project 
* Open the url https://github.com/vivekdubeydeveloper/movie-rental in web browser
* Click on the code button
* If you are using GIT client, open git bash in the path where you want to check out the project,run the below command
```
   git clone https://github.com/vivekdubeydeveloper/movie-rental.git
   ```
* It will download movie-rental project in your folder
* If you are not using git client when you click on code button, you will see Download Zip menu, click on the Download Zip menu.
* You will get movie-rental-maser.zip file, unzip it, you will get movie-rental-maser folder


## Steps To Build and Run Code From IntelliJ
* Open the project in Intellij.
* Click in Intellij File->Project Structure menu,select project SDK 21,module SDK 21 and SDK 21
* Apply the setting.
* Open terminal in intellij,run below command it will clean,build and run the test cases(make sure JAVA_HOME is pointing to JDK 21).
   ```
   mvn clean install
   ```
* Jacoco plugin is configured in pom so it will generate code coverage html file on mentioned path <projectfolder>/target/site/jacoco/index.html.Go inside the folder and open the file in any web browser(Chrome,Firefox,IE) to see the test coverage report.
* For only run the test cases use the below command
   ```
   mvn test
   ```
* For running test case with report generation
   ```
   mvn verify
   ```
* Once build is successful,from the maven panel you can run Sync all the project to sync source code with downloaded libs.You run Main class from Intellij.

## Steps To Build and Run Code Outside of IntelliJ

* Open command prompt and cd to unzip project folder movie-rental-maser.
* You need to verify if JDK,JAVA_HOME and maven is working as per section **Before Build And Run**.
   
* Run the below command from command prompt,It will clean,build and run the test cases
   ```
   mvn clean install
   ```
* For only run the test cases use the below command
   ```
   mvn test
   ```
* For running test case with report generation
   ```
   mvn verify
   ```  
* For running the code,run the below commands from command prompt( You need to go inside the target folder,jar file should be there if you have run the above commands)
   ```
   cd <path to target folder>
   java -cp movie-rental-1.0-SNAPSHOT.jar com.etraveli.movierental.Main
   ```    
  
* We have configured maven-shade-plugin in pom so it will generate fat jar with all the dependencies so we can run the jar file using below command
 ```
   cd <path to target folder>
   java -jar movie-rental-1.0-SNAPSHOT.jar
   ```  
* We can always change the input in main method,after that we can run mvn clean install to build the code. We can use above commands to run the code  

## Navigate The Code
* We can navigate the code in Intellij after build from Main class main method.
* The Flow diagram in description section and below sequence diagram are helpful in navigation and understanding of the code
  <img src="movie-rental-sequence-diagram.png" alt="movie-rental-sequence-diagram">
* If you want to understand the class structure we can generate class diagram from IntelliJ, right click on the package com.etraveli.movierental,in the diagram menu click on the show diagram submenu. It will show class digram, we can select fields,method etc. 

## What I Refactored And Why
In this section I have explained what change I made and why? Why section is describing the current benefits(applicable for this codebase) and future benefits as the codebase scales.

### What:
* Kept classes in packages instead of default package

### Why:
* Packages group related functionality together, which significantly improves code navigation, understanding, and maintenance.
* Packages promote loosely coupled code with high cohesion.
* Packages establish modularity, making the code easily reusable in other projects or modules.
* Package reduce the possibility of Class name conflicts.

### What:
* Used maven as build tool

### Why:
* While using javac for compilation and java for execution is feasible for small projects, this manual approach is unsustainable and inefficient for enterprise-scale development.
* Maven is a project management and build automation tool which makes developer life much easier.
* We can compile the code,run the test cases,package(create jar,war) and run the project.
* We can easily manage the dependencies of 3rd party libraries including transitive dependencies.
* Maven project structure is consistent and clear.
* There are many plugins which can help in different activity for example jacoco for code coverage report generation.
* It is easy to integrate with CI/CD pipelines.
* There are plugins which can help in 3rd party libs vulnerability detection.

### What:
* Refactored RentalInfo class

### Why:
* This was kind of god class,which violated the Single Responsibility Principle (SRP) by concurrently managing data access (DAO), core business logic (charge calculation), and statement formatting.This was example of bad design. 
* Created MovieDAO class to handle DAO logic.
* For movie charge and frequent enter point calculation,there are 3 kinds of movies(New,Regular,Children) so 3 different logic for calculation.This is good use case for strategy design pattern.I have implement strategy design pattern using interface RentalChargeService and 3 concrete implementations ChildrenMoiveChargeService,NewMoiveChargeService and RegularMoiveChargeService.Moved the calculation logic in appropriate strategy implementation classes.
* Implemented RentalChargeServiceResolver interface and implementation class to get appropriate Strategy implementation based on movie type.
* Implemented StatementFormatter service which will generate Formatted rental statement.
* These classes are following SOLID principle and natural design patterns
* These implementations represent the philosophy of loose coupling and high cohesion
* These classes are following Interface first philosophy
* Now the question is what RentalInfo class is doing? this class has been renamed with RentalStatementServiceImpl (following spring naming convention),using above-mentioned classes to fetch calculation result and pass it to statement formatter for formatting.

### What:
* Added RentalStatementProxyService for input validation

### Why:
* There was no class for input validation.Added a proxy class which will help in input validation.
* It validates inputs with the help MovieValidator and CustomerValidator,if inputs are invalid throws exception else calls business logic
* Added ValidationErrors enum which is central and more maintainable place for error messages
* Separate crosscutting concerns(input validation) from business logic
* Promotes loose coupling,SRP and Open/Close principle


### What:
* Implemented records for model

### Why:
* Records (Java 17 feature) are immutable data classes.
* Which are very good for keeping immutable data.
* We do not need to write boilerplate code for getter setter etc
* There are 2 enum classes InputType and MovieType which are helpful for model classes
* Instead of keeping movieType as string implemented is as enum which is robust and less error-prone

### What:
* Implemented custom exceptions

### Why:
* Implemented custom exception which are more helpful and readable to detect the exception.

### What:
* Implemented interactive input from user

### Why:
* The main class was using hard coded customer data to call the business logic.
* Implemented the class where we can take input from user, now we have 2 options either we can take hard coded input or we can take interactive input from user.
* For switching the input classes we just need to change input type in main method.
* Promotes loose coupling,SRP and Open/Close principle 

### What:
* Write unit test cases using junit 5

### Why:
* Testing is one of the important aspect of the code.Written unit test case using Junit 5.
* Test cases covers all the scenario and code coverage is 100%.
* If code changes it will help in early error detection.

### What:
* Logging using log4j2

### Why:
* Logging is one of the important aspect of the code.Used log4j2 logging.
* Logging helps in error detection when application is deployed.
* Log42 is currently the fastest logging service in java

### What:
* Added JavaDoc in code

### Why:
* Added javadocs on class as well as method level,Which will help in understanding the api code