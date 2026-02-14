package com.assesment.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="menu_item")
public class MenuItems {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY, generator ="id_gen")
	@SequenceGenerator(name = "id_gen" , initialValue=1, allocationSize = 1 )
	int id;
	
	@Column(name= "product_name")
	String name;
	double price ;
	String category;
	boolean avaliable;
	public MenuItems() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MenuItems(String name, double price, String category, boolean avaliable) {
		super();
		this.name = name;
		this.price = price;
		this.category = category;
		this.avaliable = avaliable;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public double getPrice() {
		return price;
	}
	public String getCategory() {
		return category;
	}
	public boolean isAvaliable() {
		return avaliable;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setAvaliable(boolean avaliable) {
		this.avaliable = avaliable;
	}
	@Override
	public String toString() {
		return "MenuItem [id=" + id + ", name=" + name + ", price=" + price + ", category=" + category + ", avaliable="
				+ avaliable + "]";
	}
	
}
