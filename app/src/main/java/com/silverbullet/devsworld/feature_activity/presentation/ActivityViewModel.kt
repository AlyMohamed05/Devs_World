package com.silverbullet.devsworld.feature_activity.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silverbullet.devsworld.core.util.Resource
import com.silverbullet.devsworld.feature_activity.domain.repository.ActivityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val repository: ActivityRepository
) : ViewModel() {

    private val _state = mutableStateOf(ActivityScreenState())
    val state: State<ActivityScreenState> = _state

    init {
        viewModelScope.launch {
            repository
                .getActivities()
                .collect { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            _state.value = _state.value.copy(
                                isLoading = false,
                                activities = resource.data
                            )
                        }
                        is Resource.Error ->
                            _state.value = _state.value.copy(isLoading = false)
                        is Resource.Loading ->
                            _state.value = _state.value.copy(isLoading = true)
                    }
                }
        }
    }
}