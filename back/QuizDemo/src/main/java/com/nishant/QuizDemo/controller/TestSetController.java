package com.nishant.QuizDemo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nishant.QuizDemo.model.QuestionAnswer;
import com.nishant.QuizDemo.model.TestQuestionSet;
import com.nishant.QuizDemo.model.TestSet;
import com.nishant.QuizDemo.payload.GenericApiResponse;
import com.nishant.QuizDemo.payload.QuesAnsUploadRequest;
import com.nishant.QuizDemo.payload.QuesAnsUploadResponse;
import com.nishant.QuizDemo.payload.TestSetRequest;
import com.nishant.QuizDemo.repository.TestQuestionSetRepository;
import com.nishant.QuizDemo.repository.TestSetRepository;

@RestController
@RequestMapping("/api/testset")
public class TestSetController {
	

	private static final Logger logger = LoggerFactory.getLogger(TestSetController.class);
	@Autowired
	TestSetRepository testSetRepository;
	@Autowired
	TestQuestionSetRepository testQuestionSetRepository;
	
	TestQuestionSet testQuestionSet;
	
	@PostMapping("/createtest")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> createTest( @RequestBody TestSetRequest testSetRequest) {
		System.out.println("NISHANT Creating test set ->"+testSetRequest);
		
		
		TestSet testSet = new TestSet(testSetRequest.getClgRgsCd(),testSetRequest.getDriveDate(),testSetRequest.getStrtTime(),
				testSetRequest.getEndTime(),testSetRequest.getAllocatedTime(),testSetRequest.getTotalQues(),testSetRequest.getMaxMarks());
		
				testSet = testSetRepository.save(testSet);
				System.out.println("herer--> "+testSet);
		
		Map<String,String> reqQuesCatNCnt = testSetRequest.getReqQuesCatNCnt();
	       for (Map.Entry<String,String> entry : reqQuesCatNCnt.entrySet())  {
	    	   System.out.println("again "+testSet.getId()+"  "+entry.getKey()+"  "+entry.getValue());
	    	   testQuestionSet = new TestQuestionSet( testSet.getId(), entry.getKey(), entry.getValue());
	    	   System.out.println("nishant "+testQuestionSet);
	    	   testQuestionSetRepository.save(testQuestionSet);
	       }
	       return ResponseEntity.ok(new GenericApiResponse(true, "Test Has created", testSet));
			
		}

	
}
