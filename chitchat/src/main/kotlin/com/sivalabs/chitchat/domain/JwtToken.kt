package com.sivalabs.chitchat.domain

import java.time.Instant

data class JwtToken(
    val token: String,
    val expiresAt: Instant,
)
