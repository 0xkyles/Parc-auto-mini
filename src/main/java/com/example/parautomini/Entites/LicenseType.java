package com.example.parautomini.Entites;

import com.example.parautomini.Enums.LicenseTypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="license_types")
public class LicenseType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int licenseTypeId;
    @Enumerated(EnumType.STRING)
    @Column(unique = true, updatable = false)
    private LicenseTypeEnum category;
    private String description;
    @OneToMany(mappedBy = "licenseType")
    private Set<VehiclePermitTypeMapping> allowedVehicleTypes;
}
