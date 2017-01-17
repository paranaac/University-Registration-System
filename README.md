# University-Registration-System
Databases Project #1 - CLI univeristy registration system using a database.

Implement a University Registration System in Java using JDBC

Database users:

Staff: These users can add and delete courses and students, and register and drop courses for students. At the end of the semester, grades should be uploaded.

# The following real-world constraints need to be enforced:

Students cannot register for a class twice for the same year and semester.

If a course is deleted, all registrations of that course need to be deleted too. (delete from registration table first, then delete it from the course table.)

If a student is deleted from the system, all registrations of that student need to be deleted too. (delete from registration table first, then delete it from the student table.)

# Options

1. Add a course

2. Delete a course

3. Add a student

4. Delete a student

5. Register a course

6. Drop a course

7. Check student registration

8. Upload grades

9. Check grade

10. Quit

# Notes

Delete a course by giving a course code

Delete a student by giving a student SSN

Drop a course by giving code, SSN, year, semester

Check a student’s registration by giving either student name or student SSN

Upload grades by giving code, year, semester (then input grades for every student who registers the course)

Check grade by giving code, student SSN or student name, year, semester

