package com.jacaranda.eventia.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="notificacion")
public class Notification {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_notificacion")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="id_evento")
	private Event event;
	
	@Column(name="mensaje")
	private String message;

	public Notification() {
		super();
	}

	public Notification(Integer id, User user, Event event, String message) {
		super();
		this.id = id;
		this.user = user;
		this.event = event;
		this.message = message;
	}
	
	

	public Notification(User user, Event event, String message) {
		super();
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int hashCode() {
		return Objects.hash(event, id, message, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Notification other = (Notification) obj;
		return Objects.equals(event, other.event) && Objects.equals(id, other.id)
				&& Objects.equals(message, other.message) && Objects.equals(user, other.user);
	}
	

}
