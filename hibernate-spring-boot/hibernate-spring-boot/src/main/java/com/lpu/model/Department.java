package com.lpu.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Department {
	@SequenceGenerator(name="depidg" , initialValue = 1 , allocationSize=1)
	
	@Id
	@GeneratedValue(generator = "depidg" ,strategy = GenerationType.SEQUENCE)
	int depid;
	String name ;
	
	//mapped by : name of data member in the target class for this class 
	@OneToMany (mappedBy = "department" ) 
	// default type is lazy for many 
	// default fetch type is eager for one to one 
	Set<Employee> employees = new HashSet<>();
	
	public Department(int depid, String dept) {
		super();
		this.depid = depid;
		this.name = dept;
	}
	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getDepid() {
		return depid;
	}
	public void setDepid(int depid) {
		this.depid = depid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
	
	@Override
	public String toString() {
		return "Department [depid=" + depid + ", department=" + name + "]";
	}
	
}
