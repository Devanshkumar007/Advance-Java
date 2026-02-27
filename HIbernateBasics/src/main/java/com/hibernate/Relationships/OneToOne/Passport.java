package com.hibernate.Relationships.OneToOne;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Passport {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator= "passport_seq")
	@SequenceGenerator(name= "passport_seq", initialValue =1 , allocationSize = 1)
	private int passportId;
	private String passportNumber;
	private String country ;
	
	@OneToOne(mappedBy = "passport")
	private Person person;
	
	public Passport() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Passport(String passportNumber, String country, Person p) {
		super();
		this.passportNumber = passportNumber;
		this.country = country;
		this.person=p;
	}
	public int getPassportId() {
		return passportId;
	}
	public String getPassportNumber() {
		return passportNumber;
	}
	public String getCountry() {
		return country;
	}
	public void setPassportId(int passportId) {
		this.passportId = passportId;
	}
	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Override
	public String toString() {
		return "Passport [passportId=" + passportId + ", passportNumber=" + passportNumber + ", country=" + country
				+ "]";
	}
	
	
	
}
