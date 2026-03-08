package com.capg.Service;

import com.capg.Entity.Book;
import com.capg.Repository.BookRepository;
import com.capg.Repository.UserRepository;
import com.capg.dto.BookRequest;
import com.capg.dto.BookResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepo;
    private final UserRepository userRepo;
    private final ModelMapper modelMapper;

    @PreAuthorize("hasRole('ADMIN')")
    public BookResponse addBook(BookRequest request){
        Book book = modelMapper.map(request, Book.class) ;
        Book saved = bookRepo.save(book);

        return modelMapper.map(saved, BookResponse.class);
    }


    @PreAuthorize("hasRole('ADMIN')")
    public void deleteBook(Long id){
        bookRepo.deleteById(id);
    }

    public List<BookResponse> getAllBooks(){
        return bookRepo.findAll().stream().map(book-> modelMapper.map(book, BookResponse.class)).collect(Collectors.toList());
    }


}
