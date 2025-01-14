package com.sivalabs.chitchat.domain

import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class PostService(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository,
) {
    fun getAllPosts(): List<Post> = postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"))
}
