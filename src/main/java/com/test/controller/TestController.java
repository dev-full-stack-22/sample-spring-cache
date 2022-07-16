package com.test.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.dto.UserDTO;
import com.test.exception.ResourceNotFoundException;
import com.test.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/test")
public class TestController {

	@Autowired
	UserService userService;

	@GetMapping("/users/{userId}")
	public ResponseEntity<UserDTO> getUser(@PathVariable("userId") int userId) throws ResourceNotFoundException {
		log.debug("Controller > Inside getUser()");
		return new ResponseEntity<UserDTO>(userService.getUser(userId), HttpStatus.OK);
	}

	@GetMapping("/users")
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		log.debug("Controller > Inside getAllUsers()");
		return new ResponseEntity<List<UserDTO>>(userService.getAllUsers(), HttpStatus.OK);
	}

	@PostMapping("/users")
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDto) {
		log.debug("Controller > Inside createUser()");
		return new ResponseEntity<UserDTO>(userService.craeteUser(userDto), HttpStatus.OK);
	}

	@PutMapping("/users/{userId}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable("userId") int userId, @RequestBody UserDTO userDto)
			throws ResourceNotFoundException {
		log.debug("Controller > Inside updateUser()");
		return new ResponseEntity<UserDTO>(userService.updateUser(userId, userDto), HttpStatus.OK);
	}

}
