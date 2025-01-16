package com.sivalabs.chitchat.domain

data class CreatePostCmd(
    val uid: String,
    val content: String,
    val createdBy: Long,
)
