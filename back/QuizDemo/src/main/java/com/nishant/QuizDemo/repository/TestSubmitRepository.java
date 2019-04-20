package com.nishant.QuizDemo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nishant.QuizDemo.model.TestSubmit;


public interface TestSubmitRepository  extends JpaRepository<TestSubmit, Long> {
	

    Optional<TestSubmit> findById(Long testSetId);
    
	@Query("select u from TestSubmit u where u.emailId = :emailId and u.testId = :testId")
	TestSubmit findTestSubmitByIdEmailIdTestId(@Param("emailId") String emailId,@Param("testId") String testId);


}
