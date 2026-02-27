package com.hibernate.Relationships.ManyToMany;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Courses {

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="course")
	@SequenceGenerator(name="course", initialValue =100, allocationSize=1)
	int cid;
	
	@Column(name= "Cname")
	String name;
	String domain;
	
	@ManyToMany
	List<Student> students;

	public Courses() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Courses(String name, String domain) {
		super();
		this.name = name;
		this.domain = domain;
		students = new ArrayList<>();
	}

	public String getCid() {
		return "C"+cid;
	}

	public String getName() {
		return name;
	}

	public String getDomain() {
		return domain;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Courses [cid=C" + cid + ", name=" + name + ", domain=" + domain + ", students=" + students + "]";
	}	
}
