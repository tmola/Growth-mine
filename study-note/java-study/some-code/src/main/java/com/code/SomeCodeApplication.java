package com.code;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@EnableSwagger2
@SpringBootApplication
public class SomeCodeApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext application = SpringApplication.run(SomeCodeApplication.class, args);
		Environment env = application.getEnvironment();
		String ip = "127.0.0.1";
		String port = env.getProperty("server.port");
		String path = env.getProperty("server.servlet.context-path");
		log.info("\n----------------------------------------------------------\n\t" +
				"Application is running! Access URLs:\n\t" +
				"Local:       http://localhost:" + port + path + "/\n\t" +
				"External:    http://" + ip + ":" + port + path + "/\n\t" +
				"Swagger-ui:  http://" + ip + ":" + port + path + "/swagger-ui.html\n\t" +
				"----------------------------------------------------------");
	}

}
