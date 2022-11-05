package com.silverbullet.devsworld.core.data.remote.dto.response

data class BasicApiResponse<T>(
    val successful: Boolean,
    val message: String? = null,
    val data: T? = null
)