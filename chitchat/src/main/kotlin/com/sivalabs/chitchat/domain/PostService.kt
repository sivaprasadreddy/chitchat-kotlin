package com.sivalabs.chitchat.domain

import com.sivalabs.chitchat.common.models.PagedResult
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional(readOnly = true)
class PostService(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository,
) {
    fun getPosts(
        loginUserId: Long?,
        page: Int,
    ): PagedResult<PostProjection> {
        val pageRequest = getPageRequest(page)
        val resultsPage = postRepository.findPosts(loginUserId, pageRequest)
        return PagedResult.from(resultsPage)
    }

    fun getPost(
        loginUserId: Long?,
        uid: String,
    ): PostProjection? = postRepository.findPost(loginUserId, uid)

    @Transactional
    fun createPost(cmd: CreatePostCmd): Post {
        val post =
            Post(
                null,
                cmd.uid,
                cmd.content,
                userRepository.getReferenceById(cmd.createdBy),
                LocalDateTime.now(),
            )
        return postRepository.save(post)
    }

    private fun getPageRequest(page: Int): Pageable {
        val pageNo = if (page < 1) 1 else page
        val sort = Sort.by(Sort.Direction.DESC, "createdAt")
        val pageSize = 5
        return PageRequest.of(pageNo - 1, pageSize, sort)
    }
}
