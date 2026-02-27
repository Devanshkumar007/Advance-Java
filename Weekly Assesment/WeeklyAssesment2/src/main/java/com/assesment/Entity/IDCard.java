package com.assesment.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class IDCard {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "card_seq")
    @SequenceGenerator(name = "card_seq", initialValue = 101, allocationSize = 1)
    private int id;

    private String cardNumber;

    @OneToOne(mappedBy = "idCard")
    private Student student;

	public IDCard(String cardNumber) {
		super();
		this.cardNumber = cardNumber;
	}

	public IDCard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public Student getStudent() {
		return student;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
	    return "IDCard [id=" + id +
	           ", cardNumber=" + cardNumber +
	           ", student=" + (student != null ? student.getId() : "null") + "]";
	}
		
}
