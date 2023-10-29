package com.solutions.medilabo.gateway.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BackendGateway {

    @Value("${routes.backend}")
    private String backendRoute;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("backend-route", r -> r
                        .path("/patients/**", "/auth/**")
                        //.filters(f -> f.addResponseHeader("X-Powered-By", "MediLab Solutions Gateway Service"))
                        .uri(backendRoute)
                )
                .build();
    }

}
