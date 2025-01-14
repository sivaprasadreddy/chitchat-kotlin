package com.sivalabs.chitchat.domain

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.io.Serial
import java.io.Serializable

class SecurityUser(
    private val id: Long,
    private val username: String,
    private val password: String,
    private val role: Role,
) : UserDetails,
    Serializable {
    companion object {
        @Serial
        private const val serialVersionUID = 1L
    }

    override fun getAuthorities() = setOf(SimpleGrantedAuthority(role.name))

    override fun getUsername() = username

    override fun getPassword() = password
}
