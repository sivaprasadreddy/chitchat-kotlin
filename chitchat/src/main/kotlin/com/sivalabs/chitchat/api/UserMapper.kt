package com.sivalabs.chitchat.api

import com.sivalabs.chitchat.domain.User
import com.sivalabs.chitchat.domain.UserDto
import org.springframework.stereotype.Component

@Component
class UserMapper {
    fun toUserDto(user: User): UserDto = UserDto(user.id!!, user.email, user.password, user.name, user.role)
}
