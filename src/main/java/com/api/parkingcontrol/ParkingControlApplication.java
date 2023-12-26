package com.api.parkingcontrol;

import com.api.parkingcontrol.models.ParkingSpot;
import com.api.parkingcontrol.repositories.ParkingSpotRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;

@SpringBootApplication
public class ParkingControlApplication implements CommandLineRunner {

	private static final Logger LOG= LoggerFactory.getLogger(ParkingControlApplication.class);

	@Autowired
	private ParkingSpotRepository parkingSpotRepository;

	public static void main(String[] args) {
		SpringApplication.run(ParkingControlApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//execute only first time
		ParkingSpot parkingSpot1= new ParkingSpot(
				null,
				"123",
				"ABC123",
				"Toyota",
				"1",
				"Red",
				LocalDateTime.now(ZoneId.of("UTC-5")),
				"Mark",
				"a",
				"1");

		ParkingSpot parkingSpot2= new ParkingSpot(
				null,
				"124",
				"ABC124",
				"Mazda",
				"1",
				"Red",
				LocalDateTime.now(ZoneId.of("UTC-5")),
				"Juan",
				"a",
				"2");

		ParkingSpot parkingSpot3= new ParkingSpot(
				null,
				"125",
				"ABC125",
				"Toyota",
				"1",
				"Red",
				LocalDateTime.now(ZoneId.of("UTC-5")),
				"Martha",
				"a",
				"3");

		parkingSpotRepository.saveAll(Arrays.asList(parkingSpot1,parkingSpot2,parkingSpot3));
	}

}
