package com.jacaranda.eventia.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="valoracion")
@IdClass(value = ParticipationId.class)
public class Rating {
	
	@Id
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private User user;
	
	@Id
	@ManyToOne
	@JoinColumn(name="id_evento")
	private Event event;
	
	@Column(name="estrellas")
	private Integer stars;
	
	@Column(name="comentario")
	private String comment;	

	public Rating() {
		super();
	}

	public Rating(User user, Event event, Integer stars, String comment) {
		super();
		this.user = user;
		this.event = event;
		this.stars = stars;
		this.comment = comment;
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

	public Integer getStars() {
		return stars;
	}

	public void setStars(Integer stars) {
		this.stars = stars;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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
		Rating other = (Rating) obj;
		return Objects.equals(event, other.event) && Objects.equals(user, other.user);
	}

}
