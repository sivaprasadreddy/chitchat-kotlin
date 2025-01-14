package com.sivalabs.chitchat.config

import com.sivalabs.chitchat.ApplicationProperties
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {
    @Bean
    fun openApi(properties: ApplicationProperties): OpenAPI {
        val contact =
            Contact()
                .name("SivaLabs")
                .email("support@sivalabs.in")
        val info =
            Info()
                .title("SivaLabs ChitChat API")
                .description("SivaLabs ChitChat API")
                .version("v1.0.0")
                .contact(contact)
        return OpenAPI()
            .info(info)
            .addSecurityItem(SecurityRequirement().addList("Authorization"))
            .components(Components().addSecuritySchemes("Bearer", createJwtTokenScheme()))
    }

    private fun createJwtTokenScheme(): SecurityScheme =
        SecurityScheme()
            .name("Authorization")
            .type(SecurityScheme.Type.HTTP)
            .bearerFormat("JWT")
            .scheme("Bearer")
}
