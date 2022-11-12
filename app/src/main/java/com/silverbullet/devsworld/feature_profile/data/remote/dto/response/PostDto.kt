package com.silverbullet.devsworld.feature_profile.data.remote.dto.response

data class PostDto(
    val imageUrl: String,
    val description: String,
    val timestamp: Long,
    val userId: String,
    val username: String,
    val likesCount: Long,
    val commentsCount: Long
)
