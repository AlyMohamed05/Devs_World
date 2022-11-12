package com.silverbullet.devsworld.feature_activity.data.remote.dto

data class ActivityDto(
    val ownerId: String,
    val type: Int,
    val issuerId: String,
    val issuerName: String,
    val targetType: Int?,
    val targetId: String?,
    val timestamp: Long
)
