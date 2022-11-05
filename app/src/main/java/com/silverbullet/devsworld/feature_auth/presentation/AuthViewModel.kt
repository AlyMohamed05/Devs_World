package com.silverbullet.devsworld.feature_auth.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.silverbullet.devsworld.core.util.UiText
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

abstract class AuthViewModel : ViewModel() {

    protected val _emailInput = mutableStateOf(InputFieldState())
    protected val email: String
        get() = _emailInput.value.text
    val emailInput: State<InputFieldState> = _emailInput

    protected val _passwordInput = mutableStateOf(InputFieldState())
    protected val password: String
        get() = _passwordInput.value.text
    val passwordInput: State<InputFieldState> = _passwordInput

    protected val _showPassword = mutableStateOf(false)
    val showPassword: State<Boolean> = _showPassword

    protected val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    protected val _uiEvents = MutableSharedFlow<UiEvent>()
    val uiEvents = _uiEvents.asSharedFlow()

    sealed class UiEvent {

        class ShowToast(val message: UiText) : UiEvent()

        class Navigate(val route: String) : UiEvent()

    }
}