package com.nishant.QuizDemo.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nishant.QuizDemo.model.CollegeDetail;
import com.nishant.QuizDemo.model.QuestionCatagory;
import com.nishant.QuizDemo.payload.AddCatagoryRequest;
import com.nishant.QuizDemo.payload.AddCollegeRequest;
import com.nishant.QuizDemo.payload.ApiResponse;
import com.nishant.QuizDemo.repository.CollegeDetailRepository;

@RestController
@RequestMapping("/api/testset")
public class TestSetController {
	
	@Autowired
	CollegeDetailRepository collegeDetailRepository;
	private static final Logger logger = LoggerFactory.getLogger(TestSetController.class);

	@PostMapping("/addcollege")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addCollege(@Valid @RequestBody AddCollegeRequest addCollegeRequest) {
		System.out.println("Adding Catagory");
			if (collegeDetailRepository.existsByCollegeId(addCollegeRequest.getCollegeId())) {
	            return new ResponseEntity(new ApiResponse(false, "College already exists"),HttpStatus.BAD_REQUEST);
			} else {
				// Adding new catagory
				CollegeDetail CollegeDetail = new CollegeDetail(addCollegeRequest.getCollegeId(),addCollegeRequest.getClgState(),addCollegeRequest.getClgState());
				collegeDetailRepository.save(CollegeDetail);
				System.out.println("question-> "+CollegeDetail.toString());
		        return ResponseEntity.ok(new ApiResponse(true, "Collage added successfully"));
			}		
	}
}
