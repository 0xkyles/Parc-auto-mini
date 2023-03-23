package com.example.parautomini.Keys;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class VehiclePermitMappingKey implements Serializable {
    private int vehicleTypeId;
    private int permitTypeId;
}
