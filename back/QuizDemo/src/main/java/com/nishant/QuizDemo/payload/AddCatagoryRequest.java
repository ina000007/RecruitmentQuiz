package com.nishant.QuizDemo.payload;

import javax.validation.constraints.NotBlank;

public class AddCatagoryRequest {
	@NotBlank
    private String questionCatagory;

	public String getQuestionCatagory() {
		return questionCatagory;
	}

	public void setQuestionCatagory(String questionCatagory) {
		this.questionCatagory = questionCatagory;
	}
	
	
}
