package com.test.jpa;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.test.dto.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String firstName;

	private String lastName;

	private String email;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Plan> plans;

	public User(UserDTO userDto) {
		id = userDto.getId();
		firstName = userDto.getFirstName();
		lastName = userDto.getLastName();
		email = userDto.getEmail();
	}

	public UserDTO toDTO() {
		return UserDTO.builder()//
				.id(id)//
				.firstName(firstName)//
				.lastName(lastName)//
				.email(email)//
				.build();
	}

}
