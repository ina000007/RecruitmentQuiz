package com.nishant.QuizDemo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nishant.QuizDemo.model.ResultSet;
import com.nishant.QuizDemo.model.TestQuestionSet;
import com.nishant.QuizDemo.model.TestSet;
import com.nishant.QuizDemo.model.User;
import com.nishant.QuizDemo.payload.GenericApiResponse;
import com.nishant.QuizDemo.payload.TestSetRequest;
import com.nishant.QuizDemo.repository.TestSetRepository;
import com.nishant.QuizDemo.repository.UserQuesAnsDetailRepository;
import com.nishant.QuizDemo.repository.UserRepository;
import com.nishant.QuizDemo.utils.Utility;

@RestController
@RequestMapping("/api/test")
public class TestResultcontroller {

	@Autowired
	UserQuesAnsDetailRepository userQuesAnsDetailRepository;

	@Autowired
	TestSetRepository testSetRepository;
	@Autowired
	UserRepository userRepository;

	@GetMapping("/result/{testId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> testResult(@PathVariable("testId") String testId) {
		System.out.println("NISHANT Rresult ->" + testId);
		ResultSet resultSet;
		List<ResultSet> resultLst = new ArrayList<ResultSet>();
		User user;
		Optional<User> userRes;
		String name="";
		String marks="";
		String emailId="";
		System.out.println(userQuesAnsDetailRepository.userResultByTestId(testId));
		List<Object[]> results = userQuesAnsDetailRepository.userResultByTestId(testId);
		for (int i = 0; i < results.size(); i++) {
			Object[] arr = results.get(i);

			emailId = arr[0] + "";
			marks = (arr[1] == null)?"0":arr[1] + "";
			userRes = userRepository.findByEmail(emailId);
			if (!userRes.isPresent())
				userRes = userRepository.findByUsername(emailId);
			user = userRes.get();
			name = user.getName();
			resultSet = new ResultSet(emailId,marks,name);

			resultLst.add(resultSet);
		}

		Optional<TestSet> res = testSetRepository.findById(Long.parseLong(testId));
		TestSet testSet = res.get();
		Map<String, Object> obj = new HashMap();
		obj.put("TestSetDetail", testSet);
		obj.put("resultSet", resultLst);
		return ResponseEntity.ok(new GenericApiResponse(true, "Test Result", obj));

	}
	
//	public ResponseEntity<?> testResult(@PathVariable("testId") String testId) {
//		System.out.println("NISHANT Rresult ->" + testId);
//		HashMap<String, String> resultMap;
//		List resultLst = new ArrayList<>();
//		User user;
//		Optional<User> userRes;
//		System.out.println(userQuesAnsDetailRepository.userResultByTestId(testId));
//		List<Object[]> results = userQuesAnsDetailRepository.userResultByTestId(testId);
//		for (int i = 0; i < results.size(); i++) {
//			Object[] arr = results.get(i);
//
//			resultMap = new HashMap<>();
//			resultMap.put("emailId", arr[0] + "");
//			if (arr[1] == null)
//				resultMap.put("obtainedMrk", "0");
//			else
//				resultMap.put("obtainedMrk", arr[1] + "");
//
//			userRes = userRepository.findByEmail(arr[0] + "");
//			if (!userRes.isPresent())
//				userRes = userRepository.findByUsername(arr[0] + "");
//			user = userRes.get();
//			resultMap.put("name", user.getName());
//			resultLst.add(resultMap);
//		}
//
//		Optional<TestSet> res = testSetRepository.findById(Long.parseLong(testId));
//		TestSet testSet = res.get();
//		Map<String, Object> obj = new HashMap();
//		obj.put("TestSetDetail", testSet);
//		obj.put("resultSet", resultLst);
//		return ResponseEntity.ok(new GenericApiResponse(true, "Test Result", obj));
//
//	}
}
