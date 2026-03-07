package com.cap.BookStroreRest.DataTransferObject;

import com.cap.BookStroreRest.Entity.Book;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
public class CreateUserDTO {

    @NotBlank(message = "username must not be blank")
    String username;

    @NotBlank(message = "email must not be blank")
    @Email(message = "Enter valid email")
    String email ;

    @NotBlank(message="Password cant be blank")
    String password ;

}
