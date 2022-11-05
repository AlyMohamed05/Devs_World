package com.silverbullet.devsworld.core.presentation

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silverbullet.devsworld.core.util.SharedPrefKeys
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    var stopSplash = false

    fun hasTokenInPref(): Boolean {
        val token = sharedPreferences.getString(SharedPrefKeys.JWT_TOKEN, null)
        viewModelScope.launch {
            // Some delay to eliminate bottom app bar flashing
            delay(100L)
            stopSplash = true
        }
        return token != null
    }
}