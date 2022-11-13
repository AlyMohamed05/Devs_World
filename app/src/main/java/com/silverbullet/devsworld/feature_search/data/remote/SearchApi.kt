package com.silverbullet.devsworld.feature_search.data.remote

import com.silverbullet.devsworld.core.data.remote.dto.response.BasicApiResponse
import com.silverbullet.devsworld.core.data.remote.dto.response.ProfileDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("api/search/user")
    suspend fun userSearch(
        @Query("query") query: String
    ): BasicApiResponse<List<ProfileDto>>

    companion object {

        const val BASE_URL = "http://192.168.1.4:8080/"

    }
}