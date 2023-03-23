package com.example.parautomini.Entites;

import com.example.parautomini.Keys.VehiclePermitMappingKey;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="vehicle_permit_type_mapping")
public class VehiclePermitTypeMapping {
    @EmbeddedId
    private VehiclePermitMappingKey id;

    @ManyToOne
    @MapsId("vehicleTypeId")
    @JoinColumn(name="vehicle_type_id")
    private VehicleType vehicleType;

    @ManyToOne
    @MapsId("permitTypeId")
    @JoinColumn(name="permit_type_id")
    private PermitType permitType;
}

