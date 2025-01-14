package com.sivalabs.chitchat.api

import com.sivalabs.chitchat.domain.Post
import com.sivalabs.chitchat.domain.PostDto
import org.springframework.stereotype.Component

@Component
class PostMapper {
    fun toPostDto(post: Post): PostDto =
        PostDto(
            post.id!!,
            post.uid,
            post.content,
            post.createdBy.name,
            post.createdAt,
            post.updatedAt,
        )
}
