package com.hibernate.Relationships.ManyToOne;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int empid;
	
	String name;
	
	@ManyToOne
	@JoinColumn(name = "dept_id")
	Department dept;

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(String name, Department dept) {
		super();
		this.name = name;
		this.dept = dept;
	}

	public int getEmpid() {
		return empid;
	}

	public String getName() {
		return name;
	}

	public Department getDept() {
		return dept;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	@Override
	public String toString() {
		return "Employee [empid=" + empid + ", name=" + name + ", dept=" + dept.getName() + "]";
	}
	
	
	
	
	
}
