package com.cap.BookStroreRest.Service;

import com.cap.BookStroreRest.DataTransferObject.*;
import com.cap.BookStroreRest.Entity.Book;
import com.cap.BookStroreRest.Entity.User;
import com.cap.BookStroreRest.Repository.BookRepository;
import com.cap.BookStroreRest.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepo;
    private final ModelMapper modelMapper;
    private final BookRepository bookRepo;

    @CachePut(value = "users")
    public CreateUserDTO createUser(CreateUserDTO user){
        User dto = modelMapper.map(user, User.class);
        User save = userRepo.save(dto);
        return modelMapper.map(save, CreateUserDTO.class);
    }

    public UserDTO getUserById(Long id){
        User user = userRepo.findById(id).orElseThrow(()-> new RuntimeException("USER NOT FOUND"));
        return modelMapper.map(user,UserDTO.class);
    }

    @CacheEvict( value="users" )
    public UserDTO deleteUserById(Long id){
        User user = userRepo.findById(id).orElseThrow(()-> new RuntimeException("USER NOT FOUND"));
        userRepo.delete(user);
        return modelMapper.map(user,UserDTO.class);
    }


    @Cacheable(value = "users")
    public PageResponse<UserDTO> getAllUsers(int page , int size, String sortBy, String direction) {
        // Guardrails for pagination: never allow negative page and keep size in a safe range.
        int safePage = Math.max(page, 0);
        int safeSize = size <= 0 ? 5 : Math.min(size, 100);

        // Allow sorting only on known User fields to avoid runtime query errors.
        Set<String> allowedSortFields = Set.of("id", "username", "email");
        String requestedSortBy = sortBy == null ? "" : sortBy.trim();
        String safeSortBy = allowedSortFields.contains(requestedSortBy) ? requestedSortBy : "id";

        // Accept only "desc" explicitly; everything else falls back to ascending.
        boolean isDesc = direction != null && direction.trim().equalsIgnoreCase("desc");
        Sort sort = isDesc ? Sort.by(safeSortBy).descending() : Sort.by(safeSortBy).ascending();

        Pageable pageable = PageRequest.of(safePage, safeSize, sort);

        Page<User> userPage = userRepo.findAll(pageable);

        List<UserDTO> dtoList = userPage.getContent()
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .toList();

        return new PageResponse<>(
                dtoList,
                userPage.getNumber(),
                userPage.getSize(),
                userPage.getTotalElements(),
                userPage.getTotalPages()

        );
    }

    @CachePut(value = "users")
    public UserDTO updateUser(Long id, UserDTO userDto){
        User user = userRepo.findById(id).orElseThrow(()-> new RuntimeException("USER NOT FOUND"));

        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());

        userRepo.save(user);
        return  modelMapper.map(user, UserDTO.class);
    }



    public LoginResponse login(LoginRequest request){
        String email = request.getEmail();
        String password = request.getPassword();
        User user = userRepo.findUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("USER DOES NOT EXIST WITH EMAIL :" + email));
        if(user.getPassword().equals(password)){
            return modelMapper.map(user, LoginResponse.class);
        }
        return null;
    }



    public UserDTO assignBooksToUser(Long id, List<Long> booksid){
        User user = userRepo.findById(id).orElseThrow(()-> new RuntimeException("NO PERSON FOUND"));

        for(long bid: booksid){
            try {
                Book book = bookRepo.findById(bid).orElseThrow(
                        () -> new RuntimeException("NO BOOK FOUND WITH ID : "+bid));
                if(book.getUser()==null){
                    user.addBook(book);
                    userRepo.save(user);
                }else throw new RuntimeException("BOOK ALREADY ALLOTED TO SOMEONE ");

            }catch(RuntimeException e){
                System.out.println(e.getMessage());
            }

        }
        return modelMapper.map(user, UserDTO.class);
    }










}
