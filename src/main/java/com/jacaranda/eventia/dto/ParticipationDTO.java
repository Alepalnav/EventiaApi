package com.jacaranda.eventia.dto;

import java.util.ArrayList;
import java.util.List;

import com.jacaranda.eventia.model.Participation;

public class ParticipationDTO {
	
	private Integer user;
	
	private Integer event;

	public ParticipationDTO() {
		super();
	}

	public ParticipationDTO(Integer user, Integer event) {
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
	
	public static List<ParticipationDTO> convertOrderToDTO(List<Participation> list) {
		List<ParticipationDTO> res = new ArrayList<ParticipationDTO>();

		for (Participation or : list) {

			ParticipationDTO nodto = new ParticipationDTO(or.getUser().getId(),or.getEvent().getId());
			res.add(nodto);

		}

		return res;
	}

}
