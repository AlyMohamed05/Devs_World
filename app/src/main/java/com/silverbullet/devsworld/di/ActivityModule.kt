package com.silverbullet.devsworld.di

import com.silverbullet.devsworld.feature_activity.data.remote.ActivityApi
import com.silverbullet.devsworld.feature_activity.data.repository.ActivityRepositoryImpl
import com.silverbullet.devsworld.feature_activity.domain.repository.ActivityRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ActivityModule {

    @Provides
    @Singleton
    fun provideActivityApi(client: OkHttpClient): ActivityApi{
        return Retrofit
            .Builder()
            .baseUrl(ActivityApi.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ActivityApi::class.java)

    }

    @Provides
    @Singleton
    fun provideActivityRepository(activityApi: ActivityApi): ActivityRepository{
        return ActivityRepositoryImpl(activityApi)
    }
}