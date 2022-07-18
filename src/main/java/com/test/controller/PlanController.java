package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.dto.PlanCreateDTO;
import com.test.dto.PlanDTO;
import com.test.dto.PlanUpdateDTO;
import com.test.exception.ResourceNotFoundException;
import com.test.service.PlanService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/test")
public class PlanController {

	@Autowired
	PlanService planService;

	@PostMapping("/plans")
	public ResponseEntity<PlanDTO> createPlan(@RequestBody PlanCreateDTO planDto) throws ResourceNotFoundException {
		log.debug("Controller >> create a new plan");
		return new ResponseEntity<PlanDTO>(planService.creatPlan(planDto.getUserId(), planDto), HttpStatus.OK);
	}

	@GetMapping("/plans")
	public ResponseEntity<List<PlanDTO>> getPlansForUser(@RequestParam("userId") int userId)
			throws ResourceNotFoundException {
		log.debug("Controller >> get plans for user: {}", userId);
		return new ResponseEntity<List<PlanDTO>>(planService.getPlansForUserId(userId), HttpStatus.OK);
	}

	@GetMapping("/plans/{planId}")
	public ResponseEntity<PlanDTO> getPlan(@PathVariable("planId") int planId) throws ResourceNotFoundException {
		log.debug("Controller >> get plan: {}", planId);
		return new ResponseEntity<PlanDTO>(planService.getPlan(planId), HttpStatus.OK);
	}

	@PutMapping("/plans/{planId}")
	public ResponseEntity<PlanDTO> updatePlan(@PathVariable("planId") int planId,
			@RequestBody PlanUpdateDTO planUpdateDto) throws ResourceNotFoundException {
		log.debug("Controller >> update existing plan");
		return new ResponseEntity<PlanDTO>(planService.updatePlan(planUpdateDto.getUserId(), planId, planUpdateDto),
				HttpStatus.OK);
	}

	@DeleteMapping("/plans/{planId}")
	public ResponseEntity<String> deletePlan(@RequestParam("userId") int userId, @PathVariable("planId") int planId) {
		log.debug("Controller >> delete existing plan");
		planService.deletePlan(userId, planId);
		return new ResponseEntity<String>("Delete Success", HttpStatus.OK);
	}

}
