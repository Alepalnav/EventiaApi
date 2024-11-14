package com.jacaranda.eventia.model;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
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
	private LocalTime hour_start;
	
	@Column(name="hora_fin")
	private LocalTime hour_finish;
	
	@Column(name="lugar")
	private String place;
	
	@Column(name="categoria")
	private String category;
	
	@Column(name="max_participantes")
	private Integer max_participant;
	
	@Column(name="participantes_actuales")
	private Integer participants;
	
	@Column(name="disponible")
	private Integer available;
	
	@OneToMany(mappedBy="event", cascade = CascadeType.ALL)
	List<Participation> participations;
	
	@OneToMany(mappedBy="event", cascade = CascadeType.ALL)
	List<Rating> ratings;
	
	@OneToMany(mappedBy="event", cascade = CascadeType.ALL)
	List<Notification> notifiactions;
	
	public Event() {
		super();
	}
	
	public Event(User user, String title, String descrip, Date date_start, Date date_finish, LocalTime hour_start,
			LocalTime hour_finish, String place, String category, Integer max_participant, Integer participants,
			Integer available) {
		super();
		this.user = user;
		this.title = title;
		this.descrip = descrip;
		this.date_start = date_start;
		this.date_finish = date_finish;
		this.hour_start = hour_start;
		this.hour_finish = hour_finish;
		this.place = place;
		this.category = category;
		this.max_participant = max_participant;
		this.participants = participants;
		this.available = available;
	}


	public Event(Integer id, User user, String title, String descrip, Date date_start, Date date_finish,
			LocalTime hour_start, LocalTime hour_finish, String place, String category, Integer max_participant,
			Integer participants, Integer available) {		
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
		this.max_participant = max_participant;
		this.participants = participants;
		this.available = available;
	}



	public Event(Integer id, User user, String title, String descrip, Date date_start, Date date_finish,
			LocalTime hour_start, LocalTime hour_finish, String place, String category, Integer max_participant,
			Integer participants, Integer available, List<Participation> participations, List<Rating> ratings,
			List<Notification> notifiactions) {
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
		this.max_participant = max_participant;
		this.participants = participants;
		this.available = available;
		this.participations = participations;
		this.ratings = ratings;
		this.notifiactions = notifiactions;
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

	public LocalTime getHour_start() {
		return hour_start;
	}

	public void setHour_start(LocalTime hour_start) {
		this.hour_start = hour_start;
	}

	public LocalTime getHour_finish() {
		return hour_finish;
	}

	public void setHour_finish(LocalTime hour_finish) {
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

	public Integer getMax_participant() {
		return max_participant;
	}

	public void setMax_participant(Integer max_participant) {
		this.max_participant = max_participant;
	}

	public Integer getParticipants() {
		return participants;
	}

	public void setParticipants(Integer participants) {
		this.participants = participants;
	}

	public Integer getAvailable() {
		return available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
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
