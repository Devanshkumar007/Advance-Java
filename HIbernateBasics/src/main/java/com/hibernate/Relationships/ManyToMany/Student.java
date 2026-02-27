package com.hibernate.Relationships.ManyToMany;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int sid;
	
	@Column(name= "Sname")
	String name;
	
	@ManyToMany(cascade = CascadeType.PERSIST )
	@JoinTable(
	        name = "student_course",
	        joinColumns = @JoinColumn(name = "student_id"),
	        inverseJoinColumns = @JoinColumn(name = "course_id")
	    )
	List<Courses> courses;

	public Student(String name) {
		super();
		this.name = name;
		this.courses = new ArrayList<>();;
	}

	public String getSid() {
		return "S"+sid;
	}

	public String getName() {
		return name;
	}

	public List<Courses> getCourses() {
		return courses;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCourses(List<Courses> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "Student [sid=S" + sid + ", name=" + name + ", courses=" + courses + "]";
	}
}
