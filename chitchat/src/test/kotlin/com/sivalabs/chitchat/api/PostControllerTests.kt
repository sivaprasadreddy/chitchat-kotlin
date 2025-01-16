package com.sivalabs.chitchat.api

import com.fasterxml.jackson.core.type.TypeReference
import com.sivalabs.chitchat.BaseIT
import com.sivalabs.chitchat.common.models.PagedResult
import com.sivalabs.chitchat.domain.PostDto
import com.sivalabs.chitchat.domain.Role
import com.sivalabs.chitchat.domain.UserDto
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PostControllerTests : BaseIT() {
    @Test
    fun shouldGetPosts() {
        val response =
            given()
                .accept(ContentType.JSON)
                .`when`()
                .get("/api/posts")
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .asString()
        val typeRef = object : TypeReference<PagedResult<PostDto>>() {}
        val pagedResult = objectMapper.readValue(response, typeRef)
        assertThat(pagedResult.data).hasSize(5)
        assertThat(pagedResult.currentPageNo).isEqualTo(1)
        assertThat(pagedResult.totalPages).isEqualTo(2)
        assertThat(pagedResult.hasNextPage).isEqualTo(true)
        assertThat(pagedResult.hasPreviousPage).isEqualTo(false)
    }

    @Test
    fun shouldCreatePostSuccessfully() {
        val payload = CreatePostPayload("Post content-" + System.currentTimeMillis())
        val userDto = UserDto(2L, "Siva", "siva@gmail.com", "", Role.ROLE_USER)
        val token = jwtTokenHelper.generateToken(userDto).token
        val postDto =
            given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer $token")
                .body(payload)
                .`when`()
                .post("/api/posts")
                .then()
                .assertThat()
                .statusCode(201)
                .extract()
                .`as`(PostDto::class.java)
        assertThat(postDto.content).isEqualTo(payload.content)
    }
}
