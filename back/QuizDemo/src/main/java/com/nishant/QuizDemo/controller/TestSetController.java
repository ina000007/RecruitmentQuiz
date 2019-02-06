package com.nishant.QuizDemo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.nishant.QuizDemo.model.CollegeDetail;
import com.nishant.QuizDemo.model.QuestionAnswer;
import com.nishant.QuizDemo.model.TestQuestionSet;
import com.nishant.QuizDemo.model.TestSet;
import com.nishant.QuizDemo.payload.GenericApiResponse;
import com.nishant.QuizDemo.payload.QuesAnsUploadRequest;
import com.nishant.QuizDemo.payload.QuesAnsUploadResponse;
import com.nishant.QuizDemo.payload.TestSetRequest;
import com.nishant.QuizDemo.repository.CollegeDetailRepository;
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
	@Autowired
	CollegeDetailRepository collegeDetailRepository;

	TestQuestionSet testQuestionSet;

	@PostMapping("/createtest")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> createTest(@RequestBody TestSetRequest testSetRequest) {
		System.out.println("NISHANT Creating test set ->" + testSetRequest);

		TestSet testSet = new TestSet(testSetRequest.getClgRgsCd(), testSetRequest.getDriveDate(),
				testSetRequest.getStrtTime(), testSetRequest.getEndTime(), testSetRequest.getAllocatedTime(),
				testSetRequest.getTotalQues(), testSetRequest.getMaxMarks());

		testSet = testSetRepository.save(testSet);
		System.out.println("herer--> " + testSet);

		Map<String, String> reqQuesCatNCnt = testSetRequest.getReqQuesCatNCnt();
		for (Map.Entry<String, String> entry : reqQuesCatNCnt.entrySet()) {
			System.out.println("again " + testSet.getId() + "  " + entry.getKey() + "  " + entry.getValue());
			testQuestionSet = new TestQuestionSet(testSet.getId(), entry.getKey(), entry.getValue());
			System.out.println("nishant " + testQuestionSet);
			testQuestionSetRepository.save(testQuestionSet);
		}
		return ResponseEntity.ok(new GenericApiResponse(true, "Test Has created", testSet));

	}

	@GetMapping("/testsetdetail/{testId}")
	public ResponseEntity<?> testSertDetail(@PathVariable("testId") String testId) throws JsonProcessingException {
		System.out.println("NISHANT getting test set  Detail-> " + testId);
		Optional<TestSet> result = testSetRepository.findById(Long.parseLong(testId));
		TestSet testSet = null;
		CollegeDetail collegeDetail = null;
		if (result.isPresent()) {
			testSet = result.get();
			Optional<CollegeDetail> clgResult = collegeDetailRepository.findByClgRgstCd(testSet.getClgRgstCd());
			if (clgResult.isPresent()) {
				collegeDetail = clgResult.get();
			}
		}

		Map<String, Object> map = new HashMap<>();
		map.put("testSet", testSet);
		map.put("collegeDetail", collegeDetail);
		return ResponseEntity.ok(new GenericApiResponse(true, "Test Details", map));

	}
}
