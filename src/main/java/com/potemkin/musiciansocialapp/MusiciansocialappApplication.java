package com.potemkin.musiciansocialapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MusiciansocialappApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusiciansocialappApplication.class, args);
	}

}
