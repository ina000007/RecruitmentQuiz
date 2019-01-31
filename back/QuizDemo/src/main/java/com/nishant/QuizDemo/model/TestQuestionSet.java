package com.nishant.QuizDemo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "TestQuestionSet")
public class TestQuestionSet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	 
	private Long testSetId;

	@NotBlank
	private String quesCatId;

	@NotBlank
	private String noOfQues;

	public TestQuestionSet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TestQuestionSet(  Long tesSetId, @NotBlank String quesCatId, @NotBlank String noOfQues) {
		super();
		this.testSetId = tesSetId;
		this.quesCatId = quesCatId;
		this.noOfQues = noOfQues;
	}

	@Override
	public String toString() {
		return "TestQuestionSet [id=" + id + ", tesSetId=" + testSetId + ", quesCatId=" + quesCatId + ", noOfQues="
				+ noOfQues + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTesSetId() {
		return testSetId;
	}

	public void setTesSetId(Long tesSetId) {
		this.testSetId = tesSetId;
	}

	public String getQuesCatId() {
		return quesCatId;
	}

	public void setQuesCatId(String quesCatId) {
		this.quesCatId = quesCatId;
	}

	public String getNoOfQues() {
		return noOfQues;
	}

	public void setNoOfQues(String noOfQues) {
		this.noOfQues = noOfQues;
	}
	
}
