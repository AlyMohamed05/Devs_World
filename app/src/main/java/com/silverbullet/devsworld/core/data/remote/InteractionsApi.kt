package com.silverbullet.devsworld.core.data.remote

import com.silverbullet.devsworld.core.data.remote.dto.request.FollowingRequest
import com.silverbullet.devsworld.core.data.remote.dto.request.LikeRequest
import com.silverbullet.devsworld.core.data.remote.dto.response.BasicApiResponse
import com.silverbullet.devsworld.core.domain.model.Like
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface InteractionsApi {

    @POST("api/likes/like")
    suspend fun like(
        @Body request: LikeRequest
    ): BasicApiResponse<Unit>

    @POST("api/likes/unlike")
    suspend fun unlike(
        @Body request: LikeRequest
    ): BasicApiResponse<Unit>

    @GET("api/likes")
    suspend fun fetchLikes(
        @Query("parentId") parentId: String
    ): BasicApiResponse<List<Like>>

    @POST("api/following/follow")
    suspend fun follow(
        @Body request: FollowingRequest
    ): BasicApiResponse<Unit>

    @POST("api/following/unfollow")
    suspend fun unfollow(
        @Body request: FollowingRequest
    ): BasicApiResponse<Unit>

    companion object {

        const val BASE_URL = "http://192.168.1.6:8080/"

    }
}