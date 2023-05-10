package com.example.parautomini.Repositories;

import com.example.parautomini.Entites.LicenseType;
import com.example.parautomini.Enums.LicenseTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LicenseTypeRepository extends JpaRepository<LicenseType, Integer> {
    Optional<LicenseType> findByLicenseTypeId(int licenseTypeId);
}
