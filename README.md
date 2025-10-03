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
* Application takes username,movie ids and corresponding rental days as input
* Application validates input provided by user
* If any input is invalid application throws exception and flow is terminated
* If inputs are valid, the application calculates the charge and the frequent enter points for each movie
* The application passes the information to the statement formatter
* The Statement formatter generated formatted statement for the customer

For better code flow understanding we can check below flow diagram

<img src="movie-rental-flow-diagram.png" alt="movie-rental-flow-diagram" width="700" height="600">


## Prerequisites
To build and run the application we need following tools
* JDK 21
* Maven 3.9.4
* Intellij
* GIT client (Optional)

## Steps To Build and Run Code From IntelliJ
* Clone the repo https://github.com/vivekdubeydeveloper/movie-rental using git client or download the zip
* if you have downloaded the zip, unzip it you will get movie-rental-master folder.This step is not required if you are cloning repo.
* Open the project in Intellij.
* Click in Intellij File->Project Structure menu,select project SDK 21,module SDK 21 and SDK 21
* Apply the setting.
* You need to set JAVA_HOME variable path of JDK 21 if it is not set already.You can set either in user environment variable or if you want to set temporarily from command prompt you can use below command
   ```
   set JAVA_HOME="<Path of JDK21>"
   ```

* Open terminal in intellij,run below command to clean,build and run the test cases,before running the below command make sure your JAVA_HOME variable is pointing the path of JDK 21.
   ```
   mvn clean install
   ```
  
* Jacoco plugin is configured in pom so it will generate code coverage html file in path <projectbasefolder>/target/site/jacoco/index.html.Go inside the folder and open the file in any web browser(Chrome,Firefox,IE) to see the test coverage report.
* For only run the test cases use the below command

   ```
   mvn test
   ```
* For running test case with report generation
   ```
   mvn verify
   ```
* For running the code we can run main method of Main class from Intellij, we can change the input there for experimenting.

## Steps To Build and Run Code Outside of IntelliJ

* GO in project root directory from command prompt
* You need to set JAVA_HOME variable path of JDK 21 if it is not set already.You can set either in user environment variable or if you want to set temporarily from command prompt you can use below command
   ```
   set JAVA_HOME="<Path of JDK21>"
   ```
* For clean,build and run the test cases,run the below command from command prompt
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
   cd <path to targetf older>
   java -cp movie-rental-1.0-SNAPSHOT.jar com.etraveli.movierental.Main
   ```    
* We can always change the input in main method and run the code, or we can check the test cases 

## Navigate The Code
* We can navigate the code in Intellij after build from Main class main method.
* The Flow diagram in description section and below sequence diagram are helpful in navigation and understanding of the code
  <img src="movie-rental-sequence-diagram.png" alt="movie-rental-sequence-diagram">
* If you want to understand the class structure we can generate class diagram from IntelliJ, right click on the package com.etraveli.movierental,in the diagram menu click on the show diagram submenu. It will show class digram, we can select fields,method etc. 

## What I Refacotred And Why


