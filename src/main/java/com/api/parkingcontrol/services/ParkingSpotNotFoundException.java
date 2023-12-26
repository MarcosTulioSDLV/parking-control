package com.api.parkingcontrol.services;

public class ParkingSpotNotFoundException extends RuntimeException{

    public ParkingSpotNotFoundException(){
    }

    public ParkingSpotNotFoundException(String message){
        super(message);
    }

}
