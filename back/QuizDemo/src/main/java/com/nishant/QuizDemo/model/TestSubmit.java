package com.nishant.QuizDemo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TestSubmit")
public class TestSubmit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String emailId;
	private String testId;
	private String isTestSubmitted;
	public TestSubmit() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TestSubmit( String emailId, String testId, String isTestSubmitted) {
		super();
		this.emailId = emailId;
		this.testId = testId;
		this.isTestSubmitted = isTestSubmitted;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getTestId() {
		return testId;
	}
	public void setTestId(String testId) {
		this.testId = testId;
	}
	public String getIsTestSubmitted() {
		return isTestSubmitted;
	}
	public void setIsTestSubmitted(String isTestSubmitted) {
		this.isTestSubmitted = isTestSubmitted;
	}
	
	

}
