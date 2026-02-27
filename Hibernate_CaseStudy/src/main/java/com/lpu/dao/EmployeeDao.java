package com.lpu.dao;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.lpu.Entity.Employee;

public class EmployeeDao {
	private static SessionFactory sf ;
	private static Scanner sc;
	static {
		sf = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
		sc = new Scanner(System.in);
	}
	
	public void saveEmployee(Employee emp) {
		Session session =sf.openSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.persist(emp);
			tx.commit();
			System.out.println("Employee Added "+emp);
		}
		catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}
	}
	
	public void getEmployee(int id) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		try {
			Employee e =session.get(Employee.class,id);
			if(e==null) throw new RuntimeException("Employee Not found");
			else System.out.println(e);
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void updateSalary(int id, double newSalary) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		try {
			Employee e = session.get(Employee.class, id);
			if(e==null) throw new RuntimeException("EMPLOYEE NOT FOUND");
			e.setSalary(newSalary);
			session.merge(e);
			tx.commit();
			System.out.println("Employee Updated "+e);
		}catch(Exception e) {
			tx.rollback();
			System.out.println(e.getMessage());
		}
	}
	
	
	public void deleteEmployee(int id) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		try {
			Employee e = session.get(Employee.class, id);
			if(e==null) throw new RuntimeException("EMPLOYEE NOT IN DB");
			session.remove(e);
			System.out.println("Employee deleted "+e);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
