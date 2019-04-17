package com.nishant.QuizDemo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "UserTestTimeLeft")
public class UserTestTimeLeft {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String timeLeft;

	@NotBlank
	private String emailId;

	@NotBlank
	private String testId;
	

	public UserTestTimeLeft() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserTestTimeLeft(String timeLeft, @NotBlank String emailId, @NotBlank String testId) {
		super();
		this.timeLeft = timeLeft;
		this.emailId = emailId;
		this.testId = testId;
	}

	public String getTimeLeft() {
		return timeLeft;
	}

	public void setTimeLeft(String timeLeft) {
		this.timeLeft = timeLeft;
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

	@Override
	public String toString() {
		return "UserTestTimeLeft [id=" + id + ", timeLeft=" + timeLeft + ", emailId=" + emailId + ", testId=" + testId
				+ "]";
	}
	

}
