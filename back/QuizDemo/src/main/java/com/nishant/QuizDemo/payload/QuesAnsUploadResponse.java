package com.nishant.QuizDemo.payload;

public class QuesAnsUploadResponse {
	private Boolean success;
	private String quesDesc;
	private String quesId;
	public QuesAnsUploadResponse(Boolean success, String quesDesc, String quesId) {
		super();
		this.success = success;
		this.quesDesc = quesDesc;
		this.quesId = quesId;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getQuesDesc() {
		return quesDesc;
	}
	public void setQuesDesc(String quesDesc) {
		this.quesDesc = quesDesc;
	}
	public String getQuesId() {
		return quesId;
	}
	public void setQuesId(String quesId) {
		this.quesId = quesId;
	}
	
	
}
