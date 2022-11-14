package com.silverbullet.devsworld.feature_search.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silverbullet.devsworld.core.domain.model.Profile
import com.silverbullet.devsworld.core.domain.repository.InteractionsRepository
import com.silverbullet.devsworld.core.util.Resource
import com.silverbullet.devsworld.feature_search.domain.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository,
    private val interactionsRepository: InteractionsRepository
) : ViewModel() {

    private val _searchText = mutableStateOf("")
    private val query: String
        get() = _searchText.value
    val searchText: State<String> = _searchText

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _profilesList = mutableStateOf<List<Profile>>(emptyList())
    val profileList: State<List<Profile>> = _profilesList

    private var searchJob: Job? = null

    fun setSearchText(query: String) {
        _searchText.value = query
        search()
    }

    fun sendFollowingRequest(userId: String, followingStatus: Boolean) {
        viewModelScope.launch {
            launch { modifyIsFollowedStatus(userId, followingStatus) }
            if (followingStatus) {
                interactionsRepository
                    .follow(
                        userId,
                        failureCallback = {
                            modifyIsFollowedStatus(userId, false)
                        }
                    )
            } else {
                // unfollow
                interactionsRepository
                    .unfollow(
                        userId,
                        failureCallback = {
                            modifyIsFollowedStatus(userId, true)
                        }
                    )
            }
        }
    }

    private fun search() {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500)
            searchRepository
                .userSearch(query)
                .collect { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            _isLoading.value = false
                            _profilesList.value = resource.data
                        }
                        is Resource.Error ->
                            _isLoading.value = false
                        is Resource.Loading -> _isLoading.value = true
                    }
                }
        }
    }

    private suspend fun modifyIsFollowedStatus(profileId: String, isFollowed: Boolean) {
        val newProfileList =
            withContext(Dispatchers.Default) {
                _profilesList.value.map { profile ->
                    if (profile.id == profileId)
                        profile.copy(isFollowed = isFollowed)
                    else
                        profile
                }
            }
        _profilesList.value = newProfileList
    }
}