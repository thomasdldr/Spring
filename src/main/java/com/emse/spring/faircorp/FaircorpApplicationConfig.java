package com.emse.spring.faircorp;

import com.emse.spring.faircorp.hello.GreetingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;
import com.emse.spring.faircorp.hello.ConsoleGreetingService;

@Configuration
public class FaircorpApplicationConfig {

	@Bean
	public CommandLineRunner greetingCommandLine(GreetingService gs) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				gs.greet("Spring");
			}
		};
	}
}