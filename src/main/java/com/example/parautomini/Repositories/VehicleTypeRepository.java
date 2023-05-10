package com.example.parautomini.Repositories;

import com.example.parautomini.Entites.VehicleCategory;
import com.example.parautomini.Entites.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleTypeRepository extends JpaRepository<VehicleType, Integer> {
    @Query(
        value = "SELECT * FROM vehicles_types WHERE label = ?1",
        nativeQuery = true
    )
    Optional<VehicleType> findByLabel(String label);
    List<VehicleType> findAllByVehicleCategory(VehicleCategory vehicleCategory);
}
