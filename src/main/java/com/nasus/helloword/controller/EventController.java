package com.nasus.helloword.controller;

import com.google.gson.JsonArray;
import com.liy.starter.HelloService;
import com.nasus.helloword.entity.EventJpa;
import com.nasus.helloword.exception.UserNotExistException;
import com.nasus.helloword.form.Event;
import com.nasus.helloword.service.EventsService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.*;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventsService eventsService;

    @Autowired
    private HelloService helloService;

    @ResponseBody
    @GetMapping("/starter")
    public String starter(){
        return helloService.sayHello("liy");
    }


    @GetMapping("/testa")
    public String getStudent() throws Exception {
        Event event = new Event();
        event.setTitle("title");
        event.setName("name");
        return event.getName();
    }

    @GetMapping("/list")
    public List<EventJpa> findEventList(){
        if(true){
            throw new UserNotExistException();
        }
        return eventsService.findEventList();
    }

    @PostMapping("/axiosPost/{a}/{b}")
    public String axiosPost(@PathVariable Integer a, @PathVariable Integer b, @RequestParam(value="name") String name, @RequestParam(value="age") Integer age){
        return a.toString() + b.toString() + name + age.toString();
    }

    @PostMapping("/axiosJsonPost/")
    public String axiosJsonPost(@RequestParam(value="name") String name, @RequestBody Event event){

        List<Event> items = event.getItems();

        var result = new StringJoiner(", ", "Hello ", "!");

        for(Event e: items){
            result.add(e.getName());
            result.add(e.getTitle());
        }

        return result.toString();
    }

    @PostMapping("/axiosModelPost/")
    // 当我们使用application / x-www-form-urlencoded时，Spring并不将其理解为RequestBody。因此，如果我们想要使用它，我们必须删除@RequestBody注释。
    public String axiosModelPost(Event event){
        return event.getTitle() + event.getName();
    }

    @PostMapping("/axiosAjaxPost/")
    public String axiosAjaxPost(@RequestBody Map<String, String> requestMap){
        return requestMap.get("type") + requestMap.get("title");
    }

    @GetMapping("/async")
    public String async(){
        eventsService.asyncMethod();
        return "async method";
    }

    /**
     * 随机抛出异常
     */
    private void randomException() throws Exception {
        Exception[] exceptions = { //异常集合
                new NullPointerException(),
                new ArrayIndexOutOfBoundsException(),
                new NumberFormatException(),
                new SQLException()};
        //发生概率
        double probability = 0.75;
        if (Math.random() < probability) {
            //情况1：要么抛出异常
            throw exceptions[(int) (Math.random() * exceptions.length)];
        } else {
            //情况2：要么继续运行
        }

    }

}

