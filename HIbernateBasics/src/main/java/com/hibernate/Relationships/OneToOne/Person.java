package com.hibernate.Relationships.OneToOne;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator= "person_seq")
	@SequenceGenerator(name="person_seq", initialValue= 10, allocationSize=1)
	private int id;
	private String name;
	
	@OneToOne(cascade =CascadeType.ALL)
	@JoinColumn(name="passport_id") // Foreign Key Column Name in table 
	Passport passport;

	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Person(String name, Passport passport) {
		super();
		this.name = name;
		this.passport = passport;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Passport getPassport() {
		return passport;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassport(Passport passport) {
		this.passport = passport;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", passport=" + passport + "]";
	}
	
	
	
	
	
}
