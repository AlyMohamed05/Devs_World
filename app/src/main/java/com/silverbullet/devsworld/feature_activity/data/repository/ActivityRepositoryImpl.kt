package com.silverbullet.devsworld.feature_activity.data.repository

import com.silverbullet.devsworld.R
import com.silverbullet.devsworld.core.util.Resource
import com.silverbullet.devsworld.core.util.UiText
import com.silverbullet.devsworld.feature_activity.data.mapper.toActivity
import com.silverbullet.devsworld.feature_activity.data.remote.ActivityApi
import com.silverbullet.devsworld.feature_activity.domain.model.Activity
import com.silverbullet.devsworld.feature_activity.domain.repository.ActivityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException


class ActivityRepositoryImpl(private val api: ActivityApi) : ActivityRepository {

    override suspend fun getActivities(): Flow<Resource<List<Activity>>> = flow {
        emit(Resource.Loading())
        try {
            val response = withContext(Dispatchers.IO) {
                api.fetchActivities()
            }
            if (response.successful && response.data != null) {
                val activities = response.data.map { it.toActivity() }
                emit(Resource.Success(activities))
            } else {
                emit(Resource.Error(error = UiText.StringResource(R.string.unexpected_error)))
            }
        } catch (e: IOException) {
            emit(Resource.Error(error = UiText.StringResource(R.string.cant_connect)))

        } catch (exception: HttpException) {
            emit(Resource.Error(error = UiText.StringResource(R.string.unexpected_error)))
        }
    }
}