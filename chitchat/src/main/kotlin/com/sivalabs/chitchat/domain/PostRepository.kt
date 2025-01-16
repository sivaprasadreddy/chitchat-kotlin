package com.sivalabs.chitchat.domain

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface PostRepository : JpaRepository<Post, Long> {
    @Query(
        value = """
    SELECT
        p.id, p.uid, p.content, u.name as userName,
        CASE WHEN uf.user_id IS NOT NULL THEN TRUE ELSE FALSE END AS liked,
        p.created_at as createdAt, p.updated_at as updatedAt
    FROM posts p
    JOIN users u on p.created_by = u.id
    LEFT JOIN user_favourites uf ON p.id = uf.post_id AND uf.user_id = :userId
    """,
        nativeQuery = true,
    )
    fun findPosts(
        @Param("userId") userId: Long?,
        pageRequest: Pageable,
    ): Page<PostProjection>

    @Query(
        value = """
        SELECT
            p.id, p.uid, p.content, u.name as userName,
            CASE WHEN uf.user_id IS NOT NULL THEN TRUE ELSE FALSE END AS liked,
            p.created_at as createdAt, p.updated_at as updatedAt
        FROM posts p
        JOIN users u on p.created_by = u.id
        LEFT JOIN user_favourites uf ON p.id = uf.post_id AND uf.user_id = :userId
        WHERE p.uid = :uid
        """,
        nativeQuery = true,
    )
    fun findPost(
        userId: Long?,
        uid: String,
    ): PostProjection?
}
