package com.sivalabs.chitchat.api

import com.sivalabs.chitchat.common.models.PagedResult
import com.sivalabs.chitchat.domain.CreatePostCmd
import com.sivalabs.chitchat.domain.PostDto
import com.sivalabs.chitchat.domain.PostService
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.UUID

@RestController
class PostController(
    private val postService: PostService,
    private val postMapper: PostMapper,
    private val securityUtils: SecurityUtils,
) {
    @GetMapping("/api/posts")
    fun getAllPosts(
        @RequestParam(value = "page", defaultValue = "1") page: Int,
    ): PagedResult<PostDto> {
        val userId = securityUtils.getLoginUserId()
        val postProjections = postService.getPosts(userId, page)
        return postProjections.map { postMapper.toPostDto(it) }
    }

    @PostMapping("/api/posts")
    fun createPost(
        @Valid @RequestBody postPayload: CreatePostPayload,
    ): ResponseEntity<PostDto> {
        val uid = UUID.randomUUID().toString()
        val userId = securityUtils.getCurrentUserIdOrThrow()
        val cmd = CreatePostCmd(uid, postPayload.content!!, userId)
        val post = postService.createPost(cmd)
        val postProjection = postService.getPost(userId, post.uid)!!
        val postDto = postMapper.toPostDto(postProjection)
        val location =
            ServletUriComponentsBuilder
                .fromCurrentRequest()
                .replacePath(null)
                .path("/api/posts/{uid}")
                .buildAndExpand(uid)
                .toUri()
        return ResponseEntity.created(location).body(postDto)
    }
}

data class CreatePostPayload(
    @field:NotBlank(message = "Content is required")
    val content: String?,
)
