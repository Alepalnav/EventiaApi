package com.jacaranda.eventia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jacaranda.eventia.model.Participation;
import com.jacaranda.eventia.model.ParticipationId;
import com.jacaranda.eventia.model.User;

import java.util.List;


public interface ParticipationRepository extends JpaRepository<Participation, ParticipationId> {
	
	List<Participation> findByUser(User user);

}
