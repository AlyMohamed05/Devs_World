package com.silverbullet.devsworld.core.domain.model

data class User(
    val profilePictureUrl: String,
    val username: String,
    val description: String,
    val followersCount: Int,
    val followingCount: Int,
    val postCount: Int
)
