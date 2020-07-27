package com.nasus.helloword.form;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Event implements Serializable {

    public Event(){
    }

    public Event(String title, String name){
        this.title = title;
        this.name = name;
    }

    private String title;

    private String name;

    private List<Event> items;

}


