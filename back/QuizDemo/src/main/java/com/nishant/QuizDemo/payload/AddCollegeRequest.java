package com.nishant.QuizDemo.payload;

import javax.persistence.EmbeddedId;

import com.nishant.QuizDemo.model.CollegeId;

public class AddCollegeRequest {
	
	private CollegeId collegeId;
	
    private  String clgState;
    private  String clgUniversity;
    
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
		return "AddCollegeRequest [collegeId=" + collegeId + ", clgState=" + clgState + ", clgUniversity="
				+ clgUniversity + "]";
	}
    
}
