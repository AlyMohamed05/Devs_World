package com.silverbullet.devsworld.feature_profile.data.mapper

import com.silverbullet.devsworld.core.domain.model.Post
import com.silverbullet.devsworld.core.data.remote.dto.response.PostDto

fun PostDto.toPost(): Post{
    return Post(
        id = id,
        imageUrl = imageUrl,
        description = description,
        timestamp =  timestamp,
        userId = userId,
        username = username,
        profileImageUrl = profileImageUrl,
        isLiked = isLiked,
        likesCount = likesCount,
        commentsCount = commentsCount
    )
}