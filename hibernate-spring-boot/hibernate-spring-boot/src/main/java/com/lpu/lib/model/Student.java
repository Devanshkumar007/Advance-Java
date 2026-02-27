package com.lpu.lib.model;

import java.sql.Date;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


@Entity // this class is associated with some table in the database
public class Student {
	@SequenceGenerator(name = "stid" , initialValue = 101 , allocationSize = 1)
	@Id
	@GeneratedValue(generator="stid" , strategy = GenerationType.SEQUENCE)
	int roll ;
	
	String sname;
	String branch;
	String phone;
	Date dob;
	Integer depid;
	String gender;
	public Student(int roll, String sname, String branch, String phone, Date dob, String gender, Integer depid) {
		super();
		this.roll = roll;
		this.sname = sname;
		this.branch = branch;
		this.phone = phone;
		this.dob = dob;
		this.gender = gender;
		this.depid = depid;
	}
	public Student() {
		super();
		// TODO Auto-generated constructor stubS
	}
	public int getRoll() {
		return roll;
	}
	public void setRoll(int roll) {
		this.roll = roll;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getDepid() {
		return depid;
	}
	public void setDepid(Integer depid) {
		this.depid = depid;
	}
	
	
	@Override
	public String toString() {
		return "Student [roll=" + roll + ", sname=" + sname + ", branch=" + branch + ", phone=" + phone + ", dob=" + dob
				+ ", gender=" + gender + ", depid=" + depid + "]";
	}
	
	
}
