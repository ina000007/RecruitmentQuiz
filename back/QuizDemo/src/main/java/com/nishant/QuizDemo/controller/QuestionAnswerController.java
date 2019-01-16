package com.nishant.QuizDemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nishant.QuizDemo.model.QuestionAnswer;
import com.nishant.QuizDemo.model.QuestionCategory;
import com.nishant.QuizDemo.payload.AddCategoryRequest;
import com.nishant.QuizDemo.payload.ApiResponse;
import com.nishant.QuizDemo.payload.QuesAnsUploadRequest;
import com.nishant.QuizDemo.payload.QuesAnsUploadResponse;
import com.nishant.QuizDemo.repository.QuestionAnswerRepository;
import com.nishant.QuizDemo.repository.QuestionCategoryRepository;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/questionanswer")
public class QuestionAnswerController {

	@Autowired
	private QuestionAnswerRepository questionAnswerRepository;
	
	@Autowired
	QuestionCategoryRepository questionCategoryRepository;

	private static final Logger logger = LoggerFactory.getLogger(QuestionAnswerController.class);

	@PostMapping("/addquestion")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addQuestion(@Valid @RequestBody List<QuesAnsUploadRequest> quesAnsUploadRequest) {
		System.out.println("Adding Question");
		List<QuesAnsUploadResponse> quesAnsResponse = new ArrayList<>();

		for (QuesAnsUploadRequest quesAns : quesAnsUploadRequest) {
			
			if (questionAnswerRepository.existsByQuestionDesc(quesAns.getQuestionDesc())) {
				quesAnsResponse.add(new QuesAnsUploadResponse(false, "Question already exist", "Not generated"));
			} else {
				// Adding new question
				QuestionAnswer questionAnswer = new QuestionAnswer(quesAns.getQuestionDesc(), quesAns.getOption1(),
						quesAns.getOption2(), quesAns.getOption3(), quesAns.getOption4(), quesAns.getAnswer(),
						quesAns.getMark()+"", quesAns.getType(), new Date().getTime()+"");
				questionAnswerRepository.save(questionAnswer);
				System.out.println("question-> "+questionAnswer.toString());
				quesAnsResponse.add(new QuesAnsUploadResponse(true, quesAns.getQuestionDesc(), questionAnswer.getId()+""));
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
	
	@PostMapping("/addcategory")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addCategory(@Valid @RequestBody AddCategoryRequest addCategoryRequest) {
		System.out.println("Adding Category");
			if (questionCategoryRepository.existsByQuestionCategory(addCategoryRequest.getQuestionCategory().toLowerCase())) {
	            return new ResponseEntity(new ApiResponse(false, "Category already exists"),HttpStatus.BAD_REQUEST);
			} else {
				// Adding new category
				QuestionCategory questionCategory = new QuestionCategory(addCategoryRequest.getQuestionCategory().toLowerCase());
				questionCategoryRepository.save(questionCategory);
				System.out.println("question-> "+questionCategory.toString());
		        return ResponseEntity.ok(new ApiResponse(true, "Category added successfully"));
			}		
	}
	
	@PostMapping("/quescnt")
	@PreAuthorize("hasRole('ADMIN')")
	public Map quesCnt() {
		String quesCatType;
		String quesCatName;
		Map<String,String> quesCnt=new HashMap<>();
		for(QuestionCategory questionCategory:questionCategoryRepository.findAll()) {
			quesCatType = questionCategory.getId()+"";
			quesCatName = questionCategory.getQuestionCategory();
			quesCnt.put(quesCatName, questionAnswerRepository.findAllByType(quesCatType).size()+"");
		}
		
		return quesCnt;
	}	
}
