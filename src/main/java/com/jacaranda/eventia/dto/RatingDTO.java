package com.jacaranda.eventia.dto;

import java.util.ArrayList;
import java.util.List;

import com.jacaranda.eventia.model.Notification;
import com.jacaranda.eventia.model.Rating;

public class RatingDTO {
	
	
	private Integer user;
	
	private Integer event;
	
	private Integer stars;
	
	private String comment;

	public RatingDTO() {
		super();
	}

	public RatingDTO(Integer user, Integer event, Integer stars, String comment) {
		super();
		this.user = user;
		this.event = event;
		this.stars = stars;
		this.comment = comment;
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
	
	public static List<RatingDTO> convertOrderToDTO(List<Rating> list) {
		List<RatingDTO> res = new ArrayList<RatingDTO>();

		for (Rating or : list) {

			RatingDTO nodto = new RatingDTO(or.getUser().getId(),or.getEvent().getId(),or.getStars(),or.getComment() );
			res.add(nodto);

		}

		return res;
	}

}
