package com.sivalabs.chitchat.domain

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) {
    fun findByEmail(email: String): User? = userRepository.findByEmailEqualsIgnoreCase(email)

    fun login(
        email: String,
        password: String,
    ): User? {
        val user = findByEmail(email)
        if (user == null || !passwordEncoder.matches(password, user.password)) {
            return null
        }
        return user
    }
}
