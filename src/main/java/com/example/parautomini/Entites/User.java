package com.example.parautomini.Entites;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String email;
    private String password;
    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Driver driver;
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    public enum RoleEnum {
        ADMIN, DRIVER, TRIP_MANAGER
    }
}
