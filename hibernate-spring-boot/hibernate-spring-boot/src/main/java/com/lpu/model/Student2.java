package com.lpu.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("student")
public class Student2 extends Person{
	
	int roll;
	String branch;
	public Student2(int pid, String name, String phone, int roll, String branch) {
		super(pid, name, phone);
		this.roll = roll;
		this.branch = branch;
	}
	public Student2() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student2(int pid, String name, String phone) {
		super(pid, name, phone);
		// TODO Auto-generated constructor stub
	}
	public int getRoll() {
		return roll;
	}
	public void setRoll(int roll) {
		this.roll = roll;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	@Override
	public String toString() {
		return "Student2 [roll=" + roll + ", branch=" + branch + ", pid=" + pid + ", name=" + name + ", phone=" + phone
				+ "]";
	}
	
	
}
