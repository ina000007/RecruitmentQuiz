package com.nishant.QuizDemo.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nishant.QuizDemo.model.CollegeDetail;
import com.nishant.QuizDemo.model.QuestionCategory;
import com.nishant.QuizDemo.payload.AddCategoryRequest;
import com.nishant.QuizDemo.payload.AddCollegeRequest;
import com.nishant.QuizDemo.payload.ApiResponse;
import com.nishant.QuizDemo.payload.GenericApiResponse;
import com.nishant.QuizDemo.repository.CollegeDetailRepository;

@RestController
@RequestMapping("/api/college")
public class CollegeController {
	
	@Autowired
	CollegeDetailRepository collegeDetailRepository;
	private static final Logger logger = LoggerFactory.getLogger(CollegeController.class);

	@PostMapping("/addcollege")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addCollege(@Valid @RequestBody AddCollegeRequest addCollegeRequest) {
		System.out.println("Adding college.....>>"+addCollegeRequest);
			if (collegeDetailRepository.existsByCollegeId(addCollegeRequest.getCollegeId())) {
	            return new ResponseEntity(new ApiResponse(false, "College already exists"),HttpStatus.BAD_REQUEST);
			} else {
				// Adding new category
				Long clgRgstCd =collegeDetailRepository.count()+1;
				CollegeDetail CollegeDetail = new CollegeDetail(addCollegeRequest.getCollegeId(),
						addCollegeRequest.getClgState(),addCollegeRequest.getClgUniversity(),clgRgstCd);
				collegeDetailRepository.save(CollegeDetail);
				System.out.println("college detail-> "+CollegeDetail.toString());
		        return ResponseEntity.ok(new ApiResponse(true, "College added successfully"));
			}		
	}
	
	@GetMapping("/getcollege")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getCollege() {
		List<CollegeDetail> clgLst = collegeDetailRepository.findAll();
		if(clgLst.size()>0)
			return ResponseEntity.ok(new GenericApiResponse(true, "Contains all registered college", clgLst));
		else
			return ResponseEntity.ok(new GenericApiResponse(false, "No registered college", clgLst));
		
					
	}
	
	
	
}
