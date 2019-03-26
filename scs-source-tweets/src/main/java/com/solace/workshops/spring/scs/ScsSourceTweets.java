package com.solace.workshops.spring.scs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import com.solace.workshop.Tweet;

@SpringBootApplication
@EnableBinding(Source.class)
public class ScsSourceTweets {
	private static final Logger log = LoggerFactory.getLogger(ScsSourceTweets.class);

	private ArrayList<Tweet> tweetList;
	private Iterator<Tweet> tweetIterator;

	// Default constructor
	public ScsSourceTweets() {
		this("src/main/resources/canned_tweets.txt");
	}

	// Constructor that takes in a tweet file
	public ScsSourceTweets(String tweetFile) {
		String filename = tweetFile;
		if (null == filename) {
			throw new IllegalArgumentException("Must pass in a tweet file");
		}

		tweetList = readFile(filename);
		tweetIterator = tweetList.iterator();
		System.out.printf("Loaded " + tweetList.size() + " tweet templates to send");
		log.info("Loaded %d tweet templates to send", tweetList.size());
	}

	public static void main(String[] args) {
		SpringApplication.run(ScsSourceTweets.class, args);
	}

	// Method that sends a tweet every fixedRate ms
	@InboundChannelAdapter(channel = Source.OUTPUT, poller = @Poller(fixedRate = "1000"))
	public Message<?> sendTweet() {
		Tweet nextTweet;
		if (tweetIterator.hasNext()) {
			nextTweet = tweetIterator.next();
		} else {
			tweetIterator = tweetList.iterator();
			nextTweet = tweetIterator.next();
		}
		nextTweet.updateTimestamp();
		nextTweet.updateUuid();
		log.info(nextTweet.toString());
		return MessageBuilder.withPayload(nextTweet).build();
	}

	// Method to read the file of canned tweets
	private ArrayList<Tweet> readFile(String filename) {
		ArrayList<Tweet> tweets = new ArrayList<Tweet>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line;
			// Create a new tweet object for each line in the file
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(":::");
				tweets.add(new Tweet(parts[0], parts[1]));
			}
			reader.close();
			return tweets;
		} catch (IOException e) {
			log.error("Exception occurred trying to read " + filename);
			e.printStackTrace();
			return null;
		}
	}
}
