package com.travel_app.travel.repository;

import com.travel_app.travel.entity.Location;
import com.travel_app.travel.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query(value = "SELECT * FROM railway.vehicle WHERE starting_location = :startingLocation and destination = :destination ;", nativeQuery = true)
    List<Vehicle> findVehicleByLocation(@Param("startingLocation") String startingLocation, @Param("destination") String destination);

    @Query(value = "SELECT * FROM railway.vehicle ORDER BY RAND() LIMIT 6", nativeQuery = true)
    List<Vehicle> findRandomVehicles();

    @Query("SELECT v FROM Vehicle v " +
            "WHERE (:brand IS NULL OR v.brand LIKE CONCAT('%', :brand, '%')) " +
            "AND (:destination IS NULL OR v.destination LIKE CONCAT('%', :destination, '%')) " +
            "AND (:priceMin IS NULL OR v.price >= :priceMin) " +
            "AND (:priceMax IS NULL OR v.price <= :priceMax) " +
            "AND (:startingLocation IS NULL OR v.startingLocation LIKE CONCAT('%', :startingLocation, '%')) " +
            "AND (:vehicleTypes IS NULL OR v.vehicleType IN :vehicleTypes) " +
            "AND (:movingDate IS NULL OR v.movingDate = :movingDate)")
    List<Vehicle> searchVehicles(
            @Param("brand") String brand,
            @Param("destination") String destination,
            @Param("priceMin") BigDecimal priceMin,
            @Param("priceMax") BigDecimal priceMax,
            @Param("startingLocation") String startingLocation,
            @Param("vehicleTypes") List<String> vehicleTypes,
            @Param("movingDate") String movingDate
    );

}
