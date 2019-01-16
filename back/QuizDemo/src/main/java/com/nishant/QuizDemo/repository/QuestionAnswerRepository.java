package com.nishant.QuizDemo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nishant.QuizDemo.model.QuestionAnswer;

import java.util.List;
import java.util.Optional;

public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer, Long> {
	
    Optional<QuestionAnswer> findById(Long questionAnswerId);

    Optional<QuestionAnswer> findByquestionDesc(String questionDesc);
    
    List<QuestionAnswer> findByType(String type);
    List<QuestionAnswer> findAllByType(String type);
    
    Boolean existsByQuestionDesc(String questionDesc);

//    Page<QuestionAnswer> findByCreatedBy(Long userId, Pageable pageable);
//
//    long countByCreatedBy(Long userId);
//
//    List<Poll> findByIdIn(List<Long> pollIds);
//
//    List<Poll> findByIdIn(List<Long> pollIds, Sort sort);
}
