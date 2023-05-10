package com.example.parautomini.Entites;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Objects;
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
    @Column(name="cin")
    private String cin;
    @OneToOne(optional = false)
    @JoinColumn(name="user_id", referencedColumnName = "userId")
    private User user;
    @OneToMany(
            mappedBy = "driver",
            cascade = CascadeType.REMOVE
    )
    private Set<License> licenses;
    @OneToMany(mappedBy = "driver", cascade = CascadeType.REMOVE)
    private Set<Assignment> assignments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return driverId == driver.driverId;
    }
}
