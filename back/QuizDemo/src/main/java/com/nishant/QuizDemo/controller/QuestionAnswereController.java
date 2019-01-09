package com.nishant.QuizDemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nishant.QuizDemo.model.QuestionAnswere;
import com.nishant.QuizDemo.payload.QuesAnsUploadRequest;
import com.nishant.QuizDemo.payload.QuesAnsUploadResponse;
import com.nishant.QuizDemo.repository.QuestionAnswereRepository;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/questionanswere")
public class QuestionAnswereController {

	@Autowired
	private QuestionAnswereRepository questionAnswereRepository;

	private static final Logger logger = LoggerFactory.getLogger(QuestionAnswereController.class);

	@PostMapping("/addquestion")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> createPoll(@Valid @RequestBody List<QuesAnsUploadRequest> quesAnsUploadRequest) {
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
}
