package com.silverbullet.devsworld.feature_post.presentation.create_post

import android.net.Uri

sealed class CreatePostScreenEvent {

    data class EditDescription(val description: String) : CreatePostScreenEvent()

    class SetSelectedImageFileName(val filename: String?): CreatePostScreenEvent()

    class SetCropImageUri(val uri: Uri) : CreatePostScreenEvent()

    object Post: CreatePostScreenEvent()
}
