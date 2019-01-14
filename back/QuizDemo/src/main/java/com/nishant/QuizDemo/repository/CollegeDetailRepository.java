package com.nishant.QuizDemo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nishant.QuizDemo.model.CollegeDetail;
import com.nishant.QuizDemo.model.CollegeId;
import com.nishant.QuizDemo.model.User;

public interface CollegeDetailRepository extends JpaRepository<CollegeDetail, CollegeId> {
	
	Optional<CollegeDetail> findByCollegeId(CollegeId collegeId);
	Optional<CollegeDetail> findByClgRgstCd(Long clgRgstCd);

    Boolean existsByCollegeId(CollegeId collegeId);

}
