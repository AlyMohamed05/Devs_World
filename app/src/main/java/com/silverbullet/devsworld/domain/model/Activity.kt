package com.silverbullet.devsworld.domain.model

import com.silverbullet.devsworld.domain.utils.ActivityAction

data class Activity(
    val username: String,
    val actionType: ActivityAction,
    val formattedTime: String
)