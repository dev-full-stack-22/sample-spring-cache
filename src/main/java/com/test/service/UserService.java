package com.test.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dto.UserDTO;
import com.test.exception.ResourceNotFoundException;
import com.test.jpa.User;
import com.test.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public UserDTO craeteUser(UserDTO userDto) {
		log.debug("UserService >> userDto: {}", userDto);
		User user = new User(userDto);
		userRepository.save(user);
		return user.toDTO();
	}

	public List<UserDTO> getAllUsers() {
		log.debug("UserService >> getAllUsers()");
		List<User> userList = userRepository.findAll();
		List<UserDTO> userDtoList = userList.stream().map(user -> user.toDTO()).collect(Collectors.toList());
		return userDtoList;
	}

	public UserDTO updateUser(int userId, UserDTO userDto) throws ResourceNotFoundException {
		log.debug("UserService >> Update user : {}", userDto);
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setEmail(userDto.getEmail());
		userRepository.save(user);
		return user.toDTO();
	}

	public UserDTO getUser(int userId) throws ResourceNotFoundException {
		log.debug("UserService >> get user with id: {}", userId);
		return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException()).toDTO();
	}

}
