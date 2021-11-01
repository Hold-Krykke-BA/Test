# Assignment 3 - Test - Booking System

## Contributors
- _[Rúni Vedel Niclasen - cph-rn118](https://github.com/Runi-VN)_
- _[Camilla Jenny Valerius Staunstrup - cph-cs340](https://github.com/Castau)_

## [Assignment description](./assignment-03.pdf)

We understood the task as:
- Implement missing classes, interfaces and methods
- Create a containerized MySQL database containing the data types using the given scripts
  - Create a migration to add the column `phonenumber` on customer 
- Create connection between the application and the database
- Test the system with the with unit and integration tests

## Process and stategy
We discussed whether to split up the assignment or do it in pairs. We decided that this was a good opportunity for peerprogramming, which we then did using screensharing on discord and teamviewer.  
We did not follow TDD in thsi assignment, as there were wuite many problems with the required frameworks for testing. But since the program itself was wuite simple we weren't that worried about the "big bang" testing strategy, which is why we ended up using that. Had we had the knowledge we have now regarding the frameworks, we would be more confident in following the TDD stragey. 


### Setup
We followed the provided code in the assignment repository ([commit](https://github.com/Hold-Krykke-BA/Test/commit/1a21b64df26c98dc6cb395f8d8ce458771d56ed5#diff-bbd6f8cf2e618b335ebcaa545470413e4db5304dc5ba229858d647983c8061d6)) and coded in Java. We used the supplied dependencies found in the `pom.xml` file but updated them as we found fit.

**To run our project:**  
- Clone or download this repository
- Pull this docker image `docker run -d --name mysql-test-db -e MYSQL_ROOT_PASSWORD=holdkrykke -p 3307:3306 mysql`
- Run [this script](https://github.com/Hold-Krykke-BA/Test/blob/main/Assignment3/scripts/ddl.sql) in the database
- You can then run the project from the [main.java](https://github.com/Hold-Krykke-BA/Test/blob/main/Assignment3/src/main/java/main/Main.java)
- Or test the tests found in [src/test](https://github.com/Hold-Krykke-BA/Test/tree/main/Assignment3/src/test/java)

## Result
| Our tests so far:  |      Database and generated test database:     | 
|----------|:-------------:|
|![image](https://user-images.githubusercontent.com/35559774/139650032-b5460836-d4c5-4902-bc86-9e2380df312d.png)
|![image](https://user-images.githubusercontent.com/35559774/139650116-f63cb086-d69b-429b-a2d9-dc541e49b6b8.png)
|  

## Reflection
- The Flyway framework was difficult to get working properly, but we managed to do it at the end. When it works, it's a nice framework for creating integration tests
- The assignment was a little unclear, especially regarding what functionality was to be tested. We followed the supplied examples in the code that was provided
- We used the frameworks `javafaker` and `mockito` for the tests that needed fake data or mocked classes and both worked very well and are frameworks we will be using form now on in `Java` projects.
