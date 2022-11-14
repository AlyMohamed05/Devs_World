package com.silverbullet.devsworld.feature_post.data.remote

import com.silverbullet.devsworld.core.data.remote.dto.response.BasicApiResponse
import com.silverbullet.devsworld.core.data.remote.dto.response.PostDto
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface PostApi {

    @Multipart
    @POST("api/post/create")
    suspend fun createPost(
        @Part postData: MultipartBody.Part,
        @Part postImage: MultipartBody.Part
    ): BasicApiResponse<Unit>

    @GET("api/post/feed")
    suspend fun fetchFeed(
        @Query("page") page: Int = 1,
        @Query("offset") offset: Int? = null
    ): BasicApiResponse<List<PostDto>>

    @GET("api/post")
    suspend fun fetchPost(@Query("postId") postId: String): BasicApiResponse<PostDto>

    companion object {

        const val BASE_URL = "http://192.168.1.6:8080/"

    }
}