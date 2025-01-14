package com.sivalabs.chitchat

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.DefaultValue
import org.springframework.validation.annotation.Validated
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey

@ConfigurationProperties(prefix = "app")
@Validated
data class ApplicationProperties(
    val jwt: JwtProperties,
) {
    data class JwtProperties(
        @DefaultValue("SivaLabs") val issuer: String,
        @DefaultValue("604800") val expiresInSeconds: Long,
        val publicKey: RSAPublicKey,
        val privateKey: RSAPrivateKey,
    )
}
