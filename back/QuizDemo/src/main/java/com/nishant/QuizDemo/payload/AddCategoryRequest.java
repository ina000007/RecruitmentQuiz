package com.nishant.QuizDemo.payload;

import javax.validation.constraints.NotBlank;

public class AddCategoryRequest {
	@NotBlank
    private String questionCategory;

	public String getQuestionCategory() {
		return questionCategory;
	}

	public void setQuestionCategory(String questionCategory) {
		this.questionCategory = questionCategory;
	}
	
	
}
