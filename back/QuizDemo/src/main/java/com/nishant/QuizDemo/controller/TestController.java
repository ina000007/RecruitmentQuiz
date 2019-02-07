package com.nishant.QuizDemo.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nishant.QuizDemo.model.Question;
import com.nishant.QuizDemo.model.QuestionAnswer;
import com.nishant.QuizDemo.model.TestQuestionSet;
import com.nishant.QuizDemo.model.TestSet;
import com.nishant.QuizDemo.model.UserQuesAnsDetail;
import com.nishant.QuizDemo.model.UserQuesAnsId;
import com.nishant.QuizDemo.payload.GenericApiResponse;
import com.nishant.QuizDemo.repository.QuestionAnswerRepository;
import com.nishant.QuizDemo.repository.TestQuestionSetRepository;
import com.nishant.QuizDemo.repository.TestSetRepository;
import com.nishant.QuizDemo.repository.UserQuesAnsDetailRepository;

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

	@RequestMapping(value = "/initialize/{testId}/{emailId}", method = RequestMethod.GET)
	public ResponseEntity<?>  initialize(@PathVariable("testId") String testId, @PathVariable("emailId") String emailId) {

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
					userQuesAnsDetail = new UserQuesAnsDetail(userQuesAnsId, "", questionAnswer.getAnswer(), "");
					userQuesAnsDetailRepository.save(userQuesAnsDetail);
					System.out.println(++i + " = " + questionAnswer);
					
//					Not sure
					questionLst.add(new Question(questionAnswer.getId(), questionAnswer.getQuestionDesc(), questionAnswer.getOption1(), 
							questionAnswer.getOption2(), questionAnswer.getOption3(), questionAnswer.getOption4(), ""));
				}
			}
			return ResponseEntity.ok(new GenericApiResponse(true, "Test Created", questionLst));
		}
		else {
			for(UserQuesAnsDetail u : userQuesAnsDetailRepository.findByUserQuesAnsIdEmailIdTestId(emailId, testId)) {
				String qId = u.getUserQuesAnsId().getQuesId();
				Optional<QuestionAnswer> res = questionAnswerRepository.findById(Long.parseLong(qId));
				if(res.isPresent()) {
					QuestionAnswer quesAns= res.get();	
				questionLst.add(new Question(Long.parseLong(qId), quesAns.getQuestionDesc(), quesAns.getOption1(), 
						quesAns.getOption2(), quesAns.getOption3(), quesAns.getOption4(), u.getSelectedOption()));
				}
				
			}
			System.out.println("This test set already initialize for this emailId");
			return ResponseEntity.ok(new GenericApiResponse(true, "Test Already Created", questionLst));
		}
	}

}
