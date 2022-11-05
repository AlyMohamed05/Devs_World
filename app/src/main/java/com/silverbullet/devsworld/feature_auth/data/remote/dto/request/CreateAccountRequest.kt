package com.silverbullet.devsworld.feature_auth.data.remote.dto.request

data class CreateAccountRequest(
    val email: String,
    val username: String,
    val password: String
)
