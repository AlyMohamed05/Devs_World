package com.silverbullet.devsworld.feature_profile.data.remote

import com.silverbullet.devsworld.core.data.remote.dto.response.BasicApiResponse
import com.silverbullet.devsworld.core.data.remote.dto.response.PostDto
import com.silverbullet.devsworld.core.data.remote.dto.response.ProfileDto
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Query

interface ProfileApi {

    @GET("api/user")
    suspend fun getUserProfile(
        @Query("userId") userId: String?
    ): BasicApiResponse<ProfileDto>

    @GET("api/post/all")
    suspend fun getPosts(
        @Query("userId") userId: String? = null,
        @Query("page") page: Int = 1,
        @Query("offset") offset: Int? = null
    ): BasicApiResponse<List<PostDto>>

    @Multipart
    @PUT("api/user/update")
    suspend fun updateUserProfile(
        @Part profileData: MultipartBody.Part,
        @Part profileImage: MultipartBody.Part?
    ): BasicApiResponse<Unit>

    companion object {

        const val BASE_URL = "http://192.168.1.6:8080/"

    }
}