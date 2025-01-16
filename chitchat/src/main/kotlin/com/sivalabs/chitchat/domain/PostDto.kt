package com.sivalabs.chitchat.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDateTime

data class PostDto(
    val id: Long,
    val uid: String,
    val content: String,
    val userName: String,
    val liked: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime?,
) {
    @get:JsonIgnore
    val shortDescription: String
        get() = if (content.length > 200) content.substring(0, 200) + "..." else content
}
