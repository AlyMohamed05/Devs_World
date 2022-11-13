package com.silverbullet.devsworld.feature_search.data.repository

import com.silverbullet.devsworld.R
import com.silverbullet.devsworld.core.domain.model.Profile
import com.silverbullet.devsworld.core.util.Resource
import com.silverbullet.devsworld.core.util.UiText
import com.silverbullet.devsworld.feature_profile.data.mapper.toProfile
import com.silverbullet.devsworld.feature_search.data.remote.SearchApi
import com.silverbullet.devsworld.feature_search.domain.repository.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class SearchRepositoryImpl(private val api: SearchApi) : SearchRepository {

    override suspend fun userSearch(query: String): Flow<Resource<List<Profile>>> = flow {
        emit(Resource.Loading())
        try {
            val response = withContext(Dispatchers.IO) {
                api.userSearch(query)
            }
            if (response.successful && response.data != null) {
                val profiles = response.data.map { it.toProfile() }
                emit(Resource.Success(profiles))
            } else {
                emit(Resource.Error(error = UiText.DynamicString(response.message ?: "UnExpected")))
            }
        } catch (e: Exception) {
            emit(Resource.Error(error = UiText.StringResource(R.string.unexpected_error)))
        }
    }
}