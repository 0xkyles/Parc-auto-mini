package com.example.parautomini.Keys;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class AssigmentKey implements Serializable {
    private int tripId;
    private int driverId;
    private String v_registrationNumber;
}
