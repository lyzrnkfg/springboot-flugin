package com.nasus.helloword.form;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
public class SysUser {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String password;

}


