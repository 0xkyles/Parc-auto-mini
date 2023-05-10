package com.example.parautomini.Entites;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="trips")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tripId;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date startDate;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date endDate;
    @Column(columnDefinition = "boolean default false")
    private boolean isAssigned;
    @ManyToOne
    @JoinColumn(name="vehicle_type_id", referencedColumnName = "vehicleTypeId")
    private VehicleType vehicleType;
    @OneToMany(mappedBy = "trip", cascade = CascadeType.REMOVE)
    Set<Assignment> assignments;
}
