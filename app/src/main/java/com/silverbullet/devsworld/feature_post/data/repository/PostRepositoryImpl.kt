package com.silverbullet.devsworld.feature_post.data.repository

import android.net.Uri
import androidx.core.net.toFile
import com.google.gson.Gson
import com.silverbullet.devsworld.R
import com.silverbullet.devsworld.core.util.Resource
import com.silverbullet.devsworld.core.util.UiText
import com.silverbullet.devsworld.feature_post.data.remote.PostApi
import com.silverbullet.devsworld.feature_post.data.remote.dto.request.CreatePostRequest
import com.silverbullet.devsworld.feature_post.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

class PostRepositoryImpl(
    private val api: PostApi,
    private val gson: Gson
) : PostRepository {

    override suspend fun createPost(
        filename: String?,
        description: String,
        filePath: Uri
    ): Flow<Resource<Unit>> =
        flow {
            emit(Resource.Loading())
            try {
                val imageFile = filePath.toFile()
                val createPostRequest = CreatePostRequest(description)
                val postData = MultipartBody.Part.createFormData(
                    "",
                    gson.toJson(createPostRequest)
                )
                val image = MultipartBody.Part.createFormData(
                    name = "",
                    filename = filename ?: "",
                    body = imageFile.asRequestBody()
                )
                val response = api.createPost(postData, image)
                if (response.successful) {
                    emit(Resource.Success(Unit))
                } else {
                    Timber.w(response.message)
                    emit(Resource.Error(error = UiText.StringResource(R.string.unexpected_error)))
                }
            } catch (exception: IOException) {
                emit(Resource.Error(error = UiText.StringResource(R.string.cant_connect)))
            } catch (exception: HttpException) {
                emit(Resource.Error(error = UiText.StringResource(R.string.unexpected_error)))
            }
        }
}