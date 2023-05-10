package com.example.parautomini.Repositories;

import com.example.parautomini.Entites.Driver;
import com.example.parautomini.Entites.Holiday;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Integer> {
    @Query(value = "SELECT h FROM Holiday h WHERE h.driver.driverId = ?1")
    List<Holiday> findAllByDriverId(int driverId);

    @Query(value="SELECT CASE WHEN COUNT(h) = 0 THEN true ELSE false END FROM Holiday h " +
            "WHERE h.driver = :driver AND h.endDate >= :startDate AND h.startDate <= :endDate")
    boolean IsDriverAvailableForTrip(@Param("driver") Driver driver, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
    @Transactional
    @Modifying
    void deleteByDriver(Driver driver);
}
