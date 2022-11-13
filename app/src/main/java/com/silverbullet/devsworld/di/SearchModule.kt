package com.silverbullet.devsworld.di

import com.silverbullet.devsworld.feature_search.data.remote.SearchApi
import com.silverbullet.devsworld.feature_search.data.repository.SearchRepositoryImpl
import com.silverbullet.devsworld.feature_search.domain.repository.SearchRepository
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
object SearchModule {

    @Provides
    @Singleton
    fun provideSearchApi(client: OkHttpClient): SearchApi{
        return Retrofit
            .Builder()
            .baseUrl(SearchApi.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SearchApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSearchRepository(searchApi: SearchApi): SearchRepository{
        return SearchRepositoryImpl(searchApi)
    }
}