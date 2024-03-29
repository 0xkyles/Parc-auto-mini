package com.example.parautomini.Repositories;

import com.example.parautomini.Entites.Vehicle;
import com.example.parautomini.Entites.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    List<Vehicle> findAllByVehicleType(VehicleType vehicleType);
}
