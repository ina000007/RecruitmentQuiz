package com.nishant.QuizDemo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nishant.QuizDemo.model.QuestionCategory;

public interface QuestionCategoryRepository extends JpaRepository<QuestionCategory, Long>{
	
	Optional<QuestionCategory> findById(Long questionCategoryId);
	Optional<QuestionCategory> findByQuestionCategory(String questionCategory);
	Boolean existsByQuestionCategory(String questionCategory);

}
