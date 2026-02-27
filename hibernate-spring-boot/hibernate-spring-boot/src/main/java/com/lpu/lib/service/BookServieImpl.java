package com.lpu.lib.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lpu.lib.dao.BookDao;
import com.lpu.lib.model.Book;

@Component
public class BookServieImpl implements BookService{

	@Autowired
	BookDao bookDao;
	
	@Override
	public Book findById(int id) {
		return bookDao.findBYId(id);
	}

	@Override
	public List<Book> findByAuthor(String author) {
		// TODO Auto-generated method stub
		return bookDao.findByAuthor(author);
	}

}
