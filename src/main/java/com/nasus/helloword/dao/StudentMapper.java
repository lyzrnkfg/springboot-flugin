package com.nasus.helloword.dao;

import com.nasus.helloword.form.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {


    Student findStudentById(Integer id);

    List<Student> findStudentList();

    List<Student> searchStudentList(Student studentForm);

    void updateStudents(Student student);
}
