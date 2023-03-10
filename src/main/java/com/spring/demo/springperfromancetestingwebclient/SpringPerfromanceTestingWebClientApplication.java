package com.spring.demo.springperfromancetestingwebclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class SpringPerfromanceTestingWebClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringPerfromanceTestingWebClientApplication.class, args);
	}


	@Bean
	WebClient webClient(){
		return WebClient.create ();
	}

}
