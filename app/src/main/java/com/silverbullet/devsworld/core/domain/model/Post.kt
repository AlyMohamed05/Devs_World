package com.silverbullet.devsworld.core.domain.model

data class Post(
    val id: String,
    val imageUrl: String,
    val description: String,
    val timestamp: Long,
    val userId: String,
    val profileImageUrl: String?,
    val username: String,
    val isLiked: Boolean,
    val likesCount: Long,
    val commentsCount: Long
)
