package com.cap.BookStroreRest.DataTransferObject;

import com.cap.BookStroreRest.Entity.Book;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {

    private Long id;

    @Size(min = 3, max = 20, message = "username must be between 3 and 20 characters")
    private String username;

    @NotBlank(message = "email must not be blank")
    @Email(message = "Enter valid email")
    private String email;

    private List<BookDto> books;
}
