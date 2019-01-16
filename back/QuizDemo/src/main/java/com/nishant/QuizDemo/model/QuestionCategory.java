package com.nishant.QuizDemo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "QuestionCategory",uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "questionCategory"
            })
    })
public class QuestionCategory {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String questionCategory;
    
	public QuestionCategory( @NotBlank String questionCategory) {
		super();
		this.questionCategory = questionCategory;
	}
	public QuestionCategory() {
		
	}
	@Override
	public String toString() {
		return "QuestionCategory [id=" + id + ", questionCategory=" + questionCategory + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestionCategory() {
		return questionCategory;
	}

	public void setQuestionCategory(String questionCategory) {
		this.questionCategory = questionCategory;
	}
}
