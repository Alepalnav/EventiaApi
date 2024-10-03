package com.jacaranda.eventia.model;


import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="participacion")
@IdClass(value = ParticipationId.class)
public class Participation {
	
	@Id
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private User user;
	
	@Id
	@ManyToOne
	@JoinColumn(name="id_evento")
	private Event event;

	public Participation() {
		super();
	}

	public Participation(User user, Event event) {
		super();
		this.user = user;
		this.event = event;
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

	@Override
	public int hashCode() {
		return Objects.hash(event, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Participation other = (Participation) obj;
		return Objects.equals(event, other.event) && Objects.equals(user, other.user);
	}
	
	

}
