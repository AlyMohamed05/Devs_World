package com.silverbullet.devsworld.feature_post.presentation.create_post

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreatePostViewModel @Inject constructor(): ViewModel() {

    private val _descriptionText = mutableStateOf("")
    val descriptionText: State<String> = _descriptionText

    fun setDescriptionText(description: String){
        _descriptionText.value = description
    }
}