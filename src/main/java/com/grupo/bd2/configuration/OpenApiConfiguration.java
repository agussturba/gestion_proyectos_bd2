package com.grupo.bd2.configuration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public GroupedOpenApi myApi() {
        return GroupedOpenApi.builder()
                .group("my-api")
                .pathsToMatch("/api/**") // Adjust path pattern as needed
                .build();
    }
}
