package com.lpu.model;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type" , discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("person")
public class Person {

	@Id
	@GeneratedValue
	int pid;
	String name;
	String phone;
	
	
	public Person(int pid, String name, String phone) {
		super();
		this.pid = pid;
		this.name = name;
		this.phone = phone;
	}


	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getPid() {
		return pid;
	}


	public void setPid(int pid) {
		this.pid = pid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	@Override
	public String toString() {
		return "Person [pid=" + pid + ", name=" + name + ", phone=" + phone + "]";
	}
	
	
	
	
	
	
}
