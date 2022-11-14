package com.silverbullet.devsworld.core.data.remote.dto.request

/**
 * Used for like and unlike request
 */
data class LikeRequest(
    val likedParentId: String,
    val parentType: Int
)
