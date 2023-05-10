package com.example.parautomini.Repositories;

import com.example.parautomini.Entites.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {
    List<Assignment> findAllByDriver(Driver driver);
    List<Assignment> findAllByVehicle(Vehicle vehicle);
    @Query(value = "SELECT a FROM Assignment a JOIN a.vehicle v JOIN v.vehicleType vt WHERE vt = :vehicleType")
    List<Assignment> findAllByVehicleType(@Param("vehicleType") VehicleType vehicleType);

    @Query(value="SELECT CASE WHEN COUNT(a) = 0 THEN true ELSE false END FROM Assignment a JOIN a.trip t " +
            "WHERE a.vehicle = :vehicle AND t.endDate >= :startDate AND t.startDate <= :endDate")
    boolean IsVehicleAvailableForTrip(@Param("vehicle") Vehicle vehicle, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(value="SELECT CASE WHEN COUNT(a) = 0 THEN true ELSE false END FROM Assignment a JOIN a.trip t " +
            "WHERE a.driver = :driver AND t.endDate >= :startDate AND t.startDate <= :endDate")
    boolean IsDriverAvailableForTrip(@Param("driver") Driver driver, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
    List<Assignment> findAllByStatus(Assignment.AssignmentStatus status);
    Optional<Assignment> findByTrip(Trip trip);
}
