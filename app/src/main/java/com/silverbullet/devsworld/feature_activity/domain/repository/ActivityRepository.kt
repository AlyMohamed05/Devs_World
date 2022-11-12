package com.silverbullet.devsworld.feature_activity.domain.repository

import com.silverbullet.devsworld.core.util.Resource
import com.silverbullet.devsworld.feature_activity.domain.model.Activity
import kotlinx.coroutines.flow.Flow

interface ActivityRepository {

    suspend fun getActivities(): Flow<Resource<List<Activity>>>
}