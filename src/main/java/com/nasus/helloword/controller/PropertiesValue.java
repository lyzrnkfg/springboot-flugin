package com.nasus.helloword.controller;

import com.nasus.helloword.bean.PropertiesBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class PropertiesValue {

    @Autowired
    private PropertiesBean propertiesBean;

    @GetMapping("/getInfo")
    public PropertiesBean getInfo(){
        return propertiesBean;
    }

}