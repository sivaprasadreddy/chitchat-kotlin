package com.sivalabs.chitchat.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.HttpStatusEntryPoint

@Configuration
@EnableWebSecurity
class WebSecurityConfig {
    val allowedUrls =
        listOf(
            "/",
            "/favicon.ico",
            "/actuator/**",
            "/error",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/api/**",
        )

    @Bean
    fun apiSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.securityMatcher("/api/**")
        http.csrf { obj: CsrfConfigurer<HttpSecurity> -> obj.disable() }
        http.cors { obj: CorsConfigurer<HttpSecurity> -> obj.disable() }

        http.authorizeHttpRequests { c ->
            c
                .requestMatchers(*allowedUrls.toTypedArray())
                .permitAll()
                .requestMatchers(HttpMethod.OPTIONS, "/**")
                .permitAll()
                .requestMatchers("/api/login")
                .permitAll()
                .requestMatchers(HttpMethod.GET, "/api/posts", "/api/posts/**")
                .permitAll()
                .anyRequest()
                .authenticated()
        }

        http.oauth2ResourceServer { c -> c.jwt(Customizer.withDefaults()) }

        http.exceptionHandling { c ->
            c.authenticationEntryPoint(HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
        }

        http.sessionManagement { session ->
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        }

        return http.build()
    }
}
