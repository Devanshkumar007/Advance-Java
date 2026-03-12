package com.capg.controller;

import com.capg.entity.Book;
import com.capg.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/LibraryBookCatalog")
@Tag(name = "Book", description = "Library Book Catalog")
public class BookController {

    @Autowired
    private BookService bookService ;


    @PostMapping("/books")
    @Operation(
            summary = "Add a new Book",
            description = "Create a new book in the system"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode ="201", description = "Book Created Successfully"),
            @ApiResponse(responseCode ="409", description = "Duplicate Id")
    })
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookService.addBook(book));
    }

    @GetMapping("/books")
    @Operation(
            summary = "Get all Books",
            description = "FETCHES ALL THE BOOKS IN SYSTEM"
    )
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "BOOKS FETCHED SUCCESSFULLY")
    })
    public ResponseEntity<List<Book>> getAllBooks(){
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/books/{id}")
    @Operation(
            summary = "Fetch book by ID",
            description = "Retrieves a book from the catalog using its ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @GetMapping("/books/search/{author}")
    @Operation(
            summary = "Fetch Books by Author",
            description = "Retrieves all the books from catalog having same author"
    )
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Books retrieved successfully")
    }
    )
    public ResponseEntity<List<Book>> getBooksByAuthor(@PathVariable String author){
        return ResponseEntity.ok(bookService.getBookByAuthor(author));
    }


    @PutMapping("/books/{id}")
    @Operation(
            summary = "Update Book",
            description = "Retrieves book by id and updates them"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "UPDATED SUCCESSFULLY"),
            @ApiResponse(responseCode = "404", description = "BOOK NOT FOUND")
    })
    public ResponseEntity<Book> updateBook(@PathVariable Long id,
                                            @RequestBody Book book){
        return ResponseEntity.ok(bookService.updateBook(id, book));
    }

    @PatchMapping("/books/{id}/borrow")
    @Operation(
            summary = "Borrow the book",
            description = "Check the status of the book and allot it to the customer"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "BOOK Borrowed"),
            @ApiResponse(responseCode = "409", description = "Book Already Borrowed")
    })
    public ResponseEntity<Book> borrowBook(@PathVariable Long id){
        return ResponseEntity.ok(bookService.borrowBook(id));
    }


    @PatchMapping("/books/{id}/return")
    @Operation(
            summary = "Return the book",
            description = "Check the status of the book and mark it as available for customer"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book Returned"),
            @ApiResponse(responseCode = "409", description = "Book Already Returned")
    })
    public ResponseEntity<Book> returnBook(@PathVariable Long id){
        return ResponseEntity.ok(bookService.returnBook(id));
    }


    @DeleteMapping("/books/{id}")
    @Operation(
            summary = "Delete a book",
            description = "Retrieves and delete a book from the system"
    )
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Book Deleted"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    public ResponseEntity<Book> deleteBook(@PathVariable Long id){
        return ResponseEntity.ok(bookService.deleteBook(id));
    }


}
