package com.silverbullet.devsworld.feature_post.presentation.post_detail

import com.silverbullet.devsworld.core.domain.model.Post
import com.silverbullet.devsworld.core.util.UiText

data class PostDetailScreenState(
    val post: Post? = null,
    val isLoading: Boolean = false,
    val error: UiText? = null
)
