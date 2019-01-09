package com.nishant.QuizDemo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "QuestionCatagory",uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "questionCatagory"
            })
    })
public class QuestionCatagory {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String questionCatagory;

	public QuestionCatagory( @NotBlank String questionCatagory) {
		super();
		this.questionCatagory = questionCatagory;
	}

	@Override
	public String toString() {
		return "QuestionCatagory [id=" + id + ", questionCatagory=" + questionCatagory + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestionCatagory() {
		return questionCatagory;
	}

	public void setQuestionCatagory(String questionCatagory) {
		this.questionCatagory = questionCatagory;
	}
}
