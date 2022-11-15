package com.silverbullet.devsworld.di

import com.google.gson.Gson
import com.silverbullet.devsworld.feature_profile.data.remote.ProfileApi
import com.silverbullet.devsworld.feature_profile.data.repository.ProfileRepositoryImpl
import com.silverbullet.devsworld.feature_profile.domain.repository.ProfileRepository
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
class ProfileModule {

    @Provides
    @Singleton
    fun provideProfileApi(client: OkHttpClient): ProfileApi {
        return Retrofit
            .Builder()
            .baseUrl(ProfileApi.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProfileApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProfileRepository(profileApi: ProfileApi,gson: Gson): ProfileRepository {
        return ProfileRepositoryImpl(profileApi,gson)
    }
}