package com.silverbullet.devsworld.core.domain.model

data class Post(
    val imageUrl: String,
    val description: String,
    val timestamp: Long,
    val userId: String,
    val username: String,
    val likesCount: Long,
    val commentsCount: Long
)
