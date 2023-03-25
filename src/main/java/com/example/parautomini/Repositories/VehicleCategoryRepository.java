package com.example.parautomini.Repositories;

import com.example.parautomini.Entites.VehicleCategory;
import com.example.parautomini.Entites.VehicleType;
import com.example.parautomini.Enums.VehicleCategoryEnum;
import com.example.parautomini.Enums.VehicleTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface VehicleCategoryRepository extends JpaRepository<VehicleCategory, Integer> {
    @Query(
            value = "SELECT * FROM vehicle_categories WHERE label = ?1",
            nativeQuery = true
    )
    Optional<VehicleCategory> findByLabel(String label);
}
