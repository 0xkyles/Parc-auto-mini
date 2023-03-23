package com.example.parautomini.Entites;

import com.example.parautomini.Keys.AssigmentKey;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="assignments")
public class Assignment {
    @EmbeddedId
    private AssigmentKey assigmentId;

    @ManyToOne
    @MapsId("tripId")
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @ManyToOne
    @MapsId("driverId")
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @ManyToOne
    @MapsId("v_registrationNumber")
    @JoinColumn(name="v_registration_number")
    private Vehicle vehicle;
}

