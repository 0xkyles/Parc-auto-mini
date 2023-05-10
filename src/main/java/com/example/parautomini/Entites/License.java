package com.example.parautomini.Entites;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="licenses")
public class License {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int licenseId;
    @ManyToOne
    @JoinColumn(
            name="license_type_id",
            referencedColumnName = "licenseTypeId"
    )
    private LicenseType licenseType;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date deliveryDate;
    @ManyToOne
    @JoinColumn(
            name="driver_id",
            referencedColumnName = "driverId"
    )
    private Driver driver;
}
