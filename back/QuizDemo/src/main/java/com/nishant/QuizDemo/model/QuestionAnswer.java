package com.nishant.QuizDemo.model;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "QuestionAnswer", uniqueConstraints = { @UniqueConstraint(columnNames = { "questionDesc" }) })
public class QuestionAnswer {

	public QuestionAnswer() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String questionDesc;

	@NotBlank
	private String option1;

	@NotBlank
	private String option2;

	@NotBlank
	private String option3;

	@NotBlank
	private String option4;

	@NotBlank
	private String answer;

	@NotBlank
	private String mark;

	@NotBlank
	private String type;

	@NotBlank
	private String date;

	public QuestionAnswer(@NotBlank String questionDesc, @NotBlank String option1, @NotBlank String option2,
			@NotBlank String option3, @NotBlank String option4, @NotBlank String answer, @NotBlank String mark,
			@NotBlank String type, @NotBlank String date) {
		super();
		this.questionDesc = questionDesc;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.answer = answer;
		this.mark = mark;
		this.type = type;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestionDesc() {
		return questionDesc;
	}

	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public String getOption4() {
		return option4;
	}

	public void setOption4(String option4) {
		this.option4 = option4;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "QuestionAnswer [id=" + id + ", questionDesc=" + questionDesc + ", option1=" + option1 + ", option2="
				+ option2 + ", option3=" + option3 + ", option4=" + option4 + ", answer=" + answer + ", mark=" + mark
				+ ", type=" + type + ", date=" + date + "]";
	}
}
