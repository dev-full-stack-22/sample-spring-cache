package com.test.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanDTO {

	public static final String HASH_KEY = "PLAN_DTO";

	public static final String LIST_HASH_KEY = "LIST_PLAN_DTO";

	@JsonProperty("id")
	private int id;

	@NotNull
	@JsonProperty("name")
	private String name;

	@NotNull
	@JsonProperty("description")
	private String description;

}
