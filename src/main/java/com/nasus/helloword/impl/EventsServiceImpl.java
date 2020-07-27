package com.nasus.helloword.impl;

import com.nasus.helloword.entity.EventJpa;
import com.nasus.helloword.form.Event;
import com.nasus.helloword.repository.EventsRepository;
import com.nasus.helloword.service.EventsService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventsServiceImpl implements EventsService {

    @Autowired
    private EventsRepository eventsRepository;


    @Override
    public List<EventJpa> findEventList() {
        return eventsRepository.findAll();
    }

    @Override
    @RabbitListener(queues = "atguigu.news")
    public void receive(Event event) {
        System.out.println(event);
    }

    // 异步任务
    @Override
    @Async
    public void asyncMethod() {
        int i = 1 / 0;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("method loading......");
    }

    // 定时任务
    // cron表达式：
    //second(秒), minute（分）, hour（时）, day of month（日）, month（月）, day of week（周几)
    @Override
    @Scheduled(cron = "0 * * ? * 3")
    public void scheduledMethod() {
        System.out.println("scheduled method");
    }
}
