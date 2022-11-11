package com.silverbullet.devsworld.di

import com.google.gson.Gson
import com.silverbullet.devsworld.feature_post.data.remote.PostApi
import com.silverbullet.devsworld.feature_post.data.repository.PostRepositoryImpl
import com.silverbullet.devsworld.feature_post.domain.repository.PostRepository
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
object PostModule {

    @Provides
    @Singleton
    fun providePostApi(client: OkHttpClient): PostApi {
        return Retrofit
            .Builder()
            .baseUrl(PostApi.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostApi::class.java)
    }

    @Provides
    @Singleton
    fun providePostRepository(
        postApi: PostApi,
        gson: Gson
    ): PostRepository {
        return PostRepositoryImpl(postApi, gson)
    }
}