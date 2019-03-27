package com.solace.workshops.spring.scs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;

import com.solace.workshop.Tweet;

@SpringBootApplication
@EnableBinding(Processor.class)
public class ScsProcessorYelling {
	private static final Logger log = LoggerFactory.getLogger(ScsProcessorYelling.class);

	public static void main(String[] args) {
		SpringApplication.run(ScsProcessorYelling.class, args);
	}

	@StreamListener(Processor.INPUT)
	@SendTo(Processor.OUTPUT)
	public Tweet handle(Tweet tweet) {
		log.info(tweet.toString());
		tweet.setText(tweet.getText().toLowerCase());
		return tweet;
	}

}
