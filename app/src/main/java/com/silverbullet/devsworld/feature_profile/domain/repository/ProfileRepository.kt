package com.silverbullet.devsworld.feature_profile.domain.repository

import android.net.Uri
import com.silverbullet.devsworld.core.domain.model.Post
import com.silverbullet.devsworld.core.domain.model.Profile
import com.silverbullet.devsworld.core.util.Resource
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    suspend fun getProfile(userId: String?): Flow<Resource<Profile>>

    suspend fun getProfilePosts(
        userId: String?,
        page: Int = 1,
        offset: Int? = null
    ): Flow<Resource<List<Post>>>

    suspend fun updateProfile(
        username: String,
        githubUrl: String,
        linkedinUrl: String,
        instagramUrl: String,
        bio: String,
        skills: List<String>,
        profileImageUri: Uri?
    ): Flow<Resource<Unit>>

}