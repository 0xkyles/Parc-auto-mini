package com.example.parautomini.Entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name="assignments")
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int assigmentId;

    @ManyToOne
    @JoinColumn(name = "trip_id", unique = true)
    private Trip trip;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @ManyToOne
    @JoinColumn(name="v_registration_number")
    private Vehicle vehicle;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date affectationDate;
    @Enumerated(EnumType.STRING)
    private AssignmentStatus status;

    public enum AssignmentStatus {
        TERMINATED, SCHEDULED, ONGOING
    }
}

