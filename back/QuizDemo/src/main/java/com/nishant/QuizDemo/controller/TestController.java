package com.nishant.QuizDemo.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nishant.QuizDemo.model.Question;
import com.nishant.QuizDemo.model.QuestionAnswer;
import com.nishant.QuizDemo.model.TestQuestionSet;
import com.nishant.QuizDemo.model.TestSet;
import com.nishant.QuizDemo.model.UserQuesAnsDetail;
import com.nishant.QuizDemo.model.UserQuesAnsId;
import com.nishant.QuizDemo.model.UserTestTimeLeft;
import com.nishant.QuizDemo.payload.GenericApiResponse;
import com.nishant.QuizDemo.repository.QuestionAnswerRepository;
import com.nishant.QuizDemo.repository.TestQuestionSetRepository;
import com.nishant.QuizDemo.repository.TestSetRepository;
import com.nishant.QuizDemo.repository.UserQuesAnsDetailRepository;
import com.nishant.QuizDemo.repository.UserTestTimeLeftRepository;
import com.nishant.QuizDemo.utils.Utility;

@RestController
@RequestMapping("/api/test")
public class TestController {

	private static final Logger logger = LoggerFactory.getLogger(TestController.class);
	@Autowired
	TestSetRepository testSetRepository;
	@Autowired
	TestQuestionSetRepository testQuestionSetRepository;

	@Autowired
	private QuestionAnswerRepository questionAnswerRepository;

	@Autowired
	UserQuesAnsDetailRepository userQuesAnsDetailRepository;

	@Autowired
	UserTestTimeLeftRepository userTestTimeLeftRepository;

	@RequestMapping(value = "/initialize/{testId}/{emailId}", method = RequestMethod.GET)
	public ResponseEntity<?> initialize(@PathVariable("testId") String testId,
			@PathVariable("emailId") String emailId) {

		UserQuesAnsDetail userQuesAnsDetail;
		UserQuesAnsId userQuesAnsId;
		List<Question> questionLst = new ArrayList<>();

		TestSet testSet;
		List<TestQuestionSet> testQuestionSetLst;
		String cate;
		String numOfQues;
		List<QuestionAnswer> questionAnswerLst = new ArrayList();
//		searching set test by testId 
		Optional<TestSet> result = testSetRepository.findById(Long.parseLong(testId));

//		Check if user not registered in this test
		if (userQuesAnsDetailRepository.findByUserQuesAnsIdEmailIdTestId(emailId, testId).size() == 0) {
//			if test set is present by the requested testid
			if (result.isPresent()) {
				testSet = result.get();
				userTestTimeLeftRepository.save(new UserTestTimeLeft(testSet.getAllocatedTime(), emailId, testId));
				testQuestionSetLst = testQuestionSetRepository.findByTestSetId(Long.parseLong(testId));
				for (TestQuestionSet testQuestionSet : testQuestionSetLst) {
					cate = testQuestionSet.getQuesCatId();
					numOfQues = testQuestionSet.getNoOfQues();
					List<QuestionAnswer> tempLst = questionAnswerRepository.findAllByType(cate);
					Collections.shuffle(tempLst);
					questionAnswerLst.addAll(tempLst.subList(0, Integer.parseInt(numOfQues)));
				}
				int i = 0;
				for (QuestionAnswer questionAnswer : questionAnswerLst) {
					userQuesAnsId = new UserQuesAnsId(testId, emailId, questionAnswer.getId() + "");
					userQuesAnsDetail = new UserQuesAnsDetail(userQuesAnsId, "", questionAnswer.getAnswer(), "",
							testSet.getAllocatedTime());
					userQuesAnsDetailRepository.save(userQuesAnsDetail);
					System.out
							.println(++i + " ================== " + questionAnswer + "  " + testSet.getAllocatedTime());

//					Not sure
					questionLst.add(new Question(questionAnswer.getId(), questionAnswer.getQuestionDesc(),
							questionAnswer.getOption1(), questionAnswer.getOption2(), questionAnswer.getOption3(),
							questionAnswer.getOption4(), ""));
				}
			}
			return ResponseEntity.ok(new GenericApiResponse(true, "Test Created", questionLst));
		} else {
			for (UserQuesAnsDetail u : userQuesAnsDetailRepository.findByUserQuesAnsIdEmailIdTestId(emailId, testId)) {
				String qId = u.getUserQuesAnsId().getQuesId();
				Optional<QuestionAnswer> res = questionAnswerRepository.findById(Long.parseLong(qId));
				if (res.isPresent()) {
					QuestionAnswer quesAns = res.get();
					questionLst.add(new Question(Long.parseLong(qId), quesAns.getQuestionDesc(), quesAns.getOption1(),
							quesAns.getOption2(), quesAns.getOption3(), quesAns.getOption4(), u.getSelectedOption()));
				}

			}
			System.out.println("This test set already initialize for this emailId");
			return ResponseEntity.ok(new GenericApiResponse(true, "Test Already Created", questionLst));
		}
	}

