package com.nasus.helloword.service;

import com.nasus.helloword.entity.EventJpa;
import com.nasus.helloword.form.Event;

import java.util.List;

public interface EventsService {
    List<EventJpa> findEventList();

    void receive(Event event);

    void asyncMethod();

    void scheduledMethod();
}
