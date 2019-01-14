package com.nishant.QuizDemo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nishant.QuizDemo.model.QuestionAnswere;

import java.util.List;
import java.util.Optional;

public interface QuestionAnswereRepository extends JpaRepository<QuestionAnswere, Long> {
	
    Optional<QuestionAnswere> findById(Long questionAnswereId);

    Optional<QuestionAnswere> findByquestionDesc(String questionDesc);
    
    List<QuestionAnswere> findByType(String type);
    List<QuestionAnswere> findAllByType(String type);
    
    Boolean existsByQuestionDesc(String questionDesc);

//    Page<QuestionAnswere> findByCreatedBy(Long userId, Pageable pageable);
//
//    long countByCreatedBy(Long userId);
//
//    List<Poll> findByIdIn(List<Long> pollIds);
//
//    List<Poll> findByIdIn(List<Long> pollIds, Sort sort);
}
