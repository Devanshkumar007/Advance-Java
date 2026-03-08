package com.capg.Entity;

import com.capg.Enums.Role;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password ;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

}
