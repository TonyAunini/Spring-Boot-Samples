package com.restapi.assignment.trackinghistory;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import io.swagger.v3.oas.models.info.Info;

@EnableWebMvc
@SpringBootApplication
public class TrackingHistoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrackingHistoryApplication.class, args);
	}
	
	@Bean
	public GroupedOpenApi coffeeOpenApi() {
		String[] paths = { "/track/**" };
		return GroupedOpenApi.builder().group("Tracking")
				.addOpenApiCustomiser(openApi -> openApi.info(new Info().title("Tracking API")))
				.pathsToMatch(paths)
				.build();
	}
	

}
