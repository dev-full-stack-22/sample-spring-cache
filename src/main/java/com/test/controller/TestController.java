package com.test.controller;

import java.util.List;
import java.util.stream.Collectors;

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
import com.test.jpa.User;
import com.test.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/test")
public class TestController {

	@Autowired
	UserRepository userRepository;

	@GetMapping("/users/{userId}")
	public ResponseEntity<UserDTO> getUser(@PathVariable("userId") int userId) throws ResourceNotFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
		return new ResponseEntity<UserDTO>(user.toDTO(), HttpStatus.OK);
	}

	@GetMapping("/users")
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		List<User> userList = userRepository.findAll();
		List<UserDTO> userDtoList = userList.stream().map(user -> user.toDTO()).collect(Collectors.toList());
		log.debug("userList: {}", userList);
		return new ResponseEntity<List<UserDTO>>(userDtoList, HttpStatus.OK);
	}

	@PostMapping("/users")
	public String createUser(@Valid @RequestBody UserDTO userDto) {
		log.debug("userDto: {}", userDto);
		userRepository.save(new User(userDto));
		return "success";
	}

	@PutMapping("/users/{userId}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable("userId") int userId, @RequestBody UserDTO userDto)
			throws ResourceNotFoundException {
		log.debug("Update user : {}", userDto);
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setEmail(userDto.getEmail());
		userRepository.save(user);
		return new ResponseEntity<UserDTO>(user.toDTO(), HttpStatus.OK);
	}

}
