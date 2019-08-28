package com.solace.workshops.spring.scs;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class MqttWebApp {

	static org.slf4j.Logger logger = LoggerFactory.getLogger(MqttWebApp.class);
	
	public static void main(String[] args) {
		SpringApplication.run(MqttWebApp.class, args);
		logger.info("Navigate to http://localhost:8090/mqttListener.html to see incoming tweets!");
	}

	@RequestMapping("/")
	String home() {
		logger.info("Someone made a request to '/'");
		return "Welcome to the Solace Spring Cloud Streams Workshop! "
				+ "Navigate to http://localhost:8090/mqttListener.html to see incoming tweets!";
	}

}
