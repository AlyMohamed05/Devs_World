package com.silverbullet.devsworld.feature_activity.presentation

import com.silverbullet.devsworld.core.util.UiText
import com.silverbullet.devsworld.feature_activity.domain.model.Activity

data class ActivityScreenState(
    val activities: List<Activity> = emptyList(),
    val isLoading: Boolean = false,
    val error: UiText? = null
)
