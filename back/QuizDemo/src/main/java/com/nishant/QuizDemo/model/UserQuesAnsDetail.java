package com.nishant.QuizDemo.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
 
@Entity
@Table(name = "UserQuesAnsDetail")
public class UserQuesAnsDetail {

	@EmbeddedId
	private UserQuesAnsId userQuesAnsId;
	private String selectedOption;
	private String correctOption;
	private String isCorrect;
	private String timeLeft;
	public String getTimeLeft() {
		return timeLeft;
	}
	public void setTimeLeft(String timeLeft) {
		this.timeLeft = timeLeft;
	}
	public UserQuesAnsDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserQuesAnsDetail( UserQuesAnsId userQuesAnsId, String selectedOption, String correctOption,
			String isCorrect, String timeLeft) {
		super();
		this.userQuesAnsId = userQuesAnsId;
		this.selectedOption = selectedOption;
		this.correctOption = correctOption;
		this.isCorrect = isCorrect;
		this.timeLeft = timeLeft;
	}
	public UserQuesAnsId getUserQuesAnsId() {
		return userQuesAnsId;
	}
	public void setUserQuesAnsId(UserQuesAnsId userQuesAnsId) {
		this.userQuesAnsId = userQuesAnsId;
	}
	public String getSelectedOption() {
		return selectedOption;
	}
	public void setSelectedOption(String selectedOption) {
		this.selectedOption = selectedOption;
	}
	public String getCorrectOption() {
		return correctOption;
	}
	public void setCorrectOption(String correctOption) {
		this.correctOption = correctOption;
	}
	public String getIsCorrect() {
		return isCorrect;
	}
	public void setIsCorrect(String isCorrect) {
		this.isCorrect = isCorrect;
	}
	@Override
	public String toString() {
		return "UserQuesAnsDetail [userQuesAnsId=" + userQuesAnsId + ", selectedOption=" + selectedOption
				+ ", correctOption=" + correctOption + ", isCorrect=" + isCorrect + "]";
	}
	
	
}
