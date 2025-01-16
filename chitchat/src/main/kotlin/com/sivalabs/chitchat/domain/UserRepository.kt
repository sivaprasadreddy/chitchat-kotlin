package com.sivalabs.chitchat.domain

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmailEqualsIgnoreCase(email: String): User?
}
