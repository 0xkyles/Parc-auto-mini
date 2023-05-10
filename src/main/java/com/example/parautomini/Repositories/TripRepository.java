package com.example.parautomini.Repositories;

import com.example.parautomini.Entites.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {
    @Query(value="SELECT t FROM Trip t WHERE t.isAssigned = :value")
    List<Trip> findAllByIsAssigned(@Param("value") boolean value);
    @Query(value="SELECT t FROM Trip t WHERE t.startDate < CURRENT_DATE()")
    List<Trip> findAllExpired();
}
