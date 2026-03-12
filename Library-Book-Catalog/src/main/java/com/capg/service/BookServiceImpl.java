package com.capg.service;

import com.capg.entity.Book;
import com.capg.exceptions.BookBorrowing;
import com.capg.exceptions.BookNotFound;
import com.capg.exceptions.DuplicateIdException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookServiceImpl implements BookService{

    private final Map<Long,Book> mp = new HashMap<>();

    @Override
    public Book addBook(Book book) {
        if(mp.containsKey(book.getId())) throw new DuplicateIdException("Book with id:"+ book.getId() +" already present") ;
        mp.put(book.getId(),book);
        return book;
    }

    @Override
    public List<Book> getAllBooks() {
        return new ArrayList<>(mp.values());
    }

    @Override
    public Book getBookById(Long id) {
        if(!mp.containsKey(id)) throw new BookNotFound("Book with id:"+id+" not present");
        return mp.get(id);
    }


    @Override
    public List<Book> getBookByAuthor(String author) {
        return mp.values()
                .stream()
                .filter(a-> a.getAuthor().equalsIgnoreCase(author))
                .toList();
    }

    @Override
    public Book updateBook(Long id, Book book) {
        if(!mp.containsKey(id)) throw new BookNotFound("Book with id:"+id+" not present");
        mp.put(id,book) ;
        return book ;
    }

    @Override
    public Book borrowBook(Long id) {
        if(!mp.containsKey(id) )throw new BookNotFound("Book with id:"+id+" not present");
        Book book = mp.get(id);
        if(!book.isAvailable()){
            throw new BookBorrowing("BOOK ALREADY BORROWED");
        }
        book.setAvailable(false);
        return book;
    }

    @Override
    public Book returnBook(Long id) {
        if(!mp.containsKey(id) )throw new BookNotFound("Book with id:"+id+" not present");
        Book book = mp.get(id);
        if(book.isAvailable()) throw new BookBorrowing("BOOK ALREADY RETURNED");
        book.setAvailable(true);
        return book ;
    }

    @Override
    public Book deleteBook(Long id) {
        Book book = getBookById(id);
        mp.remove(id);
        return book;
    }
}
