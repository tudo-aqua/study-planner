# Worked-out Example: StudyPlanner 
In this worked-out example, we present parts of the solution for the project _StudyPlanner_. The program _StudyPlanner_ is a software management tool that supports students to plan their curriculum and provides statistics about the progress and average grade.

An extended description of the project's requirements can be found in the task text below.

## Task Text
The task is to develop a software management tool for planning a curriculum. The user should be able to manage their courses, which means to create, modify, and delete a course and especially assign a course to a semester. 
A course always has a name, exam date, and grade.
For the planning of the remaining curriculum, the toll should provide statistical data like the number of credit points for a semester, the number of collected credit points, and the current average grade.
The program must store all entered data and load them when the program is launched.


## Requirement Model

 
### Use Case Diagram
![UseCase_Syntax](uploads/279ba4e2be22e323bdd886b9d6cbdf3c/UseCase_Syntax.png)

### Activity Diagrams
- Activity Diagram 1: Create Course ![Requirements_Model_-_Activity_Diagram__Create_Course](uploads/74529c4ed2cfca5c3460690e3400c519/Requirements_Model_-_Activity_Diagram__Create_Course.png)
- Activity Diagram 2: Show Statistics ![Requirements_Model_-_Activity_Diagram__Show_Statistics](uploads/6d0268245ac7a035dd8301b3b587c3d9/Requirements_Model_-_Activity_Diagram__Show_Statistics.png)

### Domain Model
![Problembereichsmodell](uploads/daeadc68a5a5cd6cb2710fcffb2c461b/Problembereichsmodell.png)


## Analysis Model
 
### Structure Model
![Strukturmodell](uploads/2c7805f2ace3298b17d1bff0f9ec0fff/Strukturmodell.png)

### Sequence Diagrams
- Sequence Diagram 1: Create Course ![Analysis_Model_-_Sequence_Diagram__Create_Course](uploads/26184d2ee3fc1a664e026c94cc4f75dd/Analysis_Model_-_Sequence_Diagram__Create_Course.png)
- Sequence Diagram 2: Show Statistics ![Analysis_Model_-_Sequence_Diagram__Show_Statistics](uploads/ba7d3af36a809bafa4b018ba1284d452/Analysis_Model_-_Sequence_Diagram__Show_Statistics.png)

### Product Description (only available in German)

[Produktbeschreibung_StudyPlanner.pdf](uploads/0cf54a7bec62ccb25eb0172763b060c6/Produktbeschreibung_StudyPlanner.pdf)
 
## Design Model / Javadoc (only available in German)

The documentation of the program code with Javadoc can be found [here](https://sopra-ci.cs.tu-dortmund.de/studyplanner/javadoc/).

 
## Unit Tests / Test Documentation (only available in German)
The documentation of the unit tests code with Javadoc can be found [here](https://sopra-ci.cs.tu-dortmund.de/studyplanner/testjavadoc/).

## Product test (Partial)

### Example for a product test for the use case _Create Course_
| Use Case  | Create Course |
| ------ | ------ |
| Commit-Hash | f27f695ae5a37399354f0af44d80d9bfe7201b54 |
| Expected result | Nach der Durchführung soll ein neues Modell mit entsprechenden Daten im zugewiesenen Semester sein im Programm vorhanden sein|
| Needed inputs | Name, creadit points, and date of the exam of the course and the semester in which the course will be visited|
| Special cases | Invalid inputs like empty course name and credit points less than 1 are not possible. An error message is displayed in this case.
| Errors | none | 
| Test result | ✓ |
 
## The finished product
![StudyPlanner_Overview_English](uploads/48602aae2e45dd483c2d13e412083528/StudyPlanner_Overview_English.png)

The program can be downloaded from the repository at branch **master**.

Repository Link: https://sopra-gitlab.cs.tu-dortmund.de/beispielprojekt/studyplanner.git
