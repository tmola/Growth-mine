package com.fish;

import components.response.config.EnableResponseDeal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableResponseDeal
@SpringBootApplication
public class FishApplication {

	public static void main(String[] args) {
		SpringApplication.run(FishApplication.class, args);
	}

}
