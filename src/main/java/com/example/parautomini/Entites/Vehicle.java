package com.example.parautomini.Entites;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="vehicles")
public class Vehicle {
    @Id
    private String v_registrationNumber;
    private String name;
    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="vehicle_type_id", referencedColumnName = "vehicleTypeId")
    private VehicleType vehicleType;

    @OneToMany(mappedBy = "vehicle")
    private Set<Assignment> assignments;
}
