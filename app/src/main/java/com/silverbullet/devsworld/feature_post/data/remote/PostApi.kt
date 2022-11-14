package com.silverbullet.devsworld.feature_post.data.remote

import com.silverbullet.devsworld.core.data.remote.dto.response.BasicApiResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface PostApi {

    @Multipart
    @POST("api/post/create")
    suspend fun createPost(
        @Part postData: MultipartBody.Part,
        @Part postImage: MultipartBody.Part
    ): BasicApiResponse<Unit>

    companion object {

        const val BASE_URL = "http://192.168.1.6:8080/"

    }
}