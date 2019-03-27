package com.solace.workshops.spring.scs;

import java.util.HashMap;
import java.util.Map;

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
public class ScsProcessorPositive {
	private static final Logger log = LoggerFactory.getLogger(ScsProcessorPositive.class);
	private static final Map<String, String> negToPosMap;

	static {
		negToPosMap = new HashMap<String, String>();
		negToPosMap.put("awful", "awesome");
		negToPosMap.put("sucks", "rocks");
		negToPosMap.put("worst", "best");
		negToPosMap.put("hate", "love");
		negToPosMap.put("lit on fire", "parked itself");
		negToPosMap.put("disappointing", "exciting");
		negToPosMap.put("exploded in", "made it up");
		negToPosMap.put("horrendous", "amazing");
		negToPosMap.put("wrong", "correct");
		negToPosMap.put("lacking", "abundant");
		negToPosMap.put("broke down", "saved me");
		negToPosMap.put("drove off", "parked");
	}

	public static void main(String[] args) {
		SpringApplication.run(ScsProcessorPositive.class, args);
	}

	@StreamListener(Processor.INPUT)
	@SendTo(Processor.OUTPUT)
	public Tweet handle(Tweet tweet) {
		log.info(tweet.toString());
		return turnPositive(tweet);
	}

	// Change negative words to positive
	private Tweet turnPositive(Tweet tweet) {
		String tweetText = tweet.getText();
		for (Map.Entry<String, String> entry : negToPosMap.entrySet()) {
			tweetText = tweetText.replaceAll("(?i)" + entry.getKey(), entry.getValue());
		}
		tweet.setText(tweetText);
		System.out.println(tweetText);
		return tweet;
	}

}
