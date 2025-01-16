package com.sivalabs.chitchat.domain

import java.time.LocalDateTime

interface PostProjection {
    fun getId(): Long

    fun getUid(): String

    fun getContent(): String

    fun getUserName(): String

    fun isLiked(): Boolean

    fun getCreatedAt(): LocalDateTime

    fun getUpdatedAt(): LocalDateTime?
}
