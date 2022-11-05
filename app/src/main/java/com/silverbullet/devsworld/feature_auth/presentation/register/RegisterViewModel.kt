package com.silverbullet.devsworld.feature_auth.presentation.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(

) : ViewModel() {

    private val _usernameText = mutableStateOf("")
    val usernameText: State<String> = _usernameText

    private val _emailText = mutableStateOf("")
    val emailText: State<String> = _emailText

    private val _passwordText = mutableStateOf("")
    val passwordText: State<String> = _passwordText

    private val _showPassword = mutableStateOf(false)
    val showPassword: State<Boolean> = _showPassword

    private val _usernameError = mutableStateOf<String?>(null)
    val usernameError: State<String?> = _usernameError

    private val _passwordError = mutableStateOf<String?>(null)
    val passwordError: State<String?> = _passwordError

    fun onEvent(event: RegisterScreenEvent) {
        when (event) {
            is RegisterScreenEvent.UserNameFieldChanged -> {
                _usernameText.value = event.username
            }
            is RegisterScreenEvent.EmailFieldChanged -> {
                _emailText.value = event.email
            }
            is RegisterScreenEvent.PasswordFieldChanged -> {
                _passwordText.value = event.password
            }
            is RegisterScreenEvent.SetPasswordVisibility -> {
                _showPassword.value = event.isVisible
            }
        }
    }
}