package com.nasus.helloword.controller;

import com.nasus.helloword.dao.DepartmentDao;
import com.nasus.helloword.dao.EmployeeDao;
import com.nasus.helloword.form.Department;
import com.nasus.helloword.form.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    @GetMapping("/emps")
    public String list(Model model){

        Collection<Employee> employeeDaoAll = employeeDao.getAll();

        model.addAttribute("emps", employeeDaoAll);

        // templates mori hui bingchuan
        // classpath:/templates/xxx.html
        return "emps/list";
    }

    @GetMapping("/emp")
    public String toAddPage(Model model) {
        //准备部门下拉框数据
        Collection<Department> departments = departmentDao.getDepartments();
        Employee employee = new Employee();
        employee.setDepartment(new Department());
        model.addAttribute("emp", employee).addAttribute("departments",departments);
        return "emps/add";
    }

    // springMvc自动将请求参数和入参对象的属性进行一一绑定， 要求请求参数的名字和javaBean入参的对象里面的属性名一致
    @PostMapping("/emp")
    public String add(Employee employee) {
        System.out.println(employee);
        //模拟添加到数据库
        employeeDao.save(employee);
        //添加成功重定向到列表页面
        // redirect: 表示重订向到一个地址 /代表当前项目的路劲
        // forward： 表示转发到一个地址
        return "redirect:/emps";
    }

    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable Integer id, Model model) {
        Employee employee = employeeDao.get(id);
        //准备部门下拉框数据
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("emp", employee).addAttribute("departments", departments);
        return "emps/add";
    }

    @PutMapping("/emp")
    public String update(Employee employee) {
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @DeleteMapping("/emp/{id}")
    public String delete(@PathVariable Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }


}
