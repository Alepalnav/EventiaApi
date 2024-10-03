package com.jacaranda.eventia.model;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Objects;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="eventos")
public class Event {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_evento")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private User user;
	
	@Column(name="titulo")
	private String title;
	
	@Column(name="descripcion")
	private String descrip;
	
	@Column(name="fecha_inicio")
	private Date date_start;
	
	@Column(name="fecha_fin")
	private Date date_finish;
	
	@Column(name="hora_inicio")
	private Time hour_start;
	
	@Column(name="hora_fin")
	private Time hour_finish;
	
	@Column(name="lugar")
	private String place;
	
	@Column(name="categoria")
	private String category;
	
	@OneToMany(mappedBy="event", cascade = CascadeType.ALL)
	List<Participation> participations;
	
	@OneToMany(mappedBy="event", cascade = CascadeType.ALL)
	List<Rating> ratings;
	
	@OneToMany(mappedBy="event", cascade = CascadeType.ALL)
	List<Notification> notifiactions;
	
	public Event() {
		super();
	}

	public Event(Integer id, User user, String title, String descrip, Date date_start, Date date_finish,
			Time hour_start, Time hour_finish, String place, String category) {
		super();
		this.id = id;
		this.user = user;
		this.title = title;
		this.descrip = descrip;
		this.date_start = date_start;
		this.date_finish = date_finish;
		this.hour_start = hour_start;
		this.hour_finish = hour_finish;
		this.place = place;
		this.category = category;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescrip() {
		return descrip;
	}

	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

	public Date getDate_start() {
		return date_start;
	}

	public void setDate_start(Date date_start) {
		this.date_start = date_start;
	}

	public Date getDate_finish() {
		return date_finish;
	}

	public void setDate_finish(Date date_finish) {
		this.date_finish = date_finish;
	}

	public Time getHour_start() {
		return hour_start;
	}

	public void setHour_start(Time hour_start) {
		this.hour_start = hour_start;
	}

	public Time getHour_finish() {
		return hour_finish;
	}

	public void setHour_finish(Time hour_finish) {
		this.hour_finish = hour_finish;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	

	public List<Participation> getParticipations() {
		return participations;
	}

	public void setParticipations(List<Participation> participations) {
		this.participations = participations;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public List<Notification> getNotifiactions() {
		return notifiactions;
	}

	public void setNotifiactions(List<Notification> notifiactions) {
		this.notifiactions = notifiactions;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
