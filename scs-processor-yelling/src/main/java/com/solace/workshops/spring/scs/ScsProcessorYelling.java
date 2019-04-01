/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

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

	// We define an INPUT to receive and an OUTPUT to send to!
	@StreamListener(Processor.INPUT)
	@SendTo(Processor.OUTPUT)
	public Tweet handle(Tweet tweet) {
		log.info("Input: " + tweet.toString());
		// Note that even though we output a Tweet POJO under the covers the message
		// will be in JSON so it doesn't have to be received by a Java App
		tweet.setText(tweet.getText().toLowerCase());
		log.info("Output: " + tweet.toString());
		return tweet;
	}

}
