package com.test.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanCreateDTO {

	@NotNull
	@JsonProperty("name")
	private String name;

	@NotNull
	@JsonProperty("description")
	private String description;

	@NotNull
	@JsonProperty("userId")
	private int userId;
}
