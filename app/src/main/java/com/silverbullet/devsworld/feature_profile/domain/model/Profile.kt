package com.silverbullet.devsworld.feature_profile.domain.model

data class Profile(
    val email: String,
    val username: String,
    val bio: String,
    val isOwnProfile: Boolean,
    val followingCount: Int,
    val followersCount: Int,
    val postsCount: Int,
    val profileImageUrl: String?,
    val skills: List<String>,
    val githubUrl: String?,
    val linkedinUrl: String?,
    val instagramUrl: String?
)
