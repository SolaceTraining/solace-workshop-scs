package com.solace.workshops.spring.scs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import com.solace.workshop.Tweet;

@SpringBootApplication
@EnableBinding(Sink.class)
public class ScsSinkTweetBoard {
	private static final Logger log = LoggerFactory.getLogger(ScsSinkTweetBoard.class);

	public static void main(String[] args) {
		SpringApplication.run(ScsSinkTweetBoard.class, args);
	}

	@StreamListener(Sink.INPUT)
	public void sink(Tweet tweet) {
		log.info(tweet.toString());
	}
}
