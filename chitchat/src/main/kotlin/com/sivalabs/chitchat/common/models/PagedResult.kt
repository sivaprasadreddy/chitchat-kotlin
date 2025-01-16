package com.sivalabs.chitchat.common.models

import org.springframework.data.domain.Page
import java.util.function.Function

data class PagedResult<T>(
    val data: List<T>,
    val currentPageNo: Int,
    val totalPages: Int,
    val totalElements: Long,
    val hasNextPage: Boolean,
    val hasPreviousPage: Boolean,
) {
    fun <R> map(converter: Function<T, R>): PagedResult<R> =
        PagedResult(
            this.data
                .stream()
                .map(converter)
                .toList(),
            this.currentPageNo,
            this.totalPages,
            this.totalElements,
            this.hasNextPage,
            this.hasPreviousPage,
        )

    companion object {
        fun <T> empty() =
            PagedResult<T>(
                data = emptyList(),
                currentPageNo = 0,
                totalPages = 0,
                totalElements = 0,
                hasNextPage = false,
                hasPreviousPage = false,
            )

        fun <T> from(page: Page<T>) =
            PagedResult(
                data = page.content,
                currentPageNo = page.number + 1,
                totalPages = page.totalPages,
                totalElements = page.totalElements,
                hasNextPage = page.hasNext(),
                hasPreviousPage = page.hasPrevious(),
            )
    }
}
