package com.silverbullet.devsworld.feature_activity.data.remote

import com.silverbullet.devsworld.core.data.remote.dto.response.BasicApiResponse
import com.silverbullet.devsworld.feature_activity.data.remote.dto.ActivityDto
import retrofit2.http.GET

interface ActivityApi {

    @GET("api/activity")
    suspend fun fetchActivities(): BasicApiResponse<List<ActivityDto>>

    companion object {

        const val BASE_URL = "http://192.168.1.6:8080/"

    }
}