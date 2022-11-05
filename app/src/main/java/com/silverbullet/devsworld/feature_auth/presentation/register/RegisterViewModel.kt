package com.silverbullet.devsworld.feature_auth.presentation.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silverbullet.devsworld.R
import com.silverbullet.devsworld.core.util.Resource
import com.silverbullet.devsworld.core.util.UiText
import com.silverbullet.devsworld.feature_auth.domain.repsitory.AuthRepository
import com.silverbullet.devsworld.feature_auth.presentation.InputFieldState
import com.silverbullet.devsworld.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _userInput = mutableStateOf(InputFieldState())
    private val username: String
        get() = _userInput.value.text
    val userInput: State<InputFieldState> = _userInput

    private val _emailInput = mutableStateOf(InputFieldState())
    private val email: String
        get() = _emailInput.value.text
    val emailInput: State<InputFieldState> = _emailInput

    private val _passwordInput = mutableStateOf(InputFieldState())
    private val password: String
        get() = _passwordInput.value.text
    val passwordInput: State<InputFieldState> = _passwordInput

    private val _showPassword = mutableStateOf(false)
    val showPassword: State<Boolean> = _showPassword

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _uiEvents = MutableSharedFlow<UiEvent>()
    val uiEvents = _uiEvents.asSharedFlow()


    fun onEvent(event: RegisterScreenEvent) {
        when (event) {
            is RegisterScreenEvent.UserNameFieldChanged -> {
                _userInput.value = InputFieldState(text = event.username)
            }
            is RegisterScreenEvent.EmailFieldChanged -> {
                _emailInput.value = InputFieldState(text = event.email)
            }
            is RegisterScreenEvent.PasswordFieldChanged -> {
                _passwordInput.value = InputFieldState(text = event.password)
            }
            is RegisterScreenEvent.SetPasswordVisibility -> {
                _showPassword.value = event.isVisible
            }
            RegisterScreenEvent.Register -> register()
        }
    }

    private fun register() {
        if (!validateInputFields()) {
            return
        }
        viewModelScope.launch {
            repository
                .register(
                    username = username,
                    email = email,
                    password = password
                )
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            _isLoading.value = false
                            _uiEvents.emit(UiEvent.ShowToast(UiText.StringResource(R.string.registered)))
                            delay(200)
                            _uiEvents.emit(UiEvent.Navigate(Screen.LoginScreen.route))
                        }
                        is Resource.Error -> {
                            _isLoading.value = false
                            _emailInput.value = _emailInput.value.copy(error = result.error)
                        }
                        is Resource.Loading -> {
                            _isLoading.value = true
                        }
                    }
                }
        }
    }

    /**
     * if fields are not valid it will automatically set error messages
     * @return true if fields are valid
     */
    private fun validateInputFields(): Boolean {
        var valid = true
        if (!_emailInput.value.validateAsEmail()) {
            _emailInput.value =
                _emailInput.value.copy(error = UiText.StringResource(R.string.email_not_valid))
            valid = false
        }
        val usernameValidationText = _userInput.value.validateAsUsername()
        if (usernameValidationText != null) {
            _userInput.value =
                _userInput.value.copy(error = UiText.DynamicString(usernameValidationText))
            valid = false
        }
        val passwordValidationText = _passwordInput.value.validateAsPassword()
        if (passwordValidationText != null) {
            _passwordInput.value =
                _passwordInput.value.copy(error = UiText.DynamicString(passwordValidationText))
            valid = false
        }
        return valid
    }

    sealed class UiEvent {

        class ShowToast(val message: UiText) : UiEvent()

        class Navigate(val route: String) : UiEvent()

    }
}