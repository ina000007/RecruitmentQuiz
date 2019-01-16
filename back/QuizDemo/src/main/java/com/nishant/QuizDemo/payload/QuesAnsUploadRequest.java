package com.nishant.QuizDemo.payload;


import javax.validation.constraints.NotBlank;

public class QuesAnsUploadRequest {
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
    private Long mark;    
    
    @NotBlank
    private String type;

	public QuesAnsUploadRequest(@NotBlank String questionDesc, @NotBlank String option1, @NotBlank String option2,
			@NotBlank String option3, @NotBlank String option4, @NotBlank String answer, @NotBlank Long mark,
			@NotBlank String type) {
		super();
		this.questionDesc = questionDesc;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.answer = answer;
		this.mark = mark;
		this.type = type;
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

	public Long getMark() {
		return mark;
	}

	public void setMark(Long mark) {
		this.mark = mark;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
