package com.nishant.QuizDemo.payload;

import java.util.Map;

import javax.validation.constraints.NotBlank;

/**
 * @author nishant.agarwal
 *
 */
public class TestSetRequest {

    private Long clgRgsCd;

    @NotBlank
    private String driveDate;


    private String strtTime;


    private String endTime;
	@NotBlank
	private String allocatedTime;	
	
	@NotBlank
	private String totalQues;
	
	@NotBlank
	private String maxMarks;
	

	private Map<String, String> reqQuesCatNCnt ;

	public TestSetRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TestSetRequest( Long clgRgsCd, @NotBlank String driveDate, String strtTime, String endTime,
			@NotBlank String allocatedTime, @NotBlank String totalQues, @NotBlank String maxMarks,
			 Map<String, String> reqQuesCatNCnt) {
		super();
		this.clgRgsCd = clgRgsCd;
		this.driveDate = driveDate;
		this.strtTime = strtTime;
		this.endTime = endTime;
		this.allocatedTime = allocatedTime;
		this.totalQues = totalQues;
		this.maxMarks = maxMarks;
		this.reqQuesCatNCnt = reqQuesCatNCnt;
	}
	

	@Override
	public String toString() {
		return "TestSetRequest [clgRgsCd=" + clgRgsCd + ", driveDate=" + driveDate + ", strtTime=" + strtTime
				+ ", endTime=" + endTime + ", allocatedTime=" + allocatedTime + ", totalQues=" + totalQues
				+ ", maxMarks=" + maxMarks + ", reqQuesCatNCnt=" + reqQuesCatNCnt + "]";
	}

	public Long getClgRgsCd() {
		return clgRgsCd;
	}

	public void setClgRgsCd(Long clgRgsCd) {
		this.clgRgsCd = clgRgsCd;
	}

	public String getDriveDate() {
		return driveDate;
	}

	public void setDriveDate(String driveDate) {
		this.driveDate = driveDate;
	}

	public String getStrtTime() {
		return strtTime;
	}

	public void setStrtTime(String strtTime) {
		this.strtTime = strtTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getAllocatedTime() {
		return allocatedTime;
	}

	public void setAllocatedTime(String allocatedTime) {
		this.allocatedTime = allocatedTime;
	}

	public String getTotalQues() {
		return totalQues;
	}

	public void setTotalQues(String totalQues) {
		this.totalQues = totalQues;
	}

	public String getMaxMarks() {
		return maxMarks;
	}

	public void setMaxMarks(String maxMarks) {
		this.maxMarks = maxMarks;
	}

	public Map<String, String> getReqQuesCatNCnt() {
		return reqQuesCatNCnt;
	}

	public void setReqQuesCatNCnt(Map<String, String> reqQuesCatNCnt) {
		this.reqQuesCatNCnt = reqQuesCatNCnt;
	}
	
	
}
