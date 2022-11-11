package com.silverbullet.devsworld.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.google.gson.Gson
import com.silverbullet.devsworld.core.util.SharedPrefKeys
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideSharedPref(@ApplicationContext app: Context): SharedPreferences {
        return app
            .getSharedPreferences(
                SharedPrefKeys.SHARED_PREF_NAME,
                MODE_PRIVATE
            )
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(sharedPref: SharedPreferences): OkHttpClient {
        val token = sharedPref.getString(SharedPrefKeys.JWT_TOKEN, null) ?: ""
        if (token.isBlank()) {
            Timber.w("Token is blank")
        }
        return OkHttpClient
            .Builder()
            .addInterceptor {
                val modifiedRequest = it
                    .request()
                    .newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                it.proceed(modifiedRequest)
            }
            .build()
    }

}