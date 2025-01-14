package com.sivalabs.chitchat.config

import com.sivalabs.chitchat.domain.SecurityUser
import com.sivalabs.chitchat.domain.User
import com.sivalabs.chitchat.domain.UserService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class SecurityUserDetailsService(
    private val userService: UserService,
) : UserDetailsService {
    override fun loadUserByUsername(userName: String): UserDetails {
        val user = userService.findByEmail(userName) ?: throw UsernameNotFoundException("Email $userName not found")
        return this.toSecurityUser(user)
    }

    private fun toSecurityUser(user: User): SecurityUser = SecurityUser(user.id!!, user.email, user.password, user.role)
}
