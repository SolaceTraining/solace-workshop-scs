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

package com.solace.workshop.spring.scs;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import com.solace.workshop.Tweet;

@SpringBootApplication
@EnableBinding(ProcessorBinding.class)
public class ScsProcessorFeatures {
	
	@Autowired 
	private ProcessorBinding processor;
	
	private String feature = "#NewFeature";
	
	private static final <T> Message<T> message(T val) {
        return MessageBuilder.withPayload(val).build();
    }
	
	@StreamListener(ProcessorBinding.INPUT)
	public void handle(Tweet tweet) {
		ArrayList<String> hashTags = tweet.getHashtags();
		if(hashTags.contains(feature) ) {
			processor.outputFeature().send(message(tweet));
		} else {
			processor.outputNoFeature().send(message(tweet));
		}
		
	}
	

	public static void main(String[] args) {
		SpringApplication.run(ScsProcessorFeatures.class, args);
	}

}
