package com.hibernate.Relationships.OneToMany;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Orders {
	
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	private int id;
	private String name;
	private Date orderDate;
	
	@ManyToOne
	private Customer customer;
	
	
	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Orders(String name, Date orderDate) {
		super();
		this.orderDate = orderDate;
		this.name=name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public Date getOrderDate() {
		return orderDate;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Orders [id=" + id + ", name=" + name + ", orderDate=" + orderDate + " customer="+customer.getId()+" "+customer.getName()+"]";
	}
}
