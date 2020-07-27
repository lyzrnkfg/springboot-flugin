package com.nasus.helloword.repository;

import com.nasus.helloword.entity.EventJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface EventsRepository extends JpaRepository<EventJpa, Integer> {


}




