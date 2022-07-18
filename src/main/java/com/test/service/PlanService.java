package com.test.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.test.dto.PlanCreateDTO;
import com.test.dto.PlanDTO;
import com.test.dto.PlanUpdateDTO;
import com.test.exception.ResourceNotFoundException;
import com.test.jpa.Plan;
import com.test.jpa.User;
import com.test.repository.PlanRepository;
import com.test.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PlanService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PlanRepository planRepository;

	@Cacheable(value = PlanDTO.LIST_HASH_KEY)
	public List<PlanDTO> getPlansForUserId(int userId) throws ResourceNotFoundException {
		log.debug("get plans for user: {}", userId);
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException());
		List<Plan> planList = user.getPlans();
		return planList.stream().map((plan) -> plan.toDTO()).collect(Collectors.toList());
	}

	@Cacheable(value = PlanDTO.HASH_KEY, key = "#planId")
	public PlanDTO getPlan(int planId) throws ResourceNotFoundException {
		log.debug("Service >> getPlan: {}", planId);
		return planRepository.findById(planId).orElseThrow(() -> new ResourceNotFoundException()).toDTO();
	}

	@Caching(put = { @CachePut(value = PlanDTO.HASH_KEY, key = "#result.id") }, evict = {
			@CacheEvict(value = PlanDTO.LIST_HASH_KEY, allEntries = true) })
	public PlanDTO creatPlan(int userId, PlanCreateDTO planDto) throws ResourceNotFoundException {
		Plan plan = new Plan(planDto);
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException());
		plan.setUser(user);
		Plan planResult = planRepository.save(plan);
		return planResult.toDTO();
	}

	@Caching(put = { @CachePut(value = PlanDTO.HASH_KEY, key = "#planId") }, evict = {
			@CacheEvict(value = PlanDTO.LIST_HASH_KEY, allEntries = true) })
	public PlanDTO updatePlan(int userId, int planId, PlanUpdateDTO planUpateDto) throws ResourceNotFoundException {
		log.debug("Service >> updatePlan: {}", planId);
		Plan plan = planRepository.findById(planId).orElseThrow(() -> new ResourceNotFoundException());
		if (planUpateDto.getName() != null)
			plan.setName(planUpateDto.getName());
		if (planUpateDto.getDescription() != null)
			plan.setDescription(planUpateDto.getDescription());
		planRepository.save(plan);
		return plan.toDTO();
	}

	@Caching(evict = { @CacheEvict(value = PlanDTO.HASH_KEY, key = "#planId"),
			@CacheEvict(value = PlanDTO.LIST_HASH_KEY, allEntries = true) })
	public void deletePlan(int userId, int planId) {
		log.debug("Service >> delete by planId: {}", planId);
		planRepository.deleteById(planId);
	}

}
