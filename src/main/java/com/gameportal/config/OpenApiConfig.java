package com.gameportal.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;
import org.springdoc.core.customizers.OpenApiCustomizer;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springframework.context.annotation.Bean;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "GamePortal API",
                version = "1.0",
                description = "REST API dla serwisu z grami — przeglądanie, ocenianie i zarządzanie grami",
                contact = @Contact(
                        name = "Paweł Kuczek",
                        url = "https://github.com/pawelkuczek/gameportal"
                )
        )
)
public class OpenApiConfig {
    @Bean
    public OpenApiCustomizer pageableCustomizer() {
        return openApi -> openApi.getComponents()
                .addParameters("pageable", new Parameter()
                        .in("query")
                        .name("pageable")
                        .required(false));
    }
}
