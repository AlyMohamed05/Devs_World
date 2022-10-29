package com.silverbullet.devsworld.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _usernameText = mutableStateOf("")
    val usernameText: State<String> = _usernameText

    private val _passwordText = mutableStateOf("")
    val passwordText: State<String> = _passwordText

    private val _showPassword = mutableStateOf(false)
    val showPassword: State<Boolean> = _showPassword

    private val _usernameError = mutableStateOf<String?>(null)
    val usernameError: State<String?> = _usernameError

    private val _passwordError = mutableStateOf<String?>(null)
    val passwordError: State<String?> = _passwordError

    fun setPasswordVisibility(showPassword: Boolean) {
        _showPassword.value = showPassword
    }

    fun onEvent(loginScreenEvent: LoginScreenEvent) {
        when (loginScreenEvent) {

            is LoginScreenEvent.UserNameFieldChanged -> {
                _usernameText.value = loginScreenEvent.username
            }

            is LoginScreenEvent.PasswordFieldChanged -> {
                _passwordText.value = loginScreenEvent.password
            }

        }
    }

}