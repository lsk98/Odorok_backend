package com.odorok.OdorokApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OdorokApplication {

	public static void main(String[] args) {
		SpringApplication.run(OdorokApplication.class, args);
	}

}
