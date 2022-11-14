package com.silverbullet.devsworld.di

import com.silverbullet.devsworld.core.data.remote.InteractionsApi
import com.silverbullet.devsworld.core.data.repository.InteractionsRepositoryImpl
import com.silverbullet.devsworld.core.domain.repository.InteractionsRepository
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
object InteractionsModule {

    @Provides
    @Singleton
    fun provideInteractionsApi(client: OkHttpClient): InteractionsApi{
        return Retrofit
            .Builder()
            .baseUrl(InteractionsApi.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(InteractionsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideInteractionsRepository(interactionsApi: InteractionsApi):  InteractionsRepository{
        return InteractionsRepositoryImpl(interactionsApi)
    }
}