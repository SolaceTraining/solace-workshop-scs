package com.solace.workshops.spring.scs;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import com.solace.workshop.Tweet;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScsProcessorYellingReactiveTests {


	@Autowired
	private Processor processor;

	@Autowired
	private MessageCollector collector;

	@Test
	public void testLowercaseTweet() throws InterruptedException {
		String test = "ANGRY_YELLING_TWEET!!!!";
		Tweet tweet = new Tweet("username", test);

		Message<Tweet> msgInput = MessageBuilder.withPayload(tweet).build();

		processor.input().send(msgInput);

		Message<?> msgOutput = (Message<?>) collector.forChannel(processor.output()).poll(10, TimeUnit.SECONDS);
		String payload = (msgOutput != null) ? (String) msgOutput.getPayload() : null;

		assertNotNull(payload);
		assertThat((String) payload,
				allOf(containsString(test.toLowerCase()), containsString("username"), containsString("text"),
						containsString("userMentions"), containsString("timestamp"), containsString("hashtags")));

	}


}
