package com.jacaranda.eventia.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class ParticipationId implements Serializable{
	
	private Integer user;
	
	private Integer event;
	
	public ParticipationId() {
		super();
	}

	public ParticipationId(Integer user, Integer event) {
		super();
		this.user = user;
		this.event = event;
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
		ParticipationId other = (ParticipationId) obj;
		return Objects.equals(event, other.event) && Objects.equals(user, other.user);
	}
	
	
	

}
