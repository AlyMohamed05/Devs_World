package com.silverbullet.devsworld.feature_search.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silverbullet.devsworld.core.domain.model.Profile
import com.silverbullet.devsworld.core.util.Resource
import com.silverbullet.devsworld.feature_search.domain.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository
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

    private fun search() {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500)
            repository
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
}