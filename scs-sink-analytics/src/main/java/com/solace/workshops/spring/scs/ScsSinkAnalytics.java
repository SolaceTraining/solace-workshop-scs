package com.solace.workshops.spring.scs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@SpringBootApplication
@EnableBinding(Sink.class)
public class ScsSinkAnalytics {
	private static final Logger log = LoggerFactory.getLogger(ScsSinkAnalytics.class);

	public static void main(String[] args) {
		SpringApplication.run(ScsSinkAnalytics.class, args);
	}

	@StreamListener(Sink.INPUT)
	public void sink(String input) {
		log.info(input);
	}
}
