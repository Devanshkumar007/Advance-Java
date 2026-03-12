package com.capg.service;

import com.capg.entity.Book;

import java.util.List;

public interface BookService {
    public Book addBook(Book book);
    public List<Book> getAllBooks();
    public Book getBookById(Long id);
    public List<Book> getBookByAuthor(String author);
    public Book updateBook(Long id ,Book book);
    public Book borrowBook(Long id);
    public Book returnBook(Long id);
    public Book deleteBook(Long id);
}
