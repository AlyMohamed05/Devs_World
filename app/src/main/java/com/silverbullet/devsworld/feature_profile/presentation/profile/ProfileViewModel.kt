package com.silverbullet.devsworld.feature_profile.presentation.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silverbullet.devsworld.core.util.Resource
import com.silverbullet.devsworld.feature_profile.domain.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository
) : ViewModel() {

    private var userId: String? = null

    private val _state = mutableStateOf(ProfileScreenState())
    val state: State<ProfileScreenState> = _state

    private var page = 1

    init {
        // TODO: Assign the userId from navArguments to enable loading any profile using this screen.
        viewModelScope.launch {
            launch { loadProfileInfo() }
            launch { loadProfilePosts() }
        }
    }

    private suspend fun loadProfileInfo() {
        repository
            .getProfile(userId)
            .collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            profile = resource.data
                        )
                    }
                    is Resource.Error -> {
                        // TODO: Handle Error messages
                        _state.value = _state.value.copy(isLoading = false)
                    }
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isLoading = true)
                    }
                }
            }

    }

    private suspend fun loadProfilePosts() {
        repository
            .getProfilePosts(userId = userId, page = page)
            .collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        val newPostsList = buildList {
                            addAll(_state.value.posts)
                            addAll(resource.data)
                        }
                        _state.value = _state.value.copy(posts = newPostsList)
                        page++
                    }
                    is Resource.Error -> Unit
                    is Resource.Loading -> Unit
                }
            }
    }
}