package com.sivalabs.chitchat.api

import com.sivalabs.chitchat.domain.Post
import com.sivalabs.chitchat.domain.PostDto
import com.sivalabs.chitchat.domain.PostProjection
import org.springframework.stereotype.Component

@Component
class PostMapper {
    fun toPostDto(post: Post): PostDto =
        PostDto(
            post.id!!,
            post.uid,
            post.content,
            post.createdBy.name,
            false,
            post.createdAt,
            post.updatedAt,
        )

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
