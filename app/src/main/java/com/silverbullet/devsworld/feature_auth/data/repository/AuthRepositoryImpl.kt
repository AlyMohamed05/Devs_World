package com.silverbullet.devsworld.feature_auth.data.repository

import android.content.SharedPreferences
import com.silverbullet.devsworld.R
import com.silverbullet.devsworld.core.util.Resource
import com.silverbullet.devsworld.core.util.SharedPrefKeys
import com.silverbullet.devsworld.core.util.UiText
import com.silverbullet.devsworld.feature_auth.data.remote.AuthApi
import com.silverbullet.devsworld.feature_auth.data.remote.dto.request.CreateAccountRequest
import com.silverbullet.devsworld.feature_auth.data.remote.dto.request.LoginRequest
import com.silverbullet.devsworld.feature_auth.domain.repsitory.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi,
    private val sharedPref: SharedPreferences
) : AuthRepository {

    override suspend fun register(
        username: String,
        email: String,
        password: String
    ): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        val request = CreateAccountRequest(
            username = username,
            email = email,
            password = password
        )
        try {
            val response = api.register(request)
            if (response.successful) {
                emit(Resource.Success(Unit))
            } else {
                emit(
                    Resource.Error(error = UiText.DynamicString(response.message ?: ""))
                )
            }
        } catch (exception: IOException) {
            // TODO: Emit network error
            emit(Resource.Error(error = null))
        } catch (exception: HttpException) {
            emit(Resource.Error(error = UiText.StringResource(R.string.email_already_used)))
        }
    }

    override suspend fun login(
        email: String,
        password: String
    ): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        val request = LoginRequest(email, password)
        try {
            val response = api.login(request)
            if (response.successful) {
                val token = response.data?.token
                if (token != null) {
                    emit(Resource.Success(Unit))
                    sharedPref
                        .edit()
                        .putString(SharedPrefKeys.JWT_TOKEN, token)
                        .apply()
                } else {
                    emit(Resource.Error(error = UiText.DynamicString(response.message ?: "")))
                }
            } else {
                emit(Resource.Error(error = UiText.DynamicString(response.message ?: "")))
            }
        } catch (exception: IOException) {
            // TODO: Emit network error
            emit(Resource.Error(error = null))
        } catch (exception: HttpException) {
            emit(Resource.Error(error = null))
        }
    }
}