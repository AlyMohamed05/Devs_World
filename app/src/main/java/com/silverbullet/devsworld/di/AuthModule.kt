package com.silverbullet.devsworld.di

import android.content.SharedPreferences
import com.silverbullet.devsworld.feature_auth.data.remote.AuthApi
import com.silverbullet.devsworld.feature_auth.data.repository.AuthRepositoryImpl
import com.silverbullet.devsworld.feature_auth.domain.repsitory.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideAuthApi(): AuthApi {
        return Retrofit
            .Builder()
            .baseUrl(AuthApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        authApi: AuthApi,
        sharedPreferences: SharedPreferences
    ): AuthRepository {
        return AuthRepositoryImpl(authApi, sharedPreferences)
    }
}