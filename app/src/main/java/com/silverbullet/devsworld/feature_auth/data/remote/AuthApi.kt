package com.silverbullet.devsworld.feature_auth.data.remote

import com.silverbullet.devsworld.core.data.remote.dto.response.BasicApiResponse
import com.silverbullet.devsworld.feature_auth.data.remote.dto.request.CreateAccountRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("api/user/create")
    suspend fun register(
        @Body request: CreateAccountRequest
    ): BasicApiResponse<Unit>

    companion object {

        const val BASE_URL = "http://192.168.1.2:8080/"

    }
}