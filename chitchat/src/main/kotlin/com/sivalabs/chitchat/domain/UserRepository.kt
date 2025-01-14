package com.sivalabs.chitchat.domain

import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {
    fun findByEmailEqualsIgnoreCase(email: String): User?
}
