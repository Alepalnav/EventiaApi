package com.jacaranda.eventia.model;

import java.util.Objects;

public class ParticipationId {
	
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
