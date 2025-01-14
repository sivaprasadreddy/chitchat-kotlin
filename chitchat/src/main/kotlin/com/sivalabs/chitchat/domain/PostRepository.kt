package com.sivalabs.chitchat.domain

import org.springframework.data.repository.ListPagingAndSortingRepository

interface PostRepository : ListPagingAndSortingRepository<Post, Long>
