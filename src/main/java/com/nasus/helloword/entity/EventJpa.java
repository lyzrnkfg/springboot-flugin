package com.nasus.helloword.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class EventJpa {

    // test gitmoji
    // 自增 id
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private Integer age;

}
