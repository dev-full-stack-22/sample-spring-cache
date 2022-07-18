package com.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.jpa.Plan;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer> {

}
