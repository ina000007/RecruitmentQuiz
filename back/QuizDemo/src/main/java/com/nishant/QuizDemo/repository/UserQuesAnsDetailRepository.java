package com.nishant.QuizDemo.repository;

import java.util.List;
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

}
