package com.silverbullet.devsworld.feature_post.presentation.post_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silverbullet.devsworld.core.util.Resource
import com.silverbullet.devsworld.feature_post.domain.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostDetailViewModel @Inject constructor(
    private val postRepository: PostRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val postId: String = checkNotNull(savedStateHandle["postId"])

    private val _state = mutableStateOf(PostDetailScreenState())
    val state: State<PostDetailScreenState> = _state

    init {
        viewModelScope.launch {
            loadPostDetails()
        }
    }

    private suspend fun loadPostDetails() {
        postRepository
            .getPost(postId)
            .collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            post = resource.data,
                            isLoading = false
                        )
                    }
                    is Resource.Error -> {
                        _state.value = _state.value.copy(isLoading = false)
                    }
                    is Resource.Loading ->
                        _state.value = _state.value.copy(isLoading = true)
                }
            }
    }
}