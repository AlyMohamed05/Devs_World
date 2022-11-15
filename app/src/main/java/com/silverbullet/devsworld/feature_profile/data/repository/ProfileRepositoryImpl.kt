package com.silverbullet.devsworld.feature_profile.data.repository

import android.net.Uri
import androidx.core.net.toFile
import com.google.gson.Gson
import com.silverbullet.devsworld.R
import com.silverbullet.devsworld.core.domain.model.Post
import com.silverbullet.devsworld.core.domain.model.Profile
import com.silverbullet.devsworld.core.util.Resource
import com.silverbullet.devsworld.core.util.UiText
import com.silverbullet.devsworld.feature_profile.data.mapper.toPost
import com.silverbullet.devsworld.feature_profile.data.mapper.toProfile
import com.silverbullet.devsworld.feature_profile.data.remote.ProfileApi
import com.silverbullet.devsworld.feature_profile.data.remote.dto.request.UpdateUserRequest
import com.silverbullet.devsworld.feature_profile.domain.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException
import java.io.IOException

class ProfileRepositoryImpl(
    private val api: ProfileApi,
    private val gson: Gson
) : ProfileRepository {

    override suspend fun getProfile(userId: String?): Flow<Resource<Profile>> = flow {
        emit(Resource.Loading())
        try {
            val response =
                withContext(Dispatchers.IO) {
                    api.getUserProfile(userId)
                }
            if (response.successful && response.data != null) {
                emit(Resource.Success(response.data.toProfile()))
            } else {
                emit(Resource.Error(error = UiText.StringResource(R.string.unexpected_error)))
            }
        } catch (e: IOException) {
            emit(Resource.Error(error = UiText.StringResource(R.string.cant_connect)))
        } catch (e: HttpException) {
            if (e.code() == 404) {
                emit(Resource.Error(error = UiText.StringResource(R.string.not_found)))
            } else {
                emit(Resource.Error(error = UiText.StringResource(R.string.unexpected_error)))
            }
        }
    }

    override suspend fun getProfilePosts(
        userId: String?,
        page: Int,
        offset: Int?
    ): Flow<Resource<List<Post>>> = flow {
        emit(Resource.Loading())
        try {
            val response = withContext(Dispatchers.IO) {
                api.getPosts(
                    userId = userId,
                    page = page,
                    offset = offset
                )
            }
            if (response.successful && response.data != null) {
                val posts = response.data.map { it.toPost() }
                emit(Resource.Success(data = posts))
            } else {
                emit(Resource.Error(error = UiText.DynamicString(response.message ?: "UnExcepted")))
            }
        } catch (e: IOException) {
            emit(Resource.Error(error = UiText.StringResource(R.string.cant_connect)))
        } catch (exception: HttpException) {
            emit(Resource.Error(error = UiText.StringResource(R.string.unexpected_error)))

        }
    }

    override suspend fun updateProfile(
        username: String,
        githubUrl: String,
        linkedinUrl: String,
        instagramUrl: String,
        bio: String,
        skills: List<String>,
        profileImageUri: Uri?
    ): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            val imageFile = profileImageUri?.toFile()
            val updateRequest = UpdateUserRequest(
                username = username,
                githubUrl = githubUrl,
                linkedinUrl = linkedinUrl,
                instagramUrl = instagramUrl,
                bio = bio,
                skills = skills
            )
            val profileUpdatePart = MultipartBody.Part.createFormData(
                "updated_user",
                gson.toJson(updateRequest)
            )
            val profileImage = imageFile?.let {
                MultipartBody.Part.createFormData(
                    "",
                    "profileImage.jpg",
                    body = imageFile.asRequestBody()
                )
            }
            val response = api.updateUserProfile(profileUpdatePart, profileImage)
            if (response.successful) {
                emit(Resource.Success(Unit))
            } else {
                emit(Resource.Error(error = UiText.StringResource(R.string.unexpected_error)))
            }
        } catch (exception: IOException) {
            emit(Resource.Error(error = UiText.StringResource(R.string.cant_connect)))
        } catch (exception: HttpException) {
            emit(Resource.Error(error = UiText.StringResource(R.string.unexpected_error)))
        }
    }
}