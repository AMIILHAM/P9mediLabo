package com.solutions.medilabo.gateway;

import com.solutions.medilabo.gateway.config.CorsGlobalFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder, CorsGlobalFilter globalCorsFilter) {
		return builder.routes()
				.route(r -> r.path("/auth/**")
						.filters(f -> f.filter(globalCorsFilter))
						.uri("http://localhost:8080"))
				.build();
	}

}
