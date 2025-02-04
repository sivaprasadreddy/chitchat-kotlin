package com.sivalabs.chitchat.api

import com.sivalabs.chitchat.domain.PostDto
import com.sivalabs.chitchat.domain.PostProjection
import org.springframework.stereotype.Component

@Component
class PostMapper {
    fun toPostDto(post: PostProjection): PostDto =
        PostDto(
            post.getId(),
            post.getUid(),
            post.getContent(),
            post.getUserName(),
            post.isLiked(),
            post.getCreatedAt(),
            post.getUpdatedAt(),
        )
}
