package com.jacaranda.eventia.dto;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import com.jacaranda.eventia.model.Event;
import com.jacaranda.eventia.model.Notification;
import com.jacaranda.eventia.model.Participation;
import com.jacaranda.eventia.model.Rating;

import jakarta.persistence.Column;


public class EventDTO {
	
	
	private Integer id;
	
	
	private Integer user;
	
	private String title;
	
	private String descrip;
	
	@JsonFormat(shape= Shape.STRING, pattern ="yyyy-MM-dd")
	private Date date_start;
	
	@JsonFormat(shape= Shape.STRING, pattern ="yyyy-MM-dd")
	private Date date_finish;
	
	@JsonFormat(shape= Shape.STRING, pattern ="HH:mm")
	private LocalTime hour_start;
	
	@JsonFormat(shape= Shape.STRING, pattern ="HH:mm")
	private LocalTime hour_finish;
	
	private String place;
	
	private String category;
	
	private Integer max_participant;
	
	private Integer participants;
	
	private Integer available;
	
	List<ParticipationDTO> participations;
	
	List<RatingDTO> ratings;
	
	List<NotificationDTO> notifiactions;

	public EventDTO() {
		super();
	}
	

	public EventDTO(Integer user, String title, String descrip, Date date_start, Date date_finish, LocalTime hour_start,
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



	public EventDTO(Integer id, Integer user, String title, String descrip, Date date_start, Date date_finish,
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

	public EventDTO(Integer id, Integer user, String title, String descrip, Date date_start, Date date_finish,
			LocalTime hour_start, LocalTime hour_finish, String place, String category, Integer max_participant,
			Integer participants, Integer available, List<ParticipationDTO> participations, List<RatingDTO> ratings,
			List<NotificationDTO> notifiactions) {
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

	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
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

	public List<ParticipationDTO> getParticipations() {
		return participations;
	}

	public void setParticipations(List<ParticipationDTO> participations) {
		this.participations = participations;
	}

	public List<RatingDTO> getRatings() {
		return ratings;
	}

	public void setRatings(List<RatingDTO> ratings) {
		this.ratings = ratings;
	}

	public List<NotificationDTO> getNotifiactions() {
		return notifiactions;
	}

	public void setNotifiactions(List<NotificationDTO> notifiactions) {
		this.notifiactions = notifiactions;
	}

	public static List<EventDTO> convertEventToDTO(List<Event> list) {
		List<EventDTO> res = new ArrayList<EventDTO>();

		for (Event u : list) {

			List<ParticipationDTO> partcipations = new ArrayList<ParticipationDTO>();
			for (Participation od : u.getParticipations()) {
				
				ParticipationDTO oddto = new ParticipationDTO(od.getUser().getId(), od.getEvent().getId());
				partcipations.add(oddto);
			}
			
			List<RatingDTO> ratings = new ArrayList<RatingDTO>();
			for (Rating od : u.getRatings()) {
				
				RatingDTO oddto = new RatingDTO(od.getUser().getId(),od.getEvent().getId(),od.getStars(),od.getComment());
				ratings.add(oddto);
			}
			List<NotificationDTO> notifications = new ArrayList<NotificationDTO>();
			for (Notification od : u.getNotifiactions()) {
				
				
				NotificationDTO oddto = new NotificationDTO(od.getId(),od.getUser().getId(), od.getEvent().getId(),od.getMessage());
				notifications.add(oddto);
			}
			EventDTO udto = new EventDTO(u.getId(),u.getUser().getId(),u.getTitle(),u.getDescrip(),u.getDate_start(),u.getDate_finish(),u.getHour_start(),u.getHour_finish(),u.getPlace(),u.getCategory(),u.getMax_participant(),u.getParticipants(),u.getAvailable(),partcipations,ratings,notifications);
			res.add(udto);

		}

		return res;
	}

}
