package com.sivalabs.chitchat.domain

data class UserDto(
    val id: Long,
    val name: String,
    val email: String,
    val password: String,
    val role: Role,
)
