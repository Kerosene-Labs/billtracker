package com.kerosenelabs.billtracker.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class OpenApiConfig {
    @Bean
    open fun customOpenAPI(): OpenAPI {
        val securitySchemeName = "bearerAuth"

        return OpenAPI()
            .info(Info().title("API Documentation").version("1.0"))
            .addSecurityItem(SecurityRequirement().addList(securitySchemeName))
            .components(
                io.swagger.v3.oas.models.Components()
                    .addSecuritySchemes(
                        securitySchemeName,
                        SecurityScheme()
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("bearer")
                            .bearerFormat("JWT")
                    )
            )
    }

}