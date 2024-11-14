package com.jacaranda.eventia.service;


import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jacaranda.eventia.dto.EventDTO;
import com.jacaranda.eventia.dto.NotificationDTO;
import com.jacaranda.eventia.dto.ParticipationDTO;
import com.jacaranda.eventia.dto.RatingDTO;
import com.jacaranda.eventia.dto.UserDTO;
import com.jacaranda.eventia.exception.ExceptionValueNotRight;
import com.jacaranda.eventia.model.Event;
import com.jacaranda.eventia.model.Participation;
import com.jacaranda.eventia.model.User;
import com.jacaranda.eventia.repository.EventRepository;
import com.jacaranda.eventia.repository.UserRepository;



@Service
public class EventService {
	
	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	ParticipationService participationService;
//	public List<EventDTO> getEvents(){
//		try {
//			List<Event> list = eventRepository.findAll();
//			return EventDTO.convertOrderToDTO(list);			
//		}catch(Exception e) {
//			throw new ExceptionValueNotRight("Orders doesn´t exists");
//		}
//	}
	
	public Page<EventDTO> findAllPages(String numPage, String pageSize, String order, String ad){
		Pageable pageable = null;
		
		try {
			Integer.valueOf(numPage);
		}catch(Exception e) {
			throw new ExceptionValueNotRight("Only numbers");
		}
		
		try {
			Integer.valueOf(pageSize);
		}catch(Exception e) {
			throw new ExceptionValueNotRight("Only numbers");
		}
		
		try {
			if(order!=null&&!order.isBlank()) {
				Event.class.getDeclaredField(order);
			}
		}catch (Exception e) {
			throw new ExceptionValueNotRight(order+" doesn´t exists");
		}
		
		if(ad.equals("desc")) {
			try {
				pageable = PageRequest.of(Integer.valueOf(numPage) - 1, Integer.valueOf(pageSize),Sort.by(order).descending());
			}catch (Exception e) {
				pageable = PageRequest.of(0, Integer.valueOf(pageSize), Sort.by(order).descending());
			}
		}else {
			try {
				pageable = PageRequest.of(Integer.valueOf(numPage) - 1, Integer.valueOf(pageSize),Sort.by(order).ascending());
			}catch (Exception e) {
				pageable = PageRequest.of(0, Integer.valueOf(pageSize), Sort.by(order).ascending());
			}
		}
		
		Page<Event> temp = eventRepository.findByAvailable(1, pageable);
		Page<EventDTO> result = temp.map(event -> new EventDTO(event.getId(),event.getUser().getId(),event.getTitle(),event.getDescrip(),event.getDate_start(),event.getDate_finish(),event.getHour_start(),event.getHour_finish(),event.getPlace(),event.getCategory(),event.getMax_participant(),event.getParticipants(),event.getAvailable()));
	    return result;
	}
	
	public User getUser(Integer id) {
		return userRepository.findById(id).orElse(null);
	}
	
	public Event getEvent(Integer id) {
		return eventRepository.findById(id).orElse(null);
	}
	
