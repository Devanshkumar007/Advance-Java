package com.lpu.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("faculty")
public class Faculty extends Person {
	
	
	int facid;
	String designation;
	
	public Faculty(int pid, String name, String phone, int facid, String designation) {
		super(pid, name, phone);
		this.facid = facid;
		this.designation = designation;
	}
	public Faculty() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getFacid() {
		return facid;
	}
	public void setFacid(int facid) {
		this.facid = facid;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	@Override
	public String toString() {
		return "Faculty [facid=" + facid + ", designation=" + designation + ", pid=" + pid + ", name=" + name
				+ ", phone=" + phone + "]";
	}
	
	
}
