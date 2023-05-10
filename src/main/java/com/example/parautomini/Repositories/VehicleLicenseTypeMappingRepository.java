package com.example.parautomini.Repositories;

import com.example.parautomini.Entites.Driver;
import com.example.parautomini.Entites.LicenseType;
import com.example.parautomini.Entites.VehiclePermitTypeMapping;
import com.example.parautomini.Entites.VehicleType;
import com.example.parautomini.Keys.VehicleLicenseMappingKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleLicenseTypeMappingRepository extends JpaRepository<VehiclePermitTypeMapping, VehicleLicenseMappingKey> {
    @Query("SELECT m.licenseType FROM VehiclePermitTypeMapping m WHERE m.vehicleType = :vehicleType")
    List<LicenseType> findLicenseTypesByVehicleType(@Param("vehicleType") VehicleType vehicleType);

    @Query("SELECT DISTINCT v.vehicleType FROM VehiclePermitTypeMapping v WHERE v.licenseType IN (SELECT DISTINCT l.licenseType FROM License l WHERE l.driver = :driver)")
    List<VehicleType> findAllowedToDriveVehicleTypesByDriver(@Param("driver") Driver driver);
}
