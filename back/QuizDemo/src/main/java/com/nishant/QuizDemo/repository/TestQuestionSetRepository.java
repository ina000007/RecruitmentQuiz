package com.nishant.QuizDemo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nishant.QuizDemo.model.TestQuestionSet;

public interface TestQuestionSetRepository extends JpaRepository<TestQuestionSet,Long> {
	
	Optional<TestQuestionSet> findById(Long id);
	List<TestQuestionSet> findByTestSetId(Long testSetId);

}
