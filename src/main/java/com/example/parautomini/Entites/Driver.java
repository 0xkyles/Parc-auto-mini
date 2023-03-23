package com.example.parautomini.Entites;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="drivers")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int driverId;
    @Column(unique=true)
    private String registrationNumber;
    private String firstName;
    private String lastName;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date birthday;
    private String CIN;
    @OneToOne(optional = false)
    @JoinColumn(name="user_id", referencedColumnName = "userId")
    private User user;
    @OneToMany(
            mappedBy = "driver",
            fetch = FetchType.EAGER,
            cascade = CascadeType.REMOVE
    )
    private List<Permit> permits;
    @OneToMany(mappedBy = "driver", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    Set<Assignment> assignments;
}
