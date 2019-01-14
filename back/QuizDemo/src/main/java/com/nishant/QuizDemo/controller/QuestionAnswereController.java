package com.nishant.QuizDemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nishant.QuizDemo.model.QuestionAnswere;
import com.nishant.QuizDemo.model.QuestionCatagory;
import com.nishant.QuizDemo.payload.AddCatagoryRequest;
import com.nishant.QuizDemo.payload.ApiResponse;
import com.nishant.QuizDemo.payload.QuesAnsUploadRequest;
import com.nishant.QuizDemo.payload.QuesAnsUploadResponse;
import com.nishant.QuizDemo.repository.QuestionAnswereRepository;
import com.nishant.QuizDemo.repository.QuestionCatagoryRepository;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/questionanswere")
public class QuestionAnswereController {

	@Autowired
	private QuestionAnswereRepository questionAnswereRepository;
	
	@Autowired
	QuestionCatagoryRepository questionCatagoryRepository;

	private static final Logger logger = LoggerFactory.getLogger(QuestionAnswereController.class);

	@PostMapping("/addquestion")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addQuestion(@Valid @RequestBody List<QuesAnsUploadRequest> quesAnsUploadRequest) {
		System.out.println("Adding Question");
		List<QuesAnsUploadResponse> quesAnsResponse = new ArrayList<>();

		for (QuesAnsUploadRequest quesAns : quesAnsUploadRequest) {
			
			if (questionAnswereRepository.existsByQuestionDesc(quesAns.getQuestionDesc())) {
				quesAnsResponse.add(new QuesAnsUploadResponse(false, "Question already exist", "Not generated"));
			} else {
				// Adding new question
				QuestionAnswere questionAnswere = new QuestionAnswere(quesAns.getQuestionDesc(), quesAns.getOption1(),
						quesAns.getOption2(), quesAns.getOption3(), quesAns.getOption4(), quesAns.getAnswere(),
						quesAns.getMark()+"", quesAns.getType(), new Date().getTime()+"");
				questionAnswereRepository.save(questionAnswere);
				System.out.println("question-> "+questionAnswere.toString());
				quesAnsResponse.add(new QuesAnsUploadResponse(true, quesAns.getQuestionDesc(), questionAnswere.getId()+""));
			}

		}

//        Poll poll = pollService.createPoll(pollRequest);
//
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest().path("/{pollId}")
//                .buildAndExpand(poll.getId()).toUri();
//
//        return ResponseEntity.created(location)
//                .body(new ApiResponse(true, "Poll Created Successfully"));

		return ResponseEntity.ok(quesAnsResponse);
	}
	
	@PostMapping("/addcatagory")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addCatagory(@Valid @RequestBody AddCatagoryRequest addCatagoryRequest) {
		System.out.println("Adding Catagory");
			if (questionCatagoryRepository.existsByQuestionCatagory(addCatagoryRequest.getQuestionCatagory().toLowerCase())) {
	            return new ResponseEntity(new ApiResponse(false, "Catagory already exists"),HttpStatus.BAD_REQUEST);
			} else {
				// Adding new catagory
				QuestionCatagory questionCatagory = new QuestionCatagory(addCatagoryRequest.getQuestionCatagory().toLowerCase());
				questionCatagoryRepository.save(questionCatagory);
				System.out.println("question-> "+questionCatagory.toString());
		        return ResponseEntity.ok(new ApiResponse(true, "Catagory added successfully"));
			}		
	}
	
	@PostMapping("/quescnt")
	@PreAuthorize("hasRole('ADMIN')")
	public Map quesCnt() {
		String quesCatType;
		String quesCatName;
		Map<String,String> quesCnt=new HashMap<>();
		for(QuestionCatagory questionCatagory:questionCatagoryRepository.findAll()) {
			quesCatType = questionCatagory.getId()+"";
			quesCatName = questionCatagory.getQuestionCatagory();
			quesCnt.put(quesCatName, quesCatType);
		}
		
		return quesCnt;
	}	
}
