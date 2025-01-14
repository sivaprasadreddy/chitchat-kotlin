package com.sivalabs.chitchat.api

import com.sivalabs.chitchat.domain.PostDto
import com.sivalabs.chitchat.domain.PostService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PostController(
    private val postService: PostService,
    private val postMapper: PostMapper,
) {
    @GetMapping("/api/posts")
    fun getAllPosts(): List<PostDto> = postService.getAllPosts().map { postMapper.toPostDto(it) }
}
