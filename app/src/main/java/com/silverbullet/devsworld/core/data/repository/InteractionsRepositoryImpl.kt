package com.silverbullet.devsworld.core.data.repository

import com.silverbullet.devsworld.R
import com.silverbullet.devsworld.core.data.remote.InteractionsApi
import com.silverbullet.devsworld.core.data.remote.dto.request.FollowingRequest
import com.silverbullet.devsworld.core.data.remote.dto.request.LikeRequest
import com.silverbullet.devsworld.core.data.util.LikeParentType
import com.silverbullet.devsworld.core.domain.model.Like
import com.silverbullet.devsworld.core.domain.repository.InteractionsRepository
import com.silverbullet.devsworld.core.util.Resource
import com.silverbullet.devsworld.core.util.UiText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class InteractionsRepositoryImpl(private val api: InteractionsApi) : InteractionsRepository {

    override suspend fun likePost(postId: String, failureCallback: suspend () -> Unit) {
        val request = LikeRequest(
            likedParentId = postId,
            parentType = LikeParentType.Post.type
        )
        try {
            val response = withContext(Dispatchers.IO) { api.like(request) }
            if (!response.successful) {
                failureCallback()
            }
        } catch (e: Exception) {
            failureCallback()
        }
    }

    override suspend fun unlikePost(postId: String, failureCallback: suspend () -> Unit) {
        val request = LikeRequest(
            likedParentId = postId,
            parentType = LikeParentType.Post.type
        )
        try {
            val response = withContext(Dispatchers.IO) { api.unlike(request) }
            if (!response.successful) {
                failureCallback()
            }
        } catch (e: Exception) {
            failureCallback()
        }
    }

    override suspend fun likeComment(commentId: String, failureCallback: suspend () -> Unit) {
        val request = LikeRequest(
            likedParentId = commentId,
            parentType = LikeParentType.Comment.type
        )
        try {
            val response = withContext(Dispatchers.IO) { api.like(request) }
            if (!response.successful) {
                failureCallback()
            }
        } catch (e: Exception) {
            failureCallback()
        }
    }

    override suspend fun unlikeComment(commentId: String, failureCallback: suspend () -> Unit) {
        val request = LikeRequest(
            likedParentId = commentId,
            parentType = LikeParentType.Comment.type
        )
        try {
            val response = withContext(Dispatchers.IO) { api.unlike(request) }
            if (!response.successful) {
                failureCallback()
            }
        } catch (e: Exception) {
            failureCallback()
        }
    }

    override suspend fun getLikes(parentId: String): Flow<Resource<List<Like>>> = flow {
        emit(Resource.Loading())
        try {
            val response = withContext(Dispatchers.IO) {
                api.fetchLikes(parentId)
            }
            if (response.successful && response.data != null) {
                emit(Resource.Success(response.data))
            } else {
                emit(Resource.Error(error = null))
            }
        } catch (e: Exception) {
            emit(Resource.Error(error = UiText.StringResource(R.string.unexpected_error)))
        }
    }

    override suspend fun follow(userId: String, failureCallback: suspend () -> Unit) {
        val request = FollowingRequest(followedUserId = userId)
        try{
            val response = withContext(Dispatchers.IO){api.follow(request)}
            if(!response.successful){
                failureCallback()
            }
        }catch (e: Exception){
            failureCallback()
        }
    }

    override suspend fun unfollow(userId: String, failureCallback: suspend () -> Unit) {
        val request = FollowingRequest(followedUserId = userId)
        try{
            val response = withContext(Dispatchers.IO){api.unfollow(request)}
            if(!response.successful){
                failureCallback()
            }
        }catch (e: Exception){
            failureCallback()
        }
    }
}