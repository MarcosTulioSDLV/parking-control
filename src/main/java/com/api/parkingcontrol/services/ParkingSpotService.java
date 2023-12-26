package com.api.parkingcontrol.services;

import com.api.parkingcontrol.ParkingControlApplication;
import com.api.parkingcontrol.models.ParkingSpot;
import com.api.parkingcontrol.repositories.ParkingSpotRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ParkingSpotService {

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    private static final Logger LOG= LoggerFactory.getLogger(ParkingControlApplication.class);

    public Page<ParkingSpot> getParkingSpots(Pageable pageable) {
        return parkingSpotRepository.findAll(pageable);
    }

    public ParkingSpot getParkingSpot(Long id) {
        ParkingSpot parkingSpot= parkingSpotRepository.findById(id).orElseThrow(()->new ParkingSpotNotFoundException("Parking spot with id "+id+" not found"));
        return parkingSpot;
    }

    public Page<ParkingSpot> getParkingSpotsByResponsibleName(String responsibleName, Pageable pageable) {
        Page<ParkingSpot> parkingSpotsPage= parkingSpotRepository.findByResponsibleNameContainingIgnoreCase(responsibleName,pageable);
        return parkingSpotsPage;
    }

    public Page<ParkingSpot> getParkingSpotsByBrandCar(String brandCar,Pageable pageable){
        Page<ParkingSpot> parkingSpotsPage= parkingSpotRepository.findByBrandCarContainingIgnoreCase(brandCar,pageable);
        return parkingSpotsPage;
    }

    @Transactional
    public ParkingSpot addParkingSpot(ParkingSpot parkingSpot) {
        if(parkingSpotRepository.existsByParkingSpotNumber(parkingSpot.getParkingSpotNumber())) {
            throw new ParkingSpotNumberExistsException("Parking spot number " + parkingSpot.getParkingSpotNumber() + " already exists");
        }
        if(parkingSpotRepository.existsByLicensePlateCar(parkingSpot.getLicensePlateCar())) {
            throw new LicensePlateCarExistsException("License plate car " + parkingSpot.getLicensePlateCar() + " already exists");
        }
        if(parkingSpotRepository.existsByApartmentAndBlock(parkingSpot.getApartment(),parkingSpot.getBlock())) {
            throw new ApartmentInBlockExistsException("Apartment " + parkingSpot.getApartment() + " in block " + parkingSpot.getBlock() + " already exists");
        }
        ParkingSpot addedParkingSpot= parkingSpotRepository.save(parkingSpot);
        LOG.info("---After add parking spot---");
        return addedParkingSpot;
    }

    @Transactional
    public ParkingSpot removeParkingSpot(Long id) {
        ParkingSpot parkingSpot= parkingSpotRepository.findById(id)
                .orElseThrow(()->new ParkingSpotNotFoundException("Parking spot with id "+id+" not found"));

        parkingSpotRepository.delete(parkingSpot);
        LOG.info("---After remove parking spot---");
        return parkingSpot;
    }

    @Transactional
    public ParkingSpot updateParkingSpot(ParkingSpot parkingSpot) {
        ParkingSpot recoveredParkingSpot= parkingSpotRepository.findById(parkingSpot.getId())
                .orElseThrow(()->new ParkingSpotNotFoundException("Parking Spot With id "+parkingSpot.getId()+" not found"));

        if (parkingSpotNumberBelongsToAnotherInstance(parkingSpot.getParkingSpotNumber(), recoveredParkingSpot.getParkingSpotNumber())) {
            throw new ParkingSpotNumberExistsException("Parking spot number " + parkingSpot.getParkingSpotNumber() + " already exists");
        }
        if (licensePlateCarBelongsToAnotherInstance(parkingSpot.getLicensePlateCar(), recoveredParkingSpot.getLicensePlateCar())) {
            throw new LicensePlateCarExistsException("License plate car " + parkingSpot.getLicensePlateCar() + " already exists");
        }
        if(apartmentInBlockBelongsToAnotherInstance(parkingSpot,recoveredParkingSpot)){
            throw new ApartmentInBlockExistsException("Apartment " + parkingSpot.getApartment() + " in block " + parkingSpot.getBlock() + " already exists");
        }

        BeanUtils.copyProperties(parkingSpot,recoveredParkingSpot,"registrationDate");
        LOG.info("---After update parking spot---");
        return recoveredParkingSpot;
    }

    private boolean parkingSpotNumberBelongsToAnotherInstance(String parkingSpotNumberA, String parkingSpotNumberB) {
        return parkingSpotRepository.existsByParkingSpotNumber(parkingSpotNumberA) && !parkingSpotNumberA.equals(parkingSpotNumberB);
    }

    private boolean licensePlateCarBelongsToAnotherInstance(String licensePlateCarA, String licensePlateCarB) {
        return parkingSpotRepository.existsByLicensePlateCar(licensePlateCarA) && !licensePlateCarA.equals(licensePlateCarB);
    }

    private boolean apartmentInBlockBelongsToAnotherInstance(ParkingSpot parkingSpot, ParkingSpot recoveredParkingSpot) {
        String apartmentAndBlockA= parkingSpot.getApartment()+parkingSpot.getBlock();
        String apartmentAndBlockB= recoveredParkingSpot.getApartment()+recoveredParkingSpot.getBlock();
        return parkingSpotRepository.existsByApartmentAndBlock(parkingSpot.getApartment(),parkingSpot.getBlock())
                && !apartmentAndBlockA.equals(apartmentAndBlockB);
    }



}
