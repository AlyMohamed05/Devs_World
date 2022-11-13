package com.silverbullet.devsworld.feature_search.domain.repository

import com.silverbullet.devsworld.core.domain.model.Profile
import com.silverbullet.devsworld.core.util.Resource
import kotlinx.coroutines.flow.Flow


interface SearchRepository {

    suspend fun userSearch(query: String): Flow<Resource<List<Profile>>>
}