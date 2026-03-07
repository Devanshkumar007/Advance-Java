package com.cap.BookStroreRest.Repository;

import com.cap.BookStroreRest.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT u FROM User u WHERE u.email = :param")
    public Optional<User> findUserByEmail(@Param("param") String email);
}
