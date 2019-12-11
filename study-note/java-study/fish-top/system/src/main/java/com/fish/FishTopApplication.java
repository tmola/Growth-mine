package com.fish;

import fish.unit.EnableGlobalDispose;
import fish.util.AppUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableGlobalDispose
@SpringBootApplication
public class FishTopApplication {
	public static void main(String[] args) {
		AppUtil.info(SpringApplication.run(FishTopApplication.class, args));
	}
}
