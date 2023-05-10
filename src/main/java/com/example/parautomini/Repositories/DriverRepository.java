package com.example.parautomini.Repositories;

import com.example.parautomini.Entites.Driver;
import com.example.parautomini.Entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {
    Optional<Driver> findByDriverId(int driverId);

    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN true ELSE false END FROM Driver d WHERE d.user = :user")
    boolean existsByUser(@Param("user") User user);
}
