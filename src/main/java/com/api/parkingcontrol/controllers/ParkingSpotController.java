package com.api.parkingcontrol.controllers;

import com.api.parkingcontrol.dtos.ParkingSpotDto;
import com.api.parkingcontrol.models.ParkingSpot;
import com.api.parkingcontrol.services.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@RequestMapping(value = "/api")
public class ParkingSpotController {

    @Autowired
    private ParkingSpotService parkingSpotService;


    @GetMapping(value = "/parking-spots")
    @ApiOperation("Find all Parking Spots")
    public ResponseEntity<Page<ParkingSpot>> getParkingSpots(@PageableDefault(size = 5) Pageable pageable){
        Page<ParkingSpot> parkingSpotsPage= parkingSpotService.getParkingSpots(pageable);
        return new ResponseEntity<>(parkingSpotsPage,HttpStatus.OK);
    }

    @GetMapping(value = "/parking-spots/{id}")
    @ApiOperation("Find a Parking Spot by Id")
    public ResponseEntity<Object> getParkingSpot(@ApiParam("Primary key of type Long") @PathVariable Long id){
        try {
            ParkingSpot parkingSpot= parkingSpotService.getParkingSpot(id);
            return new ResponseEntity<>(parkingSpot,HttpStatus.OK);
        }catch (ParkingSpotNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/parking-spots-by-responsible-name/{responsibleName}")
    @ApiOperation("Find Parking Spots by Responsible Name")
    public ResponseEntity<Page<ParkingSpot>> getParkingSpotsByResponsibleName(@ApiParam("Responsible name of type String") @PathVariable String responsibleName,
                                                                              @PageableDefault(size = 5) Pageable pageable){
        Page<ParkingSpot> parkingSpotsPage= parkingSpotService.getParkingSpotsByResponsibleName(responsibleName,pageable);
        return new ResponseEntity<>(parkingSpotsPage,HttpStatus.OK);
    }

    @GetMapping(value = "/parking-spots-by-brand-car/{brandCar}")
    @ApiOperation("Find Parking Spots by Brand of Car")
    public ResponseEntity<Page<ParkingSpot>> getParkingSpotsByBrandCar(@ApiParam("Brand of Car of type String") @PathVariable String brandCar,
                                                                       @PageableDefault(size = 5) Pageable pageable){
        Page<ParkingSpot> parkingSpotsPage= parkingSpotService.getParkingSpotsByBrandCar(brandCar,pageable);
        return new ResponseEntity<>(parkingSpotsPage,HttpStatus.OK);
    }


    @PostMapping(value = "/parking-spots")
    @ApiOperation("Add a Parking Spot")
    public ResponseEntity<Object> addParkingSpot(@RequestBody @Valid ParkingSpotDto parkingSpotDto){
        ParkingSpot parkingSpot= new ParkingSpot();
        BeanUtils.copyProperties(parkingSpotDto,parkingSpot);
        parkingSpot.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC-5")));//Colombian time
        try {
            ParkingSpot addedParkingSpot= parkingSpotService.addParkingSpot(parkingSpot);
            return new ResponseEntity<>(addedParkingSpot, HttpStatus.CREATED);
        }catch (ParkingSpotNumberExistsException | LicensePlateCarExistsException | ApartmentInBlockExistsException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }


    @DeleteMapping(value = "/parking-spots/{id}")
    @ApiOperation("Remove a Parking Spot")
    public ResponseEntity<Object> removeParkingSpot(@ApiParam("Primary key of type Long") @PathVariable Long id){
        try {
            ParkingSpot removedParkingSpot= parkingSpotService.removeParkingSpot(id);
            return new ResponseEntity<>(removedParkingSpot,HttpStatus.OK);
        }catch (ParkingSpotNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping(value = "parking-spots/{id}")
    @ApiOperation("Update a Parking Spot")
    public ResponseEntity<Object> updateParkingSpot(@ApiParam("Primary key of type Long") @PathVariable Long id ,
                                                    @RequestBody @Valid ParkingSpotDto parkingSpotDto){
        ParkingSpot parkingSpot= new ParkingSpot();
        BeanUtils.copyProperties(parkingSpotDto,parkingSpot);
        parkingSpot.setId(id);
        try {
            ParkingSpot updatedParkingSpot= parkingSpotService.updateParkingSpot(parkingSpot);
            return new ResponseEntity<>(updatedParkingSpot,HttpStatus.OK);
        }catch (ParkingSpotNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }catch (ParkingSpotNumberExistsException | LicensePlateCarExistsException | ApartmentInBlockExistsException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }



}
