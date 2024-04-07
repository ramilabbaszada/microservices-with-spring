package org.portfolio.apigateway;

import org.portfolio.apigateway.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {
    @Autowired
    AuthenticationFilter authenticationFilter;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("homeService", r -> r.path("/homeService/**")
                        .filters(f -> f.stripPrefix(1).filter(authenticationFilter.apply(authenticationFilter.newConfig())))
                        .uri("lb://home-service"))
                .route("bookingService", r -> r.path("/bookingService/**")
                        .filters(f -> f.stripPrefix(1).filter(authenticationFilter.apply(authenticationFilter.newConfig())))
                        .uri("lb://booking-service"))
                .route("eurekaServer", r -> r.path("/eurekaServer/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("http://localhost:8761"))
                .route("eurekaServer", r -> r.path("/eureka/**")
                        .uri("http://localhost:8761"))
                .route("authService", r -> r.path("/authService/**")
                        .filters(f -> f.stripPrefix(1).filter(authenticationFilter.apply(authenticationFilter.newConfig())))
                        .uri("lb://auth-service"))
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

}