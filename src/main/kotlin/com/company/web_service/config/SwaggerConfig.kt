package com.company.web_service.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun customOpenAPI(): OpenAPI {
        val securitySchemeName = "basicAuth"
        return OpenAPI()
            .info(
                Info()
                    .title("product service endpoints")
                    .version("1.0.0")
                    .description("This is an Product API documented with Swagger and SpringDoc.")
            )
            .components(
                Components()
                    .addSecuritySchemes(
                        securitySchemeName,
                SecurityScheme()
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("basic")
            )
        )
        .addSecurityItem(
            SecurityRequirement()
                .addList(securitySchemeName)
        )
    }
}