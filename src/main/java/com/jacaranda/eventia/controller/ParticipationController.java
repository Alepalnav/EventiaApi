package com.jacaranda.eventia.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jacaranda.eventia.dto.ParticipationDTO;
import com.jacaranda.eventia.exception.ElementNotFoundException;
import com.jacaranda.eventia.exception.ElementNotFoundIntException;
import com.jacaranda.eventia.service.ParticipationService;

@RestController
public class ParticipationController {
	
	@Autowired
	ParticipationService participationService;
	
	
	@PostMapping("/participate")
    public ResponseEntity<?> create(@Validated @RequestBody ParticipationDTO ParticipationDTO, BindingResult bindingResult) {
		
		ParticipationDTO newParticipationDTO = participationService.add(ParticipationDTO);
    	
    	if(newParticipationDTO==null) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al añadir elemento");
    	}else {
    		return ResponseEntity.status(201).body(newParticipationDTO);
    	}
    
    }
	
	@DeleteMapping("/participation")
	public ResponseEntity<?> delete(@RequestParam("idUser") Integer idUser,@RequestParam("idEvent") Integer idEvent){
		ParticipationDTO newParticipationDTO = participationService.delete(idUser,idEvent);
		if(newParticipationDTO==null){
			throw new ElementNotFoundIntException(idEvent);
    	}else {
    		return ResponseEntity.status(201).body(newParticipationDTO);
    	}
	}
	
	@GetMapping("/participate")
	public ResponseEntity<Boolean> isUserParticipating(@RequestParam("idUser") Integer idUser,@RequestParam("idEvent") Integer idEvent) {
	    boolean isParticipating = participationService.isUserParticipating(idEvent, idUser);
	    return ResponseEntity.ok(isParticipating);
	}

}
