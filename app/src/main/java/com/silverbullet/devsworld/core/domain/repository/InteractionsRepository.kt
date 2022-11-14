package com.silverbullet.devsworld.core.domain.repository

import com.silverbullet.devsworld.core.domain.model.Like
import com.silverbullet.devsworld.core.util.Resource
import kotlinx.coroutines.flow.Flow

interface InteractionsRepository {

    suspend fun likePost(
        postId: String,
        failureCallback: suspend () -> Unit
    )

    suspend fun unlikePost(
        postId: String,
        failureCallback: suspend () -> Unit
    )

    suspend fun likeComment(
        commentId: String,
        failureCallback: suspend () -> Unit
    )

    suspend fun unlikeComment(
        commentId: String,
        failureCallback: suspend () -> Unit
    )

    suspend fun getLikes(parentId: String): Flow<Resource<List<Like>>>

    suspend fun follow(
        userId: String,
        failureCallback: suspend () -> Unit
    )

    suspend fun unfollow(
        userId: String,
        failureCallback: suspend () -> Unit
    )
}