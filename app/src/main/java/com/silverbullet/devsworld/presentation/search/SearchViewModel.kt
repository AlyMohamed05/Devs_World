package com.silverbullet.devsworld.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel() {

    private val _searchText = mutableStateOf("")
    val searchText: State<String> = _searchText

    fun setSearchText(query: String) {
        _searchText.value = query
    }
}