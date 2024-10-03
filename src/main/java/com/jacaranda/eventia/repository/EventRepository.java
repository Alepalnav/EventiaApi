package com.jacaranda.eventia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jacaranda.eventia.model.Event;

public interface EventRepository extends JpaRepository<Event, Integer> {

}
