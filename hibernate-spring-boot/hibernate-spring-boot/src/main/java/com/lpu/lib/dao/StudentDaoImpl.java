package com.lpu.lib.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lpu.lib.model.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;

@Component
public class StudentDaoImpl implements StudentDao{

	@Autowired
	EntityManagerFactory emf ;
	
	@Override
	public List<Student> listall() {
		// TODO Auto-generated method stub
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("select s from Student s");
		List<Student> ls = q.getResultList();
		return ls;
	}

	@Override
	public Student findById(int roll) {
		// TODO Auto-generated method stub
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("select s from Student s where s.roll = :roll");
		q.setParameter("roll",roll);
		Student s1 = (Student) q.getSingleResult();
		return s1;
	}

	@Override
	public boolean removeById(int roll) {
		// TODO Auto-generated method stub
//		EntityManager em = emf.createEntityManager();
//		Query q = em.createQuery("delete from Student where roll = :roll");
//		q.setParameter("roll",roll);
//		q.executeUpdate();
//		return true;
		EntityManager em = emf.createEntityManager();
		Student s = em.find(Student.class, roll);
		em.remove(s);
		return true;
	}

	@Override
	public Student update(Student student) {
		// TODO Auto-generated method stub
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(student);
		em.getTransaction().commit();
		return student;
		
		
	}

	@Override
	public Student add(Student student) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(student);
		em.getTransaction().commit();
		return student;
	}

}
