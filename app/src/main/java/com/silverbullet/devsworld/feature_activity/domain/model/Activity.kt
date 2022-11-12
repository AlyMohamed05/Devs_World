package com.silverbullet.devsworld.feature_activity.domain.model

import com.silverbullet.devsworld.feature_activity.data.util.ActivityAction
import com.silverbullet.devsworld.feature_activity.data.util.ActivityTargetType


data class Activity(
    val ownerId: String,
    val type: ActivityAction,
    val issuerId: String,
    val issuerName: String,
    val targetType: ActivityTargetType?,
    val targetId: String?,
    val timestamp: String
)