package com.silverbullet.devsworld.feature_activity.domain.model

import com.silverbullet.devsworld.feature_activity.domain.ActivityAction

data class Activity(
    val username: String,
    val actionType: ActivityAction,
    val formattedTime: String
)