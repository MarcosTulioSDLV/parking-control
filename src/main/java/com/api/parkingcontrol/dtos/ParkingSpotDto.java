package com.api.parkingcontrol.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.ISBN;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter @Setter @ToString @ApiModel(value = "ParkingSpotDto",description = "Class to validate the Parking Spot attributes")
public class ParkingSpotDto {

    @NotBlank @ApiModelProperty("Unique attribute")
    private String parkingSpotNumber;

    @NotBlank @Size(max = 7) @ApiModelProperty("Unique attribute")
    private String licensePlateCar;
    @NotBlank
    private String brandCar;
    @NotBlank
    private String modelCar;
    @NotBlank
    private String colorCar;

    @NotBlank
    private String responsibleName;
    @NotBlank
    private String block;
    @NotBlank @ApiModelProperty("Unique per block")
    private String apartment;


}
