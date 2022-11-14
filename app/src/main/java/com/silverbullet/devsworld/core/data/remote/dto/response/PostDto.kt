package com.silverbullet.devsworld.core.data.remote.dto.response

data class PostDto(
    val id: String,
    val imageUrl: String,
    val description: String,
    val timestamp: Long,
    val userId: String,
    val username: String,
    val isLiked: Boolean,
    val likesCount: Long,
    val commentsCount: Long
)
