package com.silverbullet.devsworld.feature_profile.domain.repository

import com.silverbullet.devsworld.core.domain.model.Post
import com.silverbullet.devsworld.core.util.Resource
import com.silverbullet.devsworld.feature_profile.domain.model.Profile
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    suspend fun getProfile(userId: String?): Flow<Resource<Profile>>

    suspend fun getProfilePosts(
        userId: String?,
        page: Int = 1,
        offset: Int? = null
    ): Flow<Resource<List<Post>>>

}