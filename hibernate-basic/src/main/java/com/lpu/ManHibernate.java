package com.lpu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ManHibernate {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("lpu25");
		EntityManager em = emf.createEntityManager();
//		Student student 
		
	}
}
