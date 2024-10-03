package com.jacaranda.eventia.dto;

import java.util.ArrayList;
import java.util.List;

import com.jacaranda.eventia.model.Notification;

public class NotificationDTO {	

	private Integer id;
	
	private Integer user;
	
	private Integer event;
	
	private String message;

	public NotificationDTO() {
		super();
	}

	public NotificationDTO(Integer id, Integer user, Integer event, String message) {
		super();
		this.id = id;
		this.user = user;
		this.event = event;
		this.message = message;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
		this.user = user;
	}

	public Integer getEvent() {
		return event;
	}

	public void setEvent(Integer event) {
		this.event = event;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public static List<NotificationDTO> convertOrderToDTO(List<Notification> list) {
		List<NotificationDTO> res = new ArrayList<NotificationDTO>();

		for (Notification or : list) {

			NotificationDTO nodto = new NotificationDTO(or.getId(), or.getUser().getId(),or.getEvent().getId(),or.getMessage() );
			res.add(nodto);

		}

		return res;
	}

}
