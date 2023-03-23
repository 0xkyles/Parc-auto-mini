package com.example.parautomini.Repositories;

import com.example.parautomini.Entites.PermitType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermitTypeRepository extends JpaRepository<PermitType, Integer> {

    Optional<PermitType> findByPermitTypeId(int permitTypeId);
}
