package com.nasus.helloword.impl;

import com.nasus.helloword.dao.StudentMapper;
import com.nasus.helloword.dao.SysUserRoleMapper;
import com.nasus.helloword.form.Student;
import com.nasus.helloword.form.SysUserRole;
import com.nasus.helloword.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private SysUserRoleMapper userRoleMapper;


    /**
     * 根据 id 查询 Student
     * @param id
     * @return
     */
    @Override
    public Student findStudentById(Integer id) {
        return studentMapper.findStudentById(id);
    }

    @Override
    public List<Student> findStudentList() {
        return studentMapper.findStudentList();
    }

    @Override
    @Cacheable(cacheNames = {"student"}, keyGenerator = "myKeyGenerator")
    public List<Student> findSearch(Student studentForm) {
        return studentMapper.searchStudentList(studentForm);
    }

    @Override
    public void updateStudents(Student studentForm) {
        List<Student> studentList = studentForm.getStuList();
        for (Student student : studentList){
            studentMapper.updateStudents(student);
        }
    }

}
