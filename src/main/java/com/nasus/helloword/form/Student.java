package com.nasus.helloword.form;

import com.nasus.helloword.service.StudentService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Data
public class Student implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @NotEmpty(message = "name,master not empty")
    private String name;

    private Integer age;

    @Valid
    private List<Student> stuList;

}


