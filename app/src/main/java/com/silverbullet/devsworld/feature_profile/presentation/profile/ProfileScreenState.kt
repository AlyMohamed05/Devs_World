package com.silverbullet.devsworld.feature_profile.presentation.profile

import com.silverbullet.devsworld.core.domain.model.Post
import com.silverbullet.devsworld.core.util.UiText
import com.silverbullet.devsworld.feature_profile.domain.model.Profile

data class ProfileScreenState(
    val isLoading: Boolean = false,
    val profile: Profile? = null,
    val posts: List<Post> = emptyList(),
    val error: UiText? = null
)
