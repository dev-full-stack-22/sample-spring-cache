package com.test.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.test.dto.PlanCreateDTO;
import com.test.dto.PlanDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Plan {

	public Plan(PlanCreateDTO planDto) {
		this.name = planDto.getName();
		this.description = planDto.getDescription();
	}

	public PlanDTO toDTO() {
		return PlanDTO.builder()//
				.id(id)//
				.name(name)//
				.description(description)//
				.build();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	private String name;

	@NotNull
	private String description;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

}
