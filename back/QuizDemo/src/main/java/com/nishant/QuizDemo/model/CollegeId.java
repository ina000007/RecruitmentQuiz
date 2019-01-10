package com.nishant.QuizDemo.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CollegeId implements Serializable{
	
	@Column
	private String clgName;
	@Column
	private String clgLocation;
	@Column
	private String clgCity;
	
	public CollegeId() {
		// TODO Auto-generated constructor stub
	}
	public CollegeId(String clgName, String clgLocation, String clgCity) {
		super();
		this.clgName = clgName;
		this.clgLocation = clgLocation;
		this.clgCity = clgCity;
	}
	public String getClgName() {
		return clgName;
	}
	public void setClgName(String clgName) {
		this.clgName = clgName;
	}
	public String getClgLocation() {
		return clgLocation;
	}
	public void setClgLocation(String clgLocation) {
		this.clgLocation = clgLocation;
	}
	public String getClgCity() {
		return clgCity;
	}
	public void setClgCity(String clgCity) {
		this.clgCity = clgCity;
	}
	@Override
	public int hashCode() {
		return Objects.hash(getClgName(), getClgLocation(),getClgCity());
	}
	@Override
	public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CollegeId)) return false;
        CollegeId that = (CollegeId) o;
        return Objects.equals(getClgName(), that.getClgName()) &&
                Objects.equals(getClgLocation(), that.getClgLocation()) &&
                        Objects.equals(getClgCity(), that.getClgCity());
	}
	@Override
	public String toString() {
		return "CollegeId [clgName=" + clgName + ", clgLocation=" + clgLocation + ", clgCity=" + clgCity + "]";
	}
	

}
