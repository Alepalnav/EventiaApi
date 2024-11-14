package com.jacaranda.eventia.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.jacaranda.eventia.dto.EventDTO;
import com.jacaranda.eventia.dto.UserDTO;
import com.jacaranda.eventia.exception.ElementNotFoundException;
import com.jacaranda.eventia.exception.ExceptionPageNotFound;
import com.jacaranda.eventia.service.EventService;

@RestController
public class EventController {
	
	@Autowired
	EventService eventService;
	
	@GetMapping("/events")
	public ResponseEntity<?> listEvents(@RequestParam("numPage") Optional<String> numPage,@RequestParam("pageSize") Optional<String> pageSize, @RequestParam("order") Optional<String> order, 
			@RequestParam("asc") Optional<Boolean> asc){
		
		Page<EventDTO> pageProduct = eventService.findAllPages(numPage.orElse("1"),pageSize.orElse("10"),order.orElse("id"),asc.orElse(false)?"desc":"");
		
		if(pageProduct==null) {
			return ResponseEntity.notFound().build();
		}else if(pageProduct.getContent().size()==0) {
			throw new ExceptionPageNotFound("Pagina no existente");
		}else {
			return ResponseEntity.ok(pageProduct);
		}
	}
	
	@GetMapping("/event/{id}")
	public ResponseEntity<EventDTO> get(@PathVariable String id){
		
		EventDTO result = eventService.getById(id);
		if(result==null) {
			throw new ElementNotFoundException(id);
		}else {
			return ResponseEntity.ok(result);
		}
	}
	
//	@PutMapping("/participate")
//	public ResponseEntity<?> update(@RequestParam("id") String id, @RequestParam("user") String> user) {
//		EventDTO newEventDTO = eventService.participate(id,user);
//		if(newEventDTO==null){
//
//			throw new ElementNotFoundException(id);
//		}else {
//			return ResponseEntity.ok(newEventDTO);
//		}
//	}
	
	@PostMapping("/events")
    public ResponseEntity<?> create(@Validated @RequestBody EventDTO EventDTO, BindingResult bindingResult) {
		
		EventDTO newEventDTO = eventService.add(EventDTO);
    	
    	if(newEventDTO==null) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al añadir elemento");
    	}else {
    		return ResponseEntity.status(201).body(newEventDTO);
    	}
    
    }
	
	@GetMapping("/eventsByUser/{id}")
	public ResponseEntity<?> getEventsByUser(@PathVariable String id){
		
		List<EventDTO> result = eventService.getEventsByUser(id);
		if(result==null) {
			throw new ElementNotFoundException(id);
		}else {
			return ResponseEntity.ok(result);
		}
	}
	
	@GetMapping("/eventsByOrg/{id}")
	public ResponseEntity<?> getEventsByOrg(@PathVariable String id){
		
		List<EventDTO> result = eventService.getEventsByOrg(id);
		if(result==null) {
			throw new ElementNotFoundException(id);
		}else {
			return ResponseEntity.ok(result);
		}
	}
	
	@PutMapping("/events/{id}")
	public ResponseEntity<?> update(@PathVariable String id,@Validated @RequestBody EventDTO eventDTO, BindingResult bindingResult) {
		EventDTO newEventDTO = eventService.edit(id,eventDTO);
		if(newEventDTO==null){
			throw new ElementNotFoundException(id);
		}else {
			return ResponseEntity.ok(newEventDTO);
		}
	}
	
	
	@GetMapping("/participants/{id}")
	public ResponseEntity<?> getParticipants(@PathVariable String id){
		
		List<UserDTO> lista = eventService.getParticipants(id);
		if(lista==null) {
			throw new ElementNotFoundException(id);
		}else {
			return ResponseEntity.ok(lista);
		}
	}
	

}