	public EventDTO add(EventDTO eventDTO) {
	    Integer id = eventDTO.getId();
	    if (id != null && eventRepository.existsById(id)) {
	        return null;
	    } else {

	        // Validaciones de los campos
	        if (eventDTO.getTitle() == null || eventDTO.getTitle().isEmpty() ||
	        	eventDTO.getUser() == null || 
	        	eventDTO.getDescrip() == null || eventDTO.getDescrip().isEmpty() ||
	        	eventDTO.getDate_start() == null || eventDTO.getDate_start().toLocalDate().isBefore(LocalDate.now()) ||
	        	eventDTO.getDate_finish() == null || eventDTO.getDate_finish().toLocalDate().isBefore(eventDTO.getDate_start().toLocalDate()) || 
	        	eventDTO.getHour_start() == null ||	eventDTO.getHour_start().isAfter(eventDTO.getHour_finish()) ||
	        	eventDTO.getHour_finish() == null ||
	        	eventDTO.getPlace() == null ||
	        	eventDTO.getCategory() == null ||
	        	eventDTO.getMax_participant() == null ||
	        	eventDTO.getParticipants() == null ||
	        	eventDTO.getAvailable() == null) {
	            throw new ExceptionValueNotRight("All fields must be non-null and non-empty");
	        }

	        try {
	            Event event = new Event(getUser(eventDTO.getUser()),eventDTO.getTitle(),eventDTO.getDescrip(), eventDTO.getDate_start(), eventDTO.getDate_finish(), eventDTO.getHour_start(), eventDTO.getHour_finish(), eventDTO.getPlace(),eventDTO.getCategory(),eventDTO.getMax_participant(),eventDTO.getParticipants(),1);
	            eventRepository.save(event);
	            id = event.getId();
	            EventDTO newEventDTO = new EventDTO(id, event.getUser().getId(),event.getTitle(),event.getDescrip(), event.getDate_start(), event.getDate_finish(), event.getHour_start(), event.getHour_finish(), event.getPlace(),event.getCategory(),event.getMax_participant(),event.getParticipants(),event.getAvailable());
	            return newEventDTO;
	        } catch (Exception e) {
	            throw new ExceptionValueNotRight(e.getMessage());
	        }
	    }
	}
	
	public EventDTO getById(String id) {
		Integer id2;

		try {
			id2 = Integer.valueOf(id);
		}catch(Exception e) {
			throw new ExceptionValueNotRight("Id must be Integer");
		}
		try {
			Event event = eventRepository.findById(id2).orElse(null);
			ParticipationDTO participationDTO = new ParticipationDTO();
			RatingDTO ratingDTO = new RatingDTO();
			NotificationDTO notificationDTO = new NotificationDTO();
			EventDTO eventDTO = new EventDTO(event.getId(),event.getUser().getId(),event.getTitle(),event.getDescrip(),event.getDate_start(),event.getDate_finish(), event.getHour_start(), event.getHour_finish(),event.getPlace(),event.getCategory(),event.getMax_participant(),event.getParticipants(),event.getAvailable(),participationDTO.convertOrderToDTO(event.getParticipations()),ratingDTO.convertOrderToDTO(event.getRatings()),notificationDTO.convertOrderToDTO(event.getNotifiactions()));			
			return eventDTO;
		}catch(Exception e) {
			return null;
		}
	}
	
	public EventDTO edit(String id, EventDTO eventDTO) {
	    Integer id2;
	    try {
	        id2 = Integer.valueOf(id);
	    } catch (Exception e) {
	        throw new ExceptionValueNotRight("Id must be Integer");
	    }
	    EventDTO existingProduct = getById(id);
	    if (existingProduct != null) {
	        try {
	            User user = (eventDTO.getUser() != null) ? getUser(eventDTO.getUser()) : getUser(existingProduct.getUser());
	            String title = (eventDTO.getTitle() != null && !eventDTO.getTitle().isEmpty()) ? eventDTO.getTitle() : existingProduct.getTitle();
	            String descrip = (eventDTO.getDescrip() != null && !eventDTO.getDescrip().isEmpty()) ? eventDTO.getDescrip() : existingProduct.getDescrip();
	            Date date_start = eventDTO.getDate_start() != null ? eventDTO.getDate_start() : existingProduct.getDate_start();
	            Date date_finish = eventDTO.getDate_finish() != null ? eventDTO.getDate_finish() : existingProduct.getDate_finish();
	            LocalTime hour_start = (eventDTO.getHour_start() != null) ? eventDTO.getHour_start() : existingProduct.getHour_start();
	            LocalTime hour_finish = (eventDTO.getHour_finish() != null) ? eventDTO.getHour_finish() : existingProduct.getHour_finish();
	            String place = (eventDTO.getPlace() != null) ? eventDTO.getPlace() : existingProduct.getPlace();
	            String category = (eventDTO.getCategory() != null) ? eventDTO.getCategory() : existingProduct.getCategory();
	            Integer max_participant = (eventDTO.getMax_participant() != null) ? eventDTO.getMax_participant() : existingProduct.getMax_participant();
	            Integer participants = (eventDTO.getParticipants() != null) ? eventDTO.getParticipants() : existingProduct.getParticipants();
	            Integer available = (eventDTO.getAvailable() != null) ? eventDTO.getAvailable() : existingProduct.getAvailable();

	            Event event = new Event(id2, user, title, descrip, date_start, date_finish, hour_start, hour_finish, place,category,max_participant,participants,available);
	            eventRepository.save(event);
	            EventDTO updatedEventDTO = new EventDTO(event.getId(),event.getUser().getId(),event.getTitle(),event.getDescrip(),event.getDate_start(),event.getDate_finish(), event.getHour_start(), event.getHour_finish(),event.getPlace(),event.getCategory(),event.getMax_participant(),event.getParticipants(),event.getAvailable());
	            return updatedEventDTO;
	        } catch (Exception e) {
	            throw new ExceptionValueNotRight(e.getMessage());
	        }
	    } else {
	        return null;
	    }

	}
	
