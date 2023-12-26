package com.api.parkingcontrol.services;

public class LicensePlateCarExistsException extends RuntimeException{

    public LicensePlateCarExistsException(){
    }

    public LicensePlateCarExistsException(String message){
        super(message);
    }

}
