package com.sivalabs.chitchat.common.models

data class PagedResult<T>(
    val data: List<T>,
    val currentPageNo: Int,
    val totalPages: Int,
    val totalElements: Long,
    val hasNextPage: Boolean,
    val hasPreviousPage: Boolean,
)
