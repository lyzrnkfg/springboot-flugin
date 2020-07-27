package com.nasus.helloword.service;

import com.nasus.helloword.form.Student;

import java.util.List;

public interface StudentService {

    Student findStudentById(Integer id);

    List<Student> findStudentList();

    List<Student> findSearch(Student studentForm);

    void updateStudents(Student studentForm);
}
