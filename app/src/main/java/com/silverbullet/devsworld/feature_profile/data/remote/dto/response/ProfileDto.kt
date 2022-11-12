package com.silverbullet.devsworld.feature_profile.data.remote.dto.response

data class ProfileDto(
    val email: String,
    val username: String,
    val bio: String = "",
    val isOwnProfile: Boolean,
    val followingCount: Int,
    val followersCount: Int,
    val postsCount: Int,
    val profileImageUrl: String? = null,
    val skills: List<String> = emptyList(),
    val githubUrl: String? = null,
    val linkedinUrl: String? = null,
    val instagramUrl: String? = null
)