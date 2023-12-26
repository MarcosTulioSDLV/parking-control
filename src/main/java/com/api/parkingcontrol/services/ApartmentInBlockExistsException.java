package com.api.parkingcontrol.services;

public class ApartmentInBlockExistsException extends RuntimeException{

    public ApartmentInBlockExistsException(){
    }

    public ApartmentInBlockExistsException(String message){
        super(message);
    }

}
