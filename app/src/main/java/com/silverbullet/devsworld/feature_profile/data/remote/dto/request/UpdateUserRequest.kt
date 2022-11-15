package com.silverbullet.devsworld.feature_profile.data.remote.dto.request

data class UpdateUserRequest(
    val username: String,
    val githubUrl: String,
    val linkedinUrl: String,
    val instagramUrl: String,
    val bio: String,
    val skills: List<String>,
)