package com.jacaranda.eventia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jacaranda.eventia.dto.NotificationDTO;
import com.jacaranda.eventia.service.NotificationService;

@RestController
public class NotificationController {
	
	@Autowired
	NotificationService notificationService;
	
	
	@PostMapping("/notifications")
    public ResponseEntity<?> create(@Validated @RequestBody NotificationDTO notificationDTO, BindingResult bindingResult) {
		
		NotificationDTO newNotificationDTO = notificationService.add(notificationDTO);
    	
    	if(newNotificationDTO==null) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al añadir elemento");
    	}else {
    		return ResponseEntity.status(201).body(newNotificationDTO);
    	}
    
    }

}
