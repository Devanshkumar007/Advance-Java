package com.lpu.lib.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lpu.lib.model.Book;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;


@Component
public class BookDaoImpl implements BookDao{

	@Autowired
	EntityManagerFactory factory;
	
		
	@Override
	public List<Book> findall() {
		// TODO Auto-generated method stub
		EntityManager em =factory.createEntityManager();
		Query q = em.createQuery("select b from Book b");
		List<Book> ls = q.getResultList();
		return ls;
	}

	@Override
	public Book findBYId(int bid) {
		// TODO Auto-generated method stub
		EntityManager em = factory.createEntityManager();
		Book book = em.find(Book.class, bid);
		return book;
	}

	@Override
	public Book add(Book book) {
		// TODO Auto-generated method stub
		EntityManager em = factory.createEntityManager();
		em.persist(book);
		return null;
	}

	@Override
	public boolean remove(Book book) {
		// TODO Auto-generated method stub
		EntityManager em = factory.createEntityManager();
		Book b = (Book) em.find(Book.class, book.bid);
		em.remove(book); // error : if book is not persistent object it will give error 
		return true;
	}

	@Override
	public Book update(Book book) {
		// TODO Auto-generated method stub
		EntityManager em = factory.createEntityManager();
		em.persist(book);
		return book;
	}

	@Override
	public List<Book> findByAuthor(String author) {
		// TODO Auto-generated method stub
		EntityManager em =factory.createEntityManager();
		Query q = em.createQuery("select b from Book b where b.author = :author");
		q.setParameter("author", author);
		List<Book> ls = q.getResultList();
		return ls;
	}
	
}
