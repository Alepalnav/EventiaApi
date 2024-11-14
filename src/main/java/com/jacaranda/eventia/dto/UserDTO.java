package com.jacaranda.eventia.dto;

import java.util.ArrayList;
import java.util.List;


import com.jacaranda.eventia.model.User;



public class UserDTO {
	
	private Integer id;
	
	private String name;
	
	private String email;
	
	private String password;
	
	private String role;
	
	
	List<EventDTO> events;
	
	List<NotificationDTO> notifications;

	public UserDTO() {
		super();
	}
	

	public UserDTO(Integer id, String name, String email, String password, String role) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	
	


	public UserDTO(Integer id, String name, String email, String role) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.role = role;
	}
	
	


	public UserDTO(Integer id, String name, String email, String role, List<EventDTO> events,
			List<NotificationDTO> notifications) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.role = role;
		this.events = events;
		this.notifications = notifications;
	}


	public UserDTO(Integer id, String name, String email, String password, String role, List<EventDTO> events,
			List<NotificationDTO> notifications) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.events = events;
		this.notifications = notifications;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public List<EventDTO> getEvents() {
		return events;
	}


	public void setEvents(List<EventDTO> events) {
		this.events = events;
	}


	public List<NotificationDTO> getNotifications() {
		return notifications;
	}


	public void setNotifications(List<NotificationDTO> notifications) {
		this.notifications = notifications;
	}
	
	public static List<UserDTO> convertUserToDTO(List<User> list) {
		List<UserDTO> res = new ArrayList<UserDTO>();

		for (User u : list) {

			List<EventDTO> events = new ArrayList<EventDTO>();
			events = EventDTO.convertEventToDTO(u.getEvents());
			
			List<NotificationDTO> notifications = new ArrayList<NotificationDTO>();
			notifications = NotificationDTO.convertOrderToDTO(u.getNotifications());
			
			UserDTO udto = new UserDTO(u.getId(), u.getName(),u.getEmail(),u.getPassword(),u.getRole(),events,notifications);
			res.add(udto);

		}

		return res;
	}

}
