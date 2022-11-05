package com.silverbullet.devsworld.feature_auth.presentation.login

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
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

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

    fun onEvent(loginScreenEvent: LoginScreenEvent) {
        when (loginScreenEvent) {

            is LoginScreenEvent.EmailFieldChanged -> {
                _emailInput.value = InputFieldState(loginScreenEvent.email)
            }

            is LoginScreenEvent.PasswordFieldChanged -> {
                _passwordInput.value = InputFieldState(loginScreenEvent.password)
            }

            is LoginScreenEvent.SetPasswordVisibility -> {
                _showPassword.value = loginScreenEvent.isVisible
            }

            LoginScreenEvent.Login -> login()

        }
    }

    private fun login() {
        if (!validateInputFields()) {
            return
        }
        viewModelScope.launch {
            repository
                .login(email, password)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            _isLoading.value = false
                            _uiEvents.emit(UiEvent.Navigate(Screen.MainFeedScreen.route))
                        }
                        is Resource.Error -> {
                            _isLoading.value = false
                            _uiEvents.emit(UiEvent.ShowToast(UiText.StringResource(R.string.wrong_credentials)))
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