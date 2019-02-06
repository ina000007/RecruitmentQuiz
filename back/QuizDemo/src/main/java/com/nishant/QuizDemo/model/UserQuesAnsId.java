package com.nishant.QuizDemo.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Embeddable
//@Entity
//@Table(name = "UserQuesAnsId")
public class UserQuesAnsId implements Serializable{
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
	
	@Column
	String testId;
	@Column
	String emailId;
	@Column
	String quesId;
	public UserQuesAnsId(String testId, String emailId, String quesId) {
		super();
		this.testId = testId;
		this.emailId = emailId;
		this.quesId = quesId;
	}
	public UserQuesAnsId() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getTestId() {
		return testId;
	}
	public void setTestId(String testId) {
		this.testId = testId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
	public String getQuesId() {
		return quesId;
	}
	public void setQuesId(String quesId) {
		this.quesId = quesId;
	}
	@Override
	public int hashCode() {
		return Objects.hash( getTestId(),getEmailId(),getQuesId());
	}
	@Override
	public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserQuesAnsId)) return false;
        UserQuesAnsId that = (UserQuesAnsId) o;
        return Objects.equals(getTestId(), that.getTestId()) &&
                Objects.equals(getEmailId(), that.getEmailId()) &&
                        Objects.equals(getQuesId(), that.getQuesId());
	}
	
}
