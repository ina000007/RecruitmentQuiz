package com.nishant.QuizDemo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "TestSet")
public class TestSet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long clgRgstCd;

	@NotBlank
	private String driveDate;

	private String startTime;
	private String endTime;
	
	@NotBlank
	private String allocatedTime;	
	
	@NotBlank
	private String totalQues;
	
	@NotBlank
	private String maxMarks;

	public TestSet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TestSet( Long clgRgstCd, @NotBlank String driveDate, String startTime, String endTime,
			@NotBlank String allocatedTime, @NotBlank String totalQues, @NotBlank String maxMarks) {
		super();
		this.clgRgstCd = clgRgstCd;
		this.driveDate = driveDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.allocatedTime = allocatedTime;
		this.totalQues = totalQues;
		this.maxMarks = maxMarks;
	}

	@Override
	public String toString() {
		return "TestSet [id=" + id + ", clgRgstCd=" + clgRgstCd + ", driveDate=" + driveDate + ", startTime="
				+ startTime + ", endTime=" + endTime + ", allocatedTime=" + allocatedTime + ", totalQues=" + totalQues
				+ ", maxMarks=" + maxMarks + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getClgRgstCd() {
		return clgRgstCd;
	}

	public void setClgRgstCd(Long clgRgstCd) {
		this.clgRgstCd = clgRgstCd;
	}

	public String getDriveDate() {
		return driveDate;
	}

	public void setDriveDate(String driveDate) {
		this.driveDate = driveDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
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
	
	
	
}
