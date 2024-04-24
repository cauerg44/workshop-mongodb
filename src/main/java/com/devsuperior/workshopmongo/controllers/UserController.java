package com.devsuperior.workshopmongo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.workshopmongo.models.dto.UserDTO;
import com.devsuperior.workshopmongo.sevices.UserService;

@RestController
@RequestMapping(value = "users")
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<UserDTO> list = service.findAll();
		return ResponseEntity.ok(list);
	}
}