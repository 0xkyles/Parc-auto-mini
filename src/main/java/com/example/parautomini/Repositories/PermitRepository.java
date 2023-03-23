package com.example.parautomini.Repositories;

import com.example.parautomini.Entites.Permit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PermitRepository extends JpaRepository<Permit, Integer> {
    Optional<Permit> findByPermitId(int permitId);
    @Query(
            value = "SELECT * FROM permits WHERE driver_id = ?1",
            nativeQuery = true
    )
    List<Permit> findAllByDriverId(int driverId);
}
