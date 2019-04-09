package com.solace.workshop.spring.scs.scsprocessoryellingfunctionreactive;


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
public class ScsProcessorYellingFunctionReactive {
	
	private static final Logger logger = LoggerFactory.getLogger(ScsProcessorYellingFunctionReactive.class);

	public static void main(String[] args) {
		SpringApplication.run(ScsProcessorYellingFunctionReactive.class, "--spring.cloud.stream.function.definition=changeCase");
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

