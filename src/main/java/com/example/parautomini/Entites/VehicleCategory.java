package com.example.parautomini.Entites;

import com.example.parautomini.Enums.VehicleCategoryEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="vehicle_categories")
public class VehicleCategory {
    @Id
    @Column(updatable = false)
    private int vehicleCategoryId;
    @Enumerated(EnumType.STRING)
    @Column(unique = true, updatable = false)
    private VehicleCategoryEnum label;
    private String description;
}
