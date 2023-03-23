package com.example.parautomini.Entites;

import com.example.parautomini.Enums.PermitTypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="permit_types")
public class PermitType {
    @Id
    @Column(updatable = false)
    private int permitTypeId;
    @Enumerated(EnumType.STRING)
    @Column(unique = true, updatable = false)
    private PermitTypeEnum category;
    private String description;
    @OneToMany(mappedBy = "permitType")
    private Set<VehiclePermitTypeMapping> allowedVehicleTypes;
}
