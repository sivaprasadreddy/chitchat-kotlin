package com.sivalabs.chitchat.api

import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Component

@Component
class SecurityUtils {
    fun getLoginUserId(): Long? {
        val principal = SecurityContextHolder.getContext().authentication.principal
        if (principal is Jwt) {
            return principal.getClaim<Long>("user_id")
        }
        return null
    }

    fun getCurrentUserIdOrThrow(): Long {
        val userId = getLoginUserId()
        if (userId != null) {
            return userId
        }
        throw AccessDeniedException("Access denied")
    }
}
