package com.lpu.lib.service;

import java.util.List;

import com.lpu.lib.model.Book;

public interface BookService {
	Book findById(int id);
	List<Book> findByAuthor(String author);
}
