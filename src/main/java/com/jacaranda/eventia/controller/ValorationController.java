package com.jacaranda.eventia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jacaranda.eventia.dto.RatingDTO;
import com.jacaranda.eventia.service.ValorationService;

@RestController
public class ValorationController {
	
	@Autowired
	ValorationService valorationService;
	
	@PostMapping("/valoration")
    public ResponseEntity<?> create(@Validated @RequestBody RatingDTO RatingDTO, BindingResult bindingResult) {
		
		RatingDTO newRatingDTO = valorationService.add(RatingDTO);
    	
    	if(newRatingDTO==null) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al añadir elemento");
    	}else {
    		return ResponseEntity.status(201).body(newRatingDTO);
    	}
    
    }

}
