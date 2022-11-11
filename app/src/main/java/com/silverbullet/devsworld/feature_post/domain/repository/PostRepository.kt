package com.silverbullet.devsworld.feature_post.domain.repository

import android.net.Uri
import com.silverbullet.devsworld.core.util.Resource
import kotlinx.coroutines.flow.Flow

interface PostRepository {

    suspend fun createPost(
        filename: String?,
        description: String,
        filePath: Uri
    ): Flow<Resource<Unit>>
}