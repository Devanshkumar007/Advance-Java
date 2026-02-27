package com.lpu.lib.dao;

import java.util.List;

import com.lpu.lib.model.Book;

public interface BookDao {
	List<Book> findall();
	Book findBYId(int bid);
	Book add(Book book);
	boolean remove(Book book);
	Book update(Book book);
	List<Book> findByAuthor(String author);
}
