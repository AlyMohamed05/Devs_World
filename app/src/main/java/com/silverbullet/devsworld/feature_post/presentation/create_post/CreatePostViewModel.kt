package com.silverbullet.devsworld.feature_post.presentation.create_post

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silverbullet.devsworld.R
import com.silverbullet.devsworld.core.util.Resource
import com.silverbullet.devsworld.core.util.UiText
import com.silverbullet.devsworld.feature_post.domain.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreatePostViewModel @Inject constructor(
    private val repository: PostRepository
) : ViewModel() {

    private var imageFileName: String? = null

    private val _croppedImageUri = mutableStateOf<Uri?>(null)
    val croppedImageUri: State<Uri?> = _croppedImageUri

    private val _descriptionText = mutableStateOf("")
    private val description: String
        get() = _descriptionText.value
    val descriptionText: State<String> = _descriptionText

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvents = _uiEvent.asSharedFlow()

    fun onEvent(event: CreatePostScreenEvent) {
        when (event) {
            is CreatePostScreenEvent.EditDescription ->
                _descriptionText.value = event.description

            is CreatePostScreenEvent.SetSelectedImageFileName ->
                imageFileName = event.filename

            is CreatePostScreenEvent.SetCropImageUri ->
                _croppedImageUri.value = event.uri

            CreatePostScreenEvent.Post -> createPost()

        }
    }

    private fun createPost() {
        if (_croppedImageUri.value == null) {
            viewModelScope.launch {
                _uiEvent.emit(UiEvent.ShowToast(UiText.StringResource(R.string.no_image)))
            }
            return
        }
        if (description.isBlank()) {
            viewModelScope.launch {
                _uiEvent.emit(UiEvent.ShowToast(UiText.StringResource(R.string.empty_description)))
            }
            return
        }
        viewModelScope.launch {
            repository.createPost(
                imageFileName,
                description,
                _croppedImageUri.value ?: return@launch
            ).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _uiEvent.emit(UiEvent.ShowToast(UiText.StringResource(R.string.created)))
                        _uiEvent.emit(UiEvent.NavigateUP)
                    }
                    is Resource.Error -> {
                        result.error?.let {
                            _uiEvent.emit(UiEvent.ShowToast(it))
                        }
                        _uiEvent.emit(UiEvent.NavigateUP)
                    }
                    is Resource.Loading -> {

                    }
                }
            }
        }
    }

    sealed class UiEvent {

        data class ShowToast(val message: UiText) : UiEvent()

        object NavigateUP : UiEvent()
    }
}