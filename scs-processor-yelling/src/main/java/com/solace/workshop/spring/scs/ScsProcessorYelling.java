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


import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;

import com.solace.workshop.Tweet;

import reactor.core.publisher.Flux;

@SpringBootApplication
@EnableBinding(Processor.class)
public class ScsProcessorYelling {
	
	private static final Logger logger = LoggerFactory.getLogger(ScsProcessorYelling.class);

	public static void main(String[] args) {
		// Defining the reactive function to bind to the INPUT channel of the Processor
		SpringApplication.run(ScsProcessorYelling.class, "--spring.cloud.stream.function.definition=changeCase");
	}
	
	@Bean
	public Function<Flux<Tweet>, Flux<Tweet>> changeCase() {
		return flux -> flux
				.doOnNext(t ->logger.info("====Tweet BEFORE mapping: " + t.toString()))
				.map(t -> { t.setText(t.getText().toLowerCase());
					return t;
					})
				.doOnNext(t ->logger.info("++++Tweet AFTER mapping: " + t.toString()))
				;
	
	}
		
}

