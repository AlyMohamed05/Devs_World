package com.silverbullet.devsworld.core.domain.model

data class Profile(
    val id: String,
    val email: String,
    val username: String,
    val bio: String,
    val isOwnProfile: Boolean,
    val isFollowed: Boolean?,
    val followingCount: Int,
    val followersCount: Int,
    val postsCount: Int,
    val profileImageUrl: String?,
    val skills: List<String>,
    val githubUrl: String?,
    val linkedinUrl: String?,
    val instagramUrl: String?
)
