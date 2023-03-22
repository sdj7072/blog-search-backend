package com.example.search.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("Ver. 1.0")
                .pathsToMatch("/api/**")
                .build();
    }
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info().title("Example API")
                        .description("Example API Service 명세서 입니다.")
                        .version("Ver. 1.0"));
    }
}
