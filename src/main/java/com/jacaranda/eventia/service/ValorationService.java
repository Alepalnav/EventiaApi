package com.jacaranda.eventia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jacaranda.eventia.dto.ParticipationDTO;
import com.jacaranda.eventia.dto.RatingDTO;
import com.jacaranda.eventia.exception.ExceptionValueNotRight;
import com.jacaranda.eventia.model.Event;
import com.jacaranda.eventia.model.Participation;
import com.jacaranda.eventia.model.ParticipationId;
import com.jacaranda.eventia.model.Rating;
import com.jacaranda.eventia.model.User;
import com.jacaranda.eventia.repository.EventRepository;
import com.jacaranda.eventia.repository.UserRepository;
import com.jacaranda.eventia.repository.ValorationRepository;

@Service
public class ValorationService {
	
	@Autowired
	ValorationRepository valorationRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	EventRepository eventRepository;
	
	public User getUser(Integer id) {
		return userRepository.findById(id).orElse(null);
	}
	
	public Event getEvent(Integer id) {
		return eventRepository.findById(id).orElse(null);
	}
	
	public RatingDTO add(RatingDTO ratingDTO) {	
		User user = userRepository.findById(ratingDTO.getUser()).orElse(null);
		Event event = eventRepository.findById(ratingDTO.getEvent()).orElse(null);
		ParticipationId id=null;
		if(user!=null&&event!=null) {
			id = new ParticipationId(user.getId(),event.getId());
		}else {
			id = new ParticipationId(null,event.getId());
		}
		
		
		Rating oldParticipation = valorationRepository.findById(id).orElse(null);
		if(oldParticipation!=null) {
			try {
				Rating rating = new Rating(getUser(ratingDTO.getUser()),getEvent(ratingDTO.getEvent()),ratingDTO.getStars(),ratingDTO.getComment()); 
				valorationRepository.save(rating);
				RatingDTO newRatingDTO = new RatingDTO(ratingDTO.getUser(),ratingDTO.getEvent(),ratingDTO.getStars(),ratingDTO.getComment()); 
				return newRatingDTO;				
			}catch(Exception e) {
				throw new ExceptionValueNotRight(e.getMessage());
			}	
		}else {
			try {
				Rating rating = new Rating(getUser(ratingDTO.getUser()),getEvent(ratingDTO.getEvent()),ratingDTO.getStars(),ratingDTO.getComment()); 
				valorationRepository.save(rating);
				RatingDTO newRatingDTO = new RatingDTO(ratingDTO.getUser(),ratingDTO.getEvent(),ratingDTO.getStars(),ratingDTO.getComment()); 
				return newRatingDTO;				
			}catch(Exception e) {
				throw new ExceptionValueNotRight(e.getMessage());
			}			
		}
	}

}
