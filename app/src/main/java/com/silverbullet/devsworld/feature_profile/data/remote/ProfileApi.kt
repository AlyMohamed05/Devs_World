package com.silverbullet.devsworld.feature_profile.data.remote

import com.silverbullet.devsworld.core.data.remote.dto.response.BasicApiResponse
import com.silverbullet.devsworld.feature_profile.data.remote.dto.response.PostDto
import com.silverbullet.devsworld.core.data.remote.dto.response.ProfileDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ProfileApi {

    @GET("api/user")
    suspend fun getUserProfile(
        @Query("userId") userId: String?
    ): BasicApiResponse<ProfileDto>

    @GET("api/post/all")
    suspend fun getPosts(
        @Query("userId") userId: String? =null,
        @Query("page") page: Int = 1,
        @Query("offset") offset: Int? = null
    ): BasicApiResponse<List<PostDto>>

    companion object {

        const val BASE_URL = "http://192.168.1.6:8080/"

    }
}