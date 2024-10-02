package com.semonemo.domain.model

data class SearchUser(
    val content: List<Profile> = emptyList(),
    val pageable: Pageable = Pageable(),
    val totalElements: Int = 0,
    val totalPages: Int = 0,
    val last: Boolean = false,
    val size: Int = 0,
    val number: Int = 0,
    val sort: Sort = Sort(),
    val numberOfElements: Int = 0,
    val first: Boolean = false,
    val empty: Boolean = false,
)

data class Profile(
    val userId: Long = 0L,
    val nickname: String = "",
    val profileImageUrl: String = "",
)

data class Pageable(
    val pageNumber: Int = 0,
    val pageSize: Int = 0,
    val sort: Sort = Sort(),
    val offset: Int = 0,
    val paged: Boolean = false,
    val unpaged: Boolean = false,
)

data class Sort(
    val empty: Boolean = false,
    val sorted: Boolean = false,
    val unsorted: Boolean = false,
)
