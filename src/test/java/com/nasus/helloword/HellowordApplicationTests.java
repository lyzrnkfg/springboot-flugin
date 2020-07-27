package com.nasus.helloword;

import com.nasus.helloword.form.Event;
import com.nasus.helloword.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@SpringBootTest
class HellowordApplicationTests {

    // 操作字符串比较多单独拿出来的k-v字符串的
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    // k-v 对象的
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpAdmin amqpAdmin;


    @Test
    public void contextLoadRabbits(){
        //rabbitTemplate.send();
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "first msg");
        map.put("data", Arrays.asList("text", 123, true));
        //发送消息
        rabbitTemplate.convertAndSend("exchange.direct", "atguigu.news", map);

        //接收消息
        Object o = rabbitTemplate.receiveAndConvert("atguigu.news");
        System.out.println(o.getClass());  //class cn.edu.ustc.springboot.bean.Book
        System.out.println(o);
    }


    @Test
    public void test01(){

        stringRedisTemplate.opsForValue().append("msg", "hello");

        Event event = new Event("title", "ly");

        redisUtil.set("event", event);

    }

    @Test
    void contextLoads() {
    }

}
