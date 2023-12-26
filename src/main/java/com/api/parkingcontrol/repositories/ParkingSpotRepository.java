package com.api.parkingcontrol.repositories;

import com.api.parkingcontrol.models.ParkingSpot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot,Long> {


    boolean existsByParkingSpotNumber(String parkingSpotNumber);
    boolean existsByLicensePlateCar(String licensePlateCar);
    boolean existsByApartmentAndBlock(String apartment,String block);


    Page<ParkingSpot> findByResponsibleNameContainingIgnoreCase(String responsibleName, Pageable pageable);

    Page<ParkingSpot> findByBrandCarContainingIgnoreCase(String brandCar, Pageable pageable);

}
