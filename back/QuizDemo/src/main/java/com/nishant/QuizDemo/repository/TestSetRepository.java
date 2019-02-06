package com.nishant.QuizDemo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nishant.QuizDemo.model.TestSet;

public interface TestSetRepository extends JpaRepository<TestSet, Long> {
	

    Optional<TestSet> findById(Long testSetId);


}
