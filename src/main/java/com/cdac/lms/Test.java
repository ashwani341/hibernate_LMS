package com.cdac.lms;

import com.cdac.pojos.Course;
import com.cdac.pojos.Student;
import java.util.List;

public class Test {
  
  public static void main(String[] args) {
    
    DBOperations dbops = new DBOperations();

//    dbops.insertBook("test", "tester", 1500);
//    dbops.updateBook(2, "Java", "James Gosling", 1500);
//    dbops.deleteBook(3);    
//    dbops.getAllBooks();

//    dbops.insertStudent("test", "test@gmail.com", );
//    dbops.updateStudent(1, "Shubham Rao", "shubham@gmail.com");
//    dbops.deleteStudent(3);
//    dbops.getAllStudents();

//    dbops.insertCourse("PGDAC");
//    dbops.insertCourse("DBDA");
//    dbops.insertCourse("DMC");
//    dbops.insertCourse("DITISS");
//    dbops.updateCourse(3, "PGDMC");
//    dbops.deleteCourse(5);
//    dbops.getAllCourses();


    Course course = dbops.getCourseById(1);
    int rollno = dbops.insertStudent("Veer", "veer@gmail.com", course);
    Student std = dbops.getStudentByRollNo(rollno);
    System.out.println(std);
    
  }
  
}
