package com.example.parautomini.Entites;

import com.example.parautomini.Enums.VehicleTypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="vehicles_types")
public class VehicleType {
    @Id
    @Column(updatable = false)
    private int vehicleTypeId;

    @Enumerated(EnumType.STRING)
    @Column(unique=true, updatable = false)
    private VehicleTypeEnum label;
    @ManyToOne
    @JoinColumn(
                name="vehicle_category_id",
                updatable = false,
                referencedColumnName = "vehicleCategoryId"
    )
    private VehicleCategory vehicleCategoryId;
    private String description;
    @OneToMany(mappedBy="vehicleType")
    private Set<VehiclePermitTypeMapping> requiredPermitTypes;
}
