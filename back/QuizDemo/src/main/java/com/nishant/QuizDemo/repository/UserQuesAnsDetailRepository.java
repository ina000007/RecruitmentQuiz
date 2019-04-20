package com.nishant.QuizDemo.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nishant.QuizDemo.model.UserQuesAnsDetail;
import com.nishant.QuizDemo.model.UserQuesAnsId;

public interface UserQuesAnsDetailRepository extends JpaRepository<UserQuesAnsDetail, UserQuesAnsId>{
	
	Optional<UserQuesAnsDetail> findByUserQuesAnsId(UserQuesAnsId userQuesAnsId);
	

	@Query("select u from UserQuesAnsDetail u where u.userQuesAnsId.emailId = :emailId and u.userQuesAnsId.testId = :testId")
	List<UserQuesAnsDetail> findByUserQuesAnsIdEmailIdTestId(@Param("emailId") String emailId,@Param("testId") String testId);
	
//	select count(ques_id) as obtainedMrk,email_id from user_ques_ans_detail where is_correct=1 and test_id=31 group by email_id order by obtainedMrk desc;
//	@Query(value="select  u.userQuesAnsId.emailId, count(u.userQuesAnsId.quesId) from UserQuesAnsDetail u where u.isCorrect = 1 and u.userQuesAnsId.testId = :testId group by u.userQuesAnsId.emailId")
//	List<Object[]> userResultByTestId(@Param("testId") String testId);
	
//	 select email_id, (select count(ques_id) from user_ques_ans_detail where test_id=135 and is_correct = 1 group by email_id ) as marks from user_ques_ans_detail where is_correct=0 and 
//	test_id=135 group by email_id;
	
//	SELECT email_id, sum(is_correct) 'count' from user_ques_ans_detail group by email_id
//	@Query(value="select  u.userQuesAnsId.emailId, (select count(u.userQuesAnsId.quesId) from UserQuesAnsDetail u where u.userQuesAnsId.testId=:testId and u.isCorrect = 1 group by u.userQuesAnsId.emailId ) from UserQuesAnsDetail u where u.userQuesAnsId.testId=:testId group by u.userQuesAnsId.emailId")
	
	@Query(value= " SELECT u.userQuesAnsId.emailId, sum(u.isCorrect) from  UserQuesAnsDetail u  group by u.userQuesAnsId.emailId ")
	List<Object[]> userResultByTestId(@Param("testId") String testId);
}
