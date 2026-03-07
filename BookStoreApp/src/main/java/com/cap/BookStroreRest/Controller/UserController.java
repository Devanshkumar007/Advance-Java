package com.cap.BookStroreRest.Controller;

import com.cap.BookStroreRest.DataTransferObject.*;
import com.cap.BookStroreRest.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name= "USERS", description = "USER MANAGEMENT API'S")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/getUser/{id}")
    @Operation(
            summary = "GET USER BY ID",
            description = "Retrieve user with id"

    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "User found"),
                    @ApiResponse(responseCode = "404", description = "User not found")
            }

    )
    public ResponseEntity<UserDTO> getUserById(
            @Parameter(description = "ID of Person")
            @PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }


    @PostMapping("/addUser")
    @Operation(
            summary = "ADD NEW USER",
            description = "Used to add new user to the application"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode="201", description="User created"),
                    @ApiResponse(responseCode="400", description="Invalid user data")
            }
    )
    public ResponseEntity<CreateUserDTO> addUser(
            @Parameter(description = "USER OBJECT")
            @RequestBody @Valid CreateUserDTO user){

        CreateUserDTO createdUser = userService.createUser(user);
        return ResponseEntity.status(201).body(createdUser);
    }

    @PostMapping("/login")
    @Operation(
            summary = "LOGIN USER",
            description = "Used to login user using email and password"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Login successful"),
                    @ApiResponse(responseCode = "400", description = "Invalid credentials"),
                    @ApiResponse(responseCode = "404", description = "Invalid credentials")
            }
    )
    public ResponseEntity<LoginResponse> loginUser(
            @Parameter(description = "Login details of user")
            @RequestBody LoginRequest request){
        LoginResponse response = userService.login(request);
        if(response==null){
            return ResponseEntity.status(404).body(response);
        }
        return ResponseEntity.ok(response);
    }


    @PutMapping("/update/{id}")
    @Operation(
            summary = "UPDATE USER",
            description = "Used to update user through id"
    )
    @ApiResponses(
            value ={
                    @ApiResponse(responseCode = "200", description = "USER UPDATED"),
                    @ApiResponse(responseCode = "400", description = "USER NOT UPDATED"),
                    @ApiResponse(responseCode = "404", description = "USER NOT FOUND")
            }
    )
    public ResponseEntity<UserDTO> updateUser(
            @Parameter(description = "User id")
            @PathVariable Long id,
            @Parameter(description = "UPDATED USER OBJECT")
            @RequestBody @Valid UserDTO user){
        return ResponseEntity.ok(userService.updateUser(id,user));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "DELETE USER",
            description = "Used to delete user through id"
    )
    @ApiResponses(
            value ={
                    @ApiResponse(responseCode = "200", description = "USER DELETED"),
                    @ApiResponse(responseCode = "400", description = "USER NOT FOUND")
            }
    )
    public ResponseEntity<UserDTO> deleteUser(
            @Parameter(description = "USER ID")
            @PathVariable Long id){
        return ResponseEntity.ok(userService.deleteUserById(id));
    }

    @GetMapping("/page")
    @Operation(
            summary = "Get users with pagination and sorting",
            description = "Fetch users using user number, size, sorting field and direction"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users fetched successfully")
    })
    public ResponseEntity<PageResponse<UserDTO>> getUsers(

            @Parameter(description = "Page number (starts from 0)")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Number of records per page")
            @RequestParam(defaultValue = "5") int size,

            @Parameter(description = "Field to sort by (id, username, email)")
            @RequestParam(defaultValue = "id") String sortBy,

            @Parameter(description = "Sorting direction: asc or desc")
            @RequestParam(defaultValue = "asc") String direction
    ) {

        return ResponseEntity.ok(
                userService.getAllUsers(page, size, sortBy, direction)
        );
    }



    @PutMapping("/assignBook/{userId}")
    @Operation(
            summary = "Assign books to user",
            description = "Used to assign books to a user with id"
    )
    @ApiResponses(
            value={
                    @ApiResponse(responseCode = "200" , description = "Assigned Successfully"),
                    @ApiResponse(responseCode = "400" , description = "Failed")
            }
    )
    public ResponseEntity<UserDTO> assignBooks(
            @Parameter(description = "USER ID")
            @PathVariable Long userId,
            @Parameter(description = "LIST OF BOOK IDs")
            @RequestBody List<Long> booksid){
             return ResponseEntity.ok(userService.assignBooksToUser(userId, booksid));
    }




}
