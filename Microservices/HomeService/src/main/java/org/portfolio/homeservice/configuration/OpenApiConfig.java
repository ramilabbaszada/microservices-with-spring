package org.portfolio.homeservice.configuration;

import io.swagger.v3.oas.models.parameters.Parameter;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    /*@Bean
    public OperationCustomizer customize() {
        return (operation, handlerMethod) -> operation.addParametersItem(
                new Parameter()
                        .in("application/json")
                        .required(true)
                        .description("content-type")
                        .description("content-type")
                        .name("content-type"));
    }*/
}
