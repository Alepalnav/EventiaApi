package com.jacaranda.eventia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.jacaranda.eventia.service.EventService;

@RestController
public class EventController {
	
	@Autowired
	EventService eventService;

}
