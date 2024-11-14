package com.jacaranda.eventia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jacaranda.eventia.dto.NotificationDTO;
import com.jacaranda.eventia.model.Event;
import com.jacaranda.eventia.model.Notification;
import com.jacaranda.eventia.model.User;
import com.jacaranda.eventia.repository.EventRepository;
import com.jacaranda.eventia.repository.NotificationRepository;
import com.jacaranda.eventia.repository.UserRepository;

@Service
public class NotificationService {
	
	@Autowired
	NotificationRepository notificationRepository;
	
	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	UserRepository userRepository;
	
	public User getUser(Integer id) {
		return userRepository.findById(id).orElse(null);
	}
	
	public Event getEvent(Integer id) {
		return eventRepository.findById(id).orElse(null);
	}
	
	public NotificationDTO add(NotificationDTO notificationDTO) {
		if(notificationDTO.getUser() != null &&
		   notificationDTO.getEvent() != null &&	
		   notificationDTO.getMessage() != null	) {
			User user = getUser(notificationDTO.getUser());
			Event event = getEvent(notificationDTO.getEvent());
			Notification notification = new Notification(user, event,notificationDTO.getMessage());
			notificationRepository.save(notification);
		}
		return notificationDTO;
		
	}

}
