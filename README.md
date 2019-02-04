## LAB 4 - Student info app

This application displays a list of students, and allows a user to see a list of classes that student is taking.

This application is built to allow students to learn about navigating to new activities in an Android app, and passing data between those activities (intents).

### STRUCTURE

- Adaptor

StudentAdapter.java converts the Database information into a more usable format.

- DataBase

Database.java employs the classes in the model folder to create students and courses, and then associates a number of random courses with each student.
In lab 5, this will be re-factored to use Localstorage instead of instantiating the same thing every time.

- Display

MainActivity.java and StudentDetailActivity.java are tied to the two different activities. They each bring up their associated .xml files.

- Model

CourseModel.java and StudentModel.java are objects used by the database to describe each course/student.