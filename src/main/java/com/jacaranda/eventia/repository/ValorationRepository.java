package com.jacaranda.eventia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jacaranda.eventia.model.Event;
import com.jacaranda.eventia.model.ParticipationId;
import com.jacaranda.eventia.model.Rating;
import com.jacaranda.eventia.model.User;

public interface ValorationRepository extends JpaRepository<Rating, ParticipationId>{
	
	List<Rating> findByUser(User user);
	
	boolean existsByEventAndUser(Event event, User user);

}
