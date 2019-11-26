package com.sbot;

import com.sbot.common.utils.ToolUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class SbotApplication {

	public static void main(String[] args) {
		ToolUtil.appRun(SbotApplication.class, args);
	}

}
