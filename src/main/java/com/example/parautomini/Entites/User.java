package com.example.parautomini.Entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
}
