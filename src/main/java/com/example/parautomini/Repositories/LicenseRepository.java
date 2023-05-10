package com.example.parautomini.Repositories;

import com.example.parautomini.Entites.Driver;
import com.example.parautomini.Entites.License;
import com.example.parautomini.Entites.LicenseType;
import com.example.parautomini.Entites.VehicleType;
import com.example.parautomini.Enums.LicenseTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LicenseRepository extends JpaRepository<License, Integer> {
    Optional<License> findByLicenseId(int licenseId);
    @Query("SELECT DISTINCT l.driver FROM License l JOIN l.licenseType lt WHERE lt.category = :licenseCategory")
    List<Driver> findDriversByLicenseType(@Param("licenseCategory") LicenseTypeEnum licenseCategory);

    @Query("SELECT DISTINCT l.driver FROM License l WHERE l.licenseType IN (SELECT v.licenseType FROM VehiclePermitTypeMapping v WHERE v.vehicleType = :vehicleType)")
    List<Driver> findAllowedDriversByVehicleType(@Param("vehicleType") VehicleType vehicleType);

    @Query("SELECT DISTINCT l.licenseType FROM License l WHERE l.driver = :driver")
    List<LicenseType> findDistinctLicenseTypesByDriver(@Param("driver") Driver driver);

    @Query(
            value = "SELECT l FROM License l WHERE l.driver = :driver"
    )
    List<License> findAllByDriverId(@Param("driver") Driver driver);
    @Query(
            value = "SELECT l FROM License l WHERE l.licenseType.licenseTypeId = :licenseTypeId"
    )
    List<License> findAllByLicenseTypeId(@Param("licenseTypeId") int licenseTypeId);

    @Query(value="SELECT l FROM License l WHERE l.licenseType.licenseTypeId = :licenseTypeId AND l.driver.driverId = :driverId")
    List<License> findAllByDriverAndType(@Param("driverId") int driverId, @Param("licenseTypeId") int licenseTypeId);
}
