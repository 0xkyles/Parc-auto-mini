package com.example.parautomini.Entites;

import com.example.parautomini.Keys.VehicleLicenseMappingKey;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="vehicle_license_type_mapping")
public class VehiclePermitTypeMapping {
    @EmbeddedId
    private VehicleLicenseMappingKey id;

    @ManyToOne
    @MapsId("vehicleTypeId")
    @JoinColumn(name="vehicle_type_id")
    private VehicleType vehicleType;

    @ManyToOne
    @MapsId("licenseTypeId")
    @JoinColumn(name="license_type_id")
    private LicenseType licenseType;
}

