package com.jacaranda.eventia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.jacaranda.eventia.dto.EventDTO;
import com.jacaranda.eventia.dto.ParticipationDTO;
import com.jacaranda.eventia.exception.ExceptionValueNotRight;
import com.jacaranda.eventia.model.Event;
import com.jacaranda.eventia.model.Participation;
import com.jacaranda.eventia.model.ParticipationId;
import com.jacaranda.eventia.model.User;
import com.jacaranda.eventia.repository.EventRepository;
import com.jacaranda.eventia.repository.ParticipationRepository;
import com.jacaranda.eventia.repository.UserRepository;

@Service
public class ParticipationService {
	
	@Autowired
	ParticipationRepository participationRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	EventRepository eventRepository;
	
	EventService eventService;
	
	public User getUser(Integer id) {
		return userRepository.findById(id).orElse(null);
	}
	
	/**
	 * Obtiene un producto con un id
	 * @return el producto con el id especificado
	 */
	
	public Event getEvent(Integer id) {
		return eventRepository.findById(id).orElse(null);
	}
	
	public List<Participation> getEventsByUser(Integer id){
		User user = getUser(id);
		return participationRepository.findByUser(user);
	}
	
//	public void participate(String id) {
//		Integer id2 = Integer.valueOf(id);
//		Event event = getEvent(id2);
//		if(event.getParticipants()<event.getMax_participant()) {			
//			EventDTO eventDTO = new EventDTO(event.getId(),event.getUser().getId(),event.getTitle(),event.getDescrip(),event.getDate_start(),event.getDate_finish(), event.getHour_start(), event.getHour_finish(),event.getPlace(),event.getCategory(),event.getMax_participant(),event.getParticipants()+1,event.getAvailable());
//			eventService.edit(id, eventDTO);
//		}
//		
//	}
	
	public ParticipationDTO add(ParticipationDTO participationDTO) {	
		User user = userRepository.findById(participationDTO.getUser()).orElse(null);
		Event event = eventRepository.findById(participationDTO.getEvent()).orElse(null);
		ParticipationId id=null;
		if(user!=null&&event!=null) {
			id = new ParticipationId(user.getId(),event.getId());
		}else {
			id = new ParticipationId(null,event.getId());
		}
		
		
		Participation oldParticipation = participationRepository.findById(id).orElse(null);
		if(oldParticipation!=null) {
			try {
				String idString = Integer.toString(participationDTO.getEvent());
				event = new Event(participationDTO.getEvent(), user, event.getTitle(), event.getDescrip(), event.getDate_start(), event.getDate_finish(), event.getHour_start(), event.getHour_finish(), event.getPlace(),event.getCategory(),event.getMax_participant(),event.getParticipants(),event.getAvailable());
				eventRepository.save(event);
				Participation participation = new Participation(getUser(participationDTO.getUser()),getEvent(participationDTO.getEvent())); 
				participationRepository.save(participation);
				ParticipationDTO newParticipationDTO = new ParticipationDTO(participation.getUser().getId(),participation.getEvent().getId()); 
				return newParticipationDTO;				
			}catch(Exception e) {
				throw new ExceptionValueNotRight(e.getMessage());
			}	
		}else {
			try {
				Integer participantes = event.getParticipants() + 1;
				event = new Event(participationDTO.getEvent(), user, event.getTitle(), event.getDescrip(), event.getDate_start(), event.getDate_finish(), event.getHour_start(), event.getHour_finish(), event.getPlace(),event.getCategory(),event.getMax_participant(),participantes,event.getAvailable());
				eventRepository.save(event);
				Participation participation = new Participation(getUser(participationDTO.getUser()),getEvent(participationDTO.getEvent())); 
				participationRepository.save(participation);
				return participationDTO;				
			}catch(Exception e) {
				throw new ExceptionValueNotRight(e.getMessage());
			}			
		}
	}
	
	public ParticipationDTO delete(Integer idUser, Integer idEvent) {
		User user = userRepository.findById(idUser).orElse(null);
		Event event = eventRepository.findById(idEvent).orElse(null);
		ParticipationId id=null;
		if(user!=null&&event!=null) {
			id = new ParticipationId(user.getId(),event.getId());
		}else {
			id = new ParticipationId(null,event.getId());
		}
		
		Participation participation = participationRepository.findById(id).orElse(null);

		if(participation!=null) {
			try {
				event = new Event(idEvent, user, event.getTitle(), event.getDescrip(), event.getDate_start(), event.getDate_finish(), event.getHour_start(), event.getHour_finish(), event.getPlace(),event.getCategory(),event.getMax_participant(),event.getParticipants()-1,event.getAvailable());
				eventRepository.save(event);
				participationRepository.delete(participation);;
				ParticipationDTO participationDTO = new ParticipationDTO(participation.getUser().getId(),participation.getEvent().getId());
				return participationDTO;				
			}catch(Exception e) {
				return null;
			}
		}else {
			return null;
		}
	}

}
