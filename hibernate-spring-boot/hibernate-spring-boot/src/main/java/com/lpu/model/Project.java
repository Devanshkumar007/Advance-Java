package com.lpu.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int projid; 
	private String client;
	@Column(name ="cost", nullable = false)
	private double cost ;
	private Date startDate;
	private Date endDate;
	
	
	
	
	@Override
	public String toString() {
		return "Project [projid=" + projid + ", client=" + client + ", cost=" + cost + ", startDate=" + startDate
				+ ", endDate=" + endDate + "]";
	}
	public int getProjid() {
		return projid;
	}
	public void setProjid(int projid) {
		this.projid = projid;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Project(int projid, String client, double cost, Date startDate, Date endDate) {
		super();
		this.projid = projid;
		this.client = client;
		this.cost = cost;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public Project() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
