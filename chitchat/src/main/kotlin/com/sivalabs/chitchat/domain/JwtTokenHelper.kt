package com.sivalabs.chitchat.domain

import com.sivalabs.chitchat.ApplicationProperties
import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class JwtTokenHelper(
    private val encoder: JwtEncoder,
    private val properties: ApplicationProperties,
) {
    fun generateToken(userDto: UserDto): JwtToken {
        val now = Instant.now()
        val expiresAt = now.plusSeconds(properties.jwt.expiresInSeconds)
        val claims: JwtClaimsSet =
            JwtClaimsSet
                .builder()
                .issuer(properties.jwt.issuer)
                .issuedAt(now)
                .expiresAt(expiresAt)
                .subject(userDto.email)
                .claim("user_id", userDto.id)
                .claim("role", userDto.role.name)
                .build()
        val token: String = encoder.encode(JwtEncoderParameters.from(claims)).tokenValue
        return JwtToken(token, expiresAt)
    }
}
