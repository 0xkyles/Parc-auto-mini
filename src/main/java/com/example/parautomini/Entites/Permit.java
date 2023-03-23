package com.example.parautomini.Entites;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="permits")
public class Permit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int permitId;
    @ManyToOne
    @JoinColumn(
            name="permit_type_id",
            referencedColumnName = "permitTypeId"
    )
    private PermitType permitType;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date permitDeliveryDate;
    @ManyToOne
    @JoinColumn(
            name="driver_id",
            referencedColumnName = "driverId"
    )
    private Driver driver;
}
