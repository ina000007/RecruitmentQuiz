package com.nishant.QuizDemo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nishant.QuizDemo.model.UserQuesAnsId;
import com.nishant.QuizDemo.model.UserTestTimeLeft;

public interface UserTestTimeLeftRepository extends JpaRepository<UserTestTimeLeft, Long>{
	
	

	@Query("select u from UserTestTimeLeft u where u.emailId = :emailId and u.testId = :testId")
	UserTestTimeLeft findByUserTestTimeLeftByEmailIdTestId(@Param("emailId") String emailId,@Param("testId") String testId);
	
//	select count(ques_id) as obtainedMrk,email_id from user_ques_ans_detail where is_correct=1 and test_id=31 group by email_id order by obtainedMrk desc;
	@Query(value="select  u.userQuesAnsId.emailId, count(u.userQuesAnsId.quesId) from UserQuesAnsDetail u where u.isCorrect = 1 and u.userQuesAnsId.testId = :testId group by u.userQuesAnsId.emailId")
	List<Object[]> userResultByTestId(@Param("testId") String testId);

}


