package com.nasus.helloword.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class PropertiesBean {

    @Value("${com.nasus.author.name}")
    private String name;

    @Value("${com.nasus.article.title}")
    private String title;

    @Value("${com.nasus.doing}")
    private String desc;

}
