package com.silverbullet.devsworld.feature_profile.data.mapper

import com.silverbullet.devsworld.core.domain.model.Post
import com.silverbullet.devsworld.feature_profile.data.remote.dto.response.PostDto

fun PostDto.toPost(): Post{
    return Post(
        imageUrl = imageUrl,
        description = description,
        timestamp =  timestamp,
        userId = userId,
        username = username,
        likesCount = likesCount,
        commentsCount = commentsCount
    )
}