package com.silverbullet.devsworld.feature_post.presentation.main_feed

import com.silverbullet.devsworld.core.domain.model.Post
import com.silverbullet.devsworld.core.util.UiText

data class MainFeedScreenState(
    val posts: List<Post> = emptyList(),
    val isLoading: Boolean = false,
    val error: UiText? = null
)
