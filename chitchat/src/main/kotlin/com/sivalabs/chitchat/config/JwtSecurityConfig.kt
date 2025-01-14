package com.sivalabs.chitchat.config

import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.RSAKey
import com.nimbusds.jose.jwk.source.ImmutableJWKSet
import com.nimbusds.jose.jwk.source.JWKSource
import com.nimbusds.jose.proc.SecurityContext
import com.sivalabs.chitchat.ApplicationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder

@Configuration
class JwtSecurityConfig(
    private val properties: ApplicationProperties,
) {
    @Bean
    fun jwtDecoder(): JwtDecoder = NimbusJwtDecoder.withPublicKey(properties.jwt.publicKey).build()

    @Bean
    fun jwtEncoder(): JwtEncoder {
        val jwk =
            RSAKey
                .Builder(properties.jwt.publicKey)
                .privateKey(properties.jwt.privateKey)
                .build()
        val jwks: JWKSource<SecurityContext> = ImmutableJWKSet(JWKSet(jwk))
        return NimbusJwtEncoder(jwks)
    }
}