	@PostMapping("/saveans")
	public ResponseEntity<?> saveAns(@RequestBody Map<String, Object> payLoad) {

		UserQuesAnsId userQuesAnsId = new UserQuesAnsId(payLoad.get("testId") + "", payLoad.get("emailId") + "",
				payLoad.get("quesId") + "");
		Optional<UserQuesAnsDetail> res = userQuesAnsDetailRepository.findByUserQuesAnsId(userQuesAnsId);
		UserQuesAnsDetail u;
		String selectedOptionVal;
		if (res.isPresent()) {
			u = res.get();
			selectedOptionVal = payLoad.get("selectedOptionVal") + "";
			u.setSelectedOption(selectedOptionVal);
			if (selectedOptionVal.equalsIgnoreCase(u.getCorrectOption()))
				u.setIsCorrect("1");
			else
				u.setIsCorrect("0");
			userQuesAnsDetailRepository.save(u);
		}
		System.out.println(payLoad);

		return ResponseEntity.ok(new GenericApiResponse(true, "Save", null));
	}

	@PostMapping("/reset")
	public ResponseEntity<?> reset(@RequestBody Map<String, Object> payLoad) {

		UserQuesAnsId userQuesAnsId = new UserQuesAnsId(payLoad.get("testId") + "", payLoad.get("emailId") + "",
				payLoad.get("quesId") + "");
		Optional<UserQuesAnsDetail> res = userQuesAnsDetailRepository.findByUserQuesAnsId(userQuesAnsId);
		UserQuesAnsDetail u;
		String selectedOptionVal;
		Question question = null;
		QuestionAnswer quesAns = null;
		if (res.isPresent()) {
			u = res.get();
//			selectedOptionVal = payLoad.get("selectedOptionVal")+"";
			u.setSelectedOption("");
			u.setIsCorrect("");
			userQuesAnsDetailRepository.save(u);

			Optional<QuestionAnswer> res2 = questionAnswerRepository
					.findById(Long.parseLong(payLoad.get("quesId") + ""));
			if (res2.isPresent()) {
				quesAns = res2.get();
				question = new Question(Long.parseLong((payLoad.get("quesId") + "")), quesAns.getQuestionDesc(),
						quesAns.getOption1(), quesAns.getOption2(), quesAns.getOption3(), quesAns.getOption4(),
						u.getSelectedOption());
			}
		}
		System.out.println(payLoad);
		return ResponseEntity.ok(new GenericApiResponse(true, "Reset", question));
	}
//  Time update
	@RequestMapping(value = "/ping/{testId}/{emailId}", method = RequestMethod.GET)
	public void ping(@PathVariable("testId") String testId, @PathVariable("emailId") String emailId) {

		UserTestTimeLeft userTestTimeLeft = userTestTimeLeftRepository.findByUserTestTimeLeftByEmailIdTestId(emailId,
				testId);

		String time = userTestTimeLeft.getTimeLeft();
		String newTime = reducedTime(time, 60);
		userTestTimeLeft.setTimeLeft(newTime);
		userTestTimeLeftRepository.save(userTestTimeLeft);
	}
	@RequestMapping(value = "/time/{testId}/{emailId}", method = RequestMethod.GET)
	public Map getTimer(@PathVariable("testId") String testId, @PathVariable("emailId") String emailId) {
		UserTestTimeLeft userTestTimeLeft = userTestTimeLeftRepository.findByUserTestTimeLeftByEmailIdTestId(emailId,
				testId);
		System.out.println("---------------------------------------------------------- "+userTestTimeLeft);
		Map map = new HashMap();
		String time = userTestTimeLeft.getTimeLeft();
		map.put("time", Utility.hhmmssToSec(time));
		return map;
	}
	public String reducedTime(String time, int i) {
		int newTime;
		newTime = Utility.hhmmssToSec(time) - i;
		System.out.println(time+" -----==+++++++++++++++++++ "+newTime+"   =====  "+Utility.secTohhmmss(newTime));
		return Utility.secTohhmmss(newTime);
	}
//	Time updater onDestroy
	@RequestMapping(value = "/ping/{time}/{testId}/{emailId}", method = RequestMethod.GET)
	public void timeUpdater(@PathVariable("testId") String testId, @PathVariable("emailId") String emailId, @PathVariable("time") String time) {

		UserTestTimeLeft userTestTimeLeft = userTestTimeLeftRepository.findByUserTestTimeLeftByEmailIdTestId(emailId,
				testId);
		time = Utility.secTohhmmss(Integer.parseInt(time));
		userTestTimeLeft.setTimeLeft(time);
		System.out.println(".............saving time.........."+time);
		userTestTimeLeftRepository.save(userTestTimeLeft);
	}
	
}
