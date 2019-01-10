package com.nishant.QuizDemo.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "CollegeDetail")
public class CollegeDetail {
	
	@EmbeddedId
	private CollegeId collegeId;
	
    private  String clgState;
    private  String clgUniversity;
    
	public CollegeDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CollegeDetail(CollegeId collegeId, String clgState, String clgUniversity) {
		super();
		this.collegeId = collegeId;
		this.clgState = clgState;
		this.clgUniversity = clgUniversity;
	}
	public CollegeId getCollegeId() {
		return collegeId;
	}
	public void setCollegeId(CollegeId collegeId) {
		this.collegeId = collegeId;
	}
	public String getClgState() {
		return clgState;
	}
	public void setClgState(String clgState) {
		this.clgState = clgState;
	}
	public String getClgUniversity() {
		return clgUniversity;
	}
	public void setClgUniversity(String clgUniversity) {
		this.clgUniversity = clgUniversity;
	}
	@Override
	public String toString() {
		return "CollegeDetail [collegeId=" + collegeId + ", clgState=" + clgState + ", clgUniversity=" + clgUniversity
				+ "]";
	}
}
