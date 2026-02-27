package com.hibernate.Relationships.ManyToOne;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Department {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	int deptid;
	
	String name ;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "dept")
	List<Employee> emps;
	
	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Department(String name) {
		super();
		this.name = name;
		emps = new ArrayList<>();
	}


	public int getDeptid() {
		return deptid;
	}

	public List<Employee> getEmps() {
		return emps;
	}


	public void setEmps(List<Employee> emps) {
		this.emps = emps;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Department [deptid=" + deptid + ", name=" + name + "]";
	}
	
	
	
	
}
