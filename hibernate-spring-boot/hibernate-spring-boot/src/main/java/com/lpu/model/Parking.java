package com.lpu.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Parking {

	@SequenceGenerator(name = "parkingseq" , initialValue = 1101 , allocationSize = 1)

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE , generator = "parkingseq")
	private int parkingid ;
	
	@Column(name="floor" , nullable=false)
	private int floor;
	
	private int place;
	
	// if we want to add some foreign key in this table let say studentid 
	// for hibernate we have objects for every row so we add a object od class student into it 
	
	//@OneToOne
	//Employee employee; // this will automatically assign empid(primary key of emplyee table) as foreign key in parking table ;
	//with name employee_empid 
	// to change the name of the column we can add 
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "empid")
	Employee employee;
	// default type is lazy for many 
	// default fetch type is eager for one to one 
	
	// if you want to add
	
	

	public int getParkingid() {
		return parkingid;
	}

	public void setParkingid(int parkingid) {
		this.parkingid = parkingid;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public int getPlace() {
		return place;
	}

	public Parking() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Parking(int parkingid, int floor, int place) {
		super();
		this.parkingid = parkingid;
		this.floor = floor;
		this.place = place;
	}

	@Override
	public String toString() {
		return "Parking [parkingid=" + parkingid + ", floor=" + floor + ", place=" + place + "]";
	}

	public void setPlace(int place) {
		this.place = place;
	}
	
	
	
	
}
