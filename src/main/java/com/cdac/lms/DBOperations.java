package com.cdac.lms;

import com.cdac.pojos.Book;
import com.cdac.pojos.Course;
import com.cdac.pojos.Student;
import com.cdac.utils.DBConnection;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

public class DBOperations {

  private Session session;

  private Session getSession() {
    try {
      session = DBConnection.getSessionFactory().openSession();
    } catch (Exception e) {
      System.err.println(e);
    }

    return session;
  }

  private void closeSession(Session session) {
    if (session != null) {
      try {
        session.close();
      } catch (Exception e) {
        System.err.println(e);
      }
    }
  }

  // ------------------------------------------------------------------------
  // START Book related methods
  // ------------------------------------------------------------------------
  public void insertBook(String title, String author, float price) {
    session = getSession();

    session.beginTransaction();
    Book bk = new Book();
    bk.setTitle(title);
    bk.setAuthor(author);
    bk.setPrice(price);
    session.save(bk);
    session.getTransaction().commit();

    closeSession(session);
  }

  public void getAllBooks() {
    session = getSession();

    List<Book> bookList;
    bookList = session.createQuery("from Book", Book.class).list();
    for (Book bk : bookList) {
      System.out.println("-------------------------------------------------");
      System.out.println("Id: " + bk.getId());
      System.out.println("Title: " + bk.getTitle());
      System.out.println("Author: " + bk.getAuthor());
      System.out.println("Price: " + bk.getPrice());
    }

    closeSession(session);
  }

  public void updateBook(int id, String title, String author, float price) {
    session = getSession();

    Book bk;
    bk = session.get(Book.class, id);
    bk.setTitle(title);
    bk.setAuthor(author);
    bk.setPrice(price);

    session.beginTransaction();
    session.update(bk);
    session.getTransaction().commit();

    closeSession(session);
  }

  public void deleteBook(int id) {
    session = getSession();

    Book bk;
    bk = session.get(Book.class, id);

    session.beginTransaction();
    session.delete(bk);
    session.getTransaction().commit();

    closeSession(session);
  }
  // ------------------------------------------------------------------------
  // END Book related methods
  // ------------------------------------------------------------------------

  // ------------------------------------------------------------------------
  // START Student related methods
  // ------------------------------------------------------------------------
  public int insertStudent(String name, String email, Course course) {
    int rollno = 0;
    session = getSession();

    session.beginTransaction();
    // create new student
    Student std = new Student();
    std.setName(name);
    std.setEmail(email);
    std.setCourse(course);
    // save student into the database
    rollno = (int) session.save(std);    
    session.getTransaction().commit();

    closeSession(session);
    
    // also update the course relationship with student
    List<Student> ls = new ArrayList<>();
    ls.add(std);
    updateCourse(std.getCourse().getId(), std.getCourse().getName(), ls);
    
    return rollno;
  }

  public void getAllStudents() {
    session = getSession();

    List<Student> studentsList;
    studentsList = session.createQuery("from Student", Student.class).list();
    for (Student std : studentsList) {
      System.out.println("-------------------------------------------------");
      System.out.println("Roll no: " + std.getRollno());
      System.out.println("Name: " + std.getName());
      System.out.println("Email: " + std.getEmail());
      System.out.println("Course: " + std.getCourse());
    }

    closeSession(session);
  }
  
  public Student getStudentByRollNo(int rollno) {
    Student std = null;
    session = getSession();
    
    std = session.get(Student.class, rollno);
    
    closeSession(session);
    return std;
  }

  public void updateStudent(int rollno, String name, String email) {
    session = getSession();

    Student std;
    std = session.get(Student.class, rollno);
    std.setName(name);
    std.setEmail(email);

    session.beginTransaction();
    session.update(std);
    session.getTransaction().commit();

    closeSession(session);
  }

  public void deleteStudent(int rollno) {
    session = getSession();

    Student std;
    std = session.get(Student.class, rollno);

    session.beginTransaction();
    session.delete(std);
    session.getTransaction().commit();

    closeSession(session);
  }
  // ------------------------------------------------------------------------
  // END Student related methods
  // ------------------------------------------------------------------------
  
  // ------------------------------------------------------------------------
  // START Course related methods
  // ------------------------------------------------------------------------
  public void insertCourse(String name) {
    session = getSession();

    session.beginTransaction();
    Course course = new Course();
    course.setName(name);
    session.save(course);
    session.getTransaction().commit();

    closeSession(session);
  }

  public void getAllCourses() {
    session = getSession();

    List<Course> coursesList;
    coursesList = session.createQuery("from Course", Course.class).list();
    for (Course course : coursesList) {
      System.out.println("-------------------------------------------------");
      System.out.println("Course Id: " + course.getId());
      System.out.println("Course Name: " + course.getName());
    }

    closeSession(session);
  }
  
  public Course getCourseById(int id) {
    Course course = null;
    session = getSession();
    
    course = session.get(Course.class, id);
    
    closeSession(session);
    
    return course;
  }

  public void updateCourse(int id, String name, List<Student> ls) {
    session = getSession();

    Course course;
    course = session.get(Course.class, id);
    course.setName(name);
    course.setStudentsList(ls);

    session.beginTransaction();
    session.update(course);
    session.getTransaction().commit();

    closeSession(session);
  }

  public void deleteCourse(int id) {
    session = getSession();

    Course course;
    course = session.get(Course.class, id);

    session.beginTransaction();
    session.delete(course);
    session.getTransaction().commit();

    closeSession(session);
  }
  // ------------------------------------------------------------------------
  // END Course related methods
  // ------------------------------------------------------------------------

}
