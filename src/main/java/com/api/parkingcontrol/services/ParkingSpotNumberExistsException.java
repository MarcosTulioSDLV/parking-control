package com.api.parkingcontrol.services;

public class ParkingSpotNumberExistsException extends RuntimeException{

    public ParkingSpotNumberExistsException(){
    }

    public ParkingSpotNumberExistsException(String message){
        super(message);
    }

}
