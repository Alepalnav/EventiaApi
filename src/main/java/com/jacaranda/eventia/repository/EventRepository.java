package com.jacaranda.eventia.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jacaranda.eventia.model.Event;
import com.jacaranda.eventia.model.User;

import java.util.List;


public interface EventRepository extends JpaRepository<Event, Integer> {

	Page<Event> findByAvailable(int available, Pageable pageable);

	List<Event> findByUserAndAvailable(User user, int available);
	
}
