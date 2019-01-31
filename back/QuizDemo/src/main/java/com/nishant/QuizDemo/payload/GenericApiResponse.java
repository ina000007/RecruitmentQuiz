package com.nishant.QuizDemo.payload;

public class GenericApiResponse {
    private Boolean success;
    private String message;
    private Object obj;
    
	public GenericApiResponse(Boolean success, String message, Object obj) {
		super();
		this.success = success;
		this.message = message;
		this.obj = obj;
	}

	public GenericApiResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
}
