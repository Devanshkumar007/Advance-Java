package com.lovely.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


public class Student {
	
	private String email;
	private String name;
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(String email, String name) {
		super();
		this.email = email;
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public String getName() {
		return name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Student [email=" + email + ", name=" + name + "]";
	}
}
