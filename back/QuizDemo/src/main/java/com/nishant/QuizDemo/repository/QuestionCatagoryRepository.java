package com.nishant.QuizDemo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nishant.QuizDemo.model.QuestionCatagory;

public interface QuestionCatagoryRepository extends JpaRepository<QuestionCatagory, Long>{
	
	Optional<QuestionCatagory> findById(Long questionCatagoryId);
	Optional<QuestionCatagory> findByQuestionCatagory(String questionCatagory);
	Boolean existsByQuestionCatagory(String questionCatagory);

}
