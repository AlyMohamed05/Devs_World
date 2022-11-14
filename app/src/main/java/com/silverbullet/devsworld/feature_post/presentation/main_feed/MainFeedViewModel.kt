package com.silverbullet.devsworld.feature_post.presentation.main_feed

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silverbullet.devsworld.core.domain.repository.InteractionsRepository
import com.silverbullet.devsworld.core.util.Resource
import com.silverbullet.devsworld.feature_post.domain.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainFeedViewModel @Inject constructor(
    private val postsRepository: PostRepository,
    private val interactionsRepository: InteractionsRepository
) : ViewModel() {

    private val _state = mutableStateOf(MainFeedScreenState())
    val state: State<MainFeedScreenState> = _state

    private var page = 1

    init {
        viewModelScope.launch {
            loadFeed()
        }
    }

    fun likePost(postId: String, like: Boolean) {
        viewModelScope.launch {
            if (like) {
                launch { modifyPostIsLikedStatus(postId, true) }
                interactionsRepository
                    .likePost(
                        postId,
                        failureCallback = {
                            modifyPostIsLikedStatus(postId, false)
                        }
                    )
            } else {
                // Remove like
                launch { modifyPostIsLikedStatus(postId, false) }
                interactionsRepository
                    .unlikePost(
                        postId,
                        failureCallback = {
                            modifyPostIsLikedStatus(postId, true)
                        }
                    )
            }
        }
    }

    private suspend fun loadFeed() {
        postsRepository
            .getFeed(page = page)
            .collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        val newPostsList = buildList {
                            addAll(_state.value.posts)
                            addAll(resource.data)
                        }
                        _state.value = _state.value.copy(posts = newPostsList, isLoading = false)
                        page++
                    }
                    is Resource.Error -> {
                        _state.value = _state.value.copy(isLoading = false)
                    }
                    is Resource.Loading ->
                        _state.value = _state.value.copy(isLoading = true)
                }
            }
    }

    private suspend fun modifyPostIsLikedStatus(postId: String, likeStatus: Boolean) {
        val newPostsList = withContext(Dispatchers.Default) {
            _state.value.posts.map { post ->
                if (post.id == postId) {
                    val likesCount = if (likeStatus) post.likesCount + 1 else post.likesCount - 1
                    post.copy(isLiked = likeStatus, likesCount = likesCount)
                } else {
                    post
                }
            }
        }
        _state.value = _state.value.copy(posts = newPostsList)
    }
}