	public EventDTO participate(String id, String user) {
	    Integer id2;
	    Integer idUser;
	    try {
	        id2 = Integer.valueOf(id);
	        idUser = Integer.valueOf(user);
	    } catch (Exception e) {
	        throw new ExceptionValueNotRight("Id must be Integer");
	    }
	    EventDTO existingProduct = getById(id);
	    User userParticipate = getUser(idUser);
	    if (existingProduct != null && existingProduct.getAvailable() != 0 && userParticipate!=null) {
	        try {
	        	Integer max_participants = existingProduct.getMax_participant();
	        	Integer participants = existingProduct.getParticipants();
	        	if(participants<max_participants) {
	        		participants=participants+1;
	        	}
	        	
	            Event event = new Event(id2, getUser(existingProduct.getUser()), existingProduct.getTitle(), existingProduct.getDescrip(), existingProduct.getDate_start(), existingProduct.getDate_finish(), existingProduct.getHour_start(), existingProduct.getHour_finish(), existingProduct.getPlace(),existingProduct.getCategory(),existingProduct.getMax_participant(),participants,existingProduct.getAvailable());
	            ParticipationDTO participationDTO = new ParticipationDTO(id2,idUser);
	            participationService.add(participationDTO);
	            eventRepository.save(event);
	            EventDTO updatedEventDTO = new EventDTO(event.getId(),event.getUser().getId(),event.getTitle(),event.getDescrip(),event.getDate_start(),event.getDate_finish(), event.getHour_start(), event.getHour_finish(),event.getPlace(),event.getCategory(),event.getMax_participant(),event.getParticipants(),event.getAvailable());
	            return updatedEventDTO;
	        } catch (Exception e) {
	            throw new ExceptionValueNotRight(e.getMessage());
	        }
	    } else {
	        return null;
	    }

	}
	
	public List<EventDTO> getEventsByUser(String id) {
		Integer idUser = Integer.valueOf(id);
		
		List<Participation> participaciones = participationService.getEventsByUser(idUser);
		List<Event>events = participaciones.stream()
        				.map(Participation::getEvent)
        				.collect(Collectors.toList());
		return EventDTO.convertEventToDTO(events);
		
	}
	
	public List<EventDTO> getEventsByOrg(String id) {
		Integer idUser = Integer.valueOf(id);
		User user = getUser(idUser);

		List<Event>events = user.getEvents();
		return EventDTO.convertEventToDTO(events);
		
	}
	
	public List<UserDTO> getParticipants(String id){
		
		List<User> result = new ArrayList<>();
		
		EventDTO event = getById(id);
		List<ParticipationDTO> participations = event.getParticipations();
		
		for(ParticipationDTO u : participations) {
			result.add(getUser(u.getUser()));
		}
		
		return UserDTO.convertUserToDTO(result);
		
	}
	
	
	
	

}
