package com.cdac.pojos;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "courses")
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(length = 50, nullable = false)
  private String name;
  
  @OneToMany
  private List<Student> studentsList;

  public List<Student> getStudentsList() {
    return studentsList;
  }

  public void setStudentsList(List<Student> studentsList) {
    this.studentsList = studentsList;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Course{" + "id=" + id + ", name=" + name + '}';
  }

}
