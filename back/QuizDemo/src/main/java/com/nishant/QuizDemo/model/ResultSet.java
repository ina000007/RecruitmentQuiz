package com.nishant.QuizDemo.model;

public class ResultSet {

	private String emailId;
	private String obtainedMrk;
	private String name;
	
    public int compareTo(ResultSet rs) 
    { 
        return Integer.parseInt(rs.obtainedMrk) - Integer.parseInt(this.obtainedMrk); 
    } 
  
	
	public ResultSet() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ResultSet(String emailId, String obtainedMrk, String name) {
		super();
		this.emailId = emailId;
		this.obtainedMrk = obtainedMrk;
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getObtainedMrk() {
		return obtainedMrk;
	}
	public void setObtainedMrk(String obtainedMrk) {
		this.obtainedMrk = obtainedMrk;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "ResultSet [emailId=" + emailId + ", obtainedMrk=" + obtainedMrk + ", name=" + name + "]";
	}
	
	
}
