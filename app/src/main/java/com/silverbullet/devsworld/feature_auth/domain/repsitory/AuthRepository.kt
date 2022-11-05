package com.silverbullet.devsworld.feature_auth.domain.repsitory

import com.silverbullet.devsworld.core.util.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun register(
        username: String,
        email: String,
        password: String
    ): Flow<Resource<Unit>>

}