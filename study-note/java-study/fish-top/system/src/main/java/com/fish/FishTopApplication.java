package com.fish;

import fish.unit.EnableGlobalDispose;
import fish.util.AppUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableGlobalDispose
@SpringBootApplication
@ComponentScan(value = "com.fish,config")
public class FishTopApplication {
	public static void main(String[] args) {
		AppUtil.info(SpringApplication.run(FishTopApplication.class, args));
	}
}
