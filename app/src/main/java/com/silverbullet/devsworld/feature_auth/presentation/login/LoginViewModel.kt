package com.silverbullet.devsworld.feature_auth.presentation.login

import androidx.lifecycle.viewModelScope
import com.silverbullet.devsworld.R
import com.silverbullet.devsworld.core.util.Resource
import com.silverbullet.devsworld.core.util.UiText
import com.silverbullet.devsworld.feature_auth.domain.repsitory.AuthRepository
import com.silverbullet.devsworld.feature_auth.presentation.AuthViewModel
import com.silverbullet.devsworld.feature_auth.presentation.InputFieldState
import com.silverbullet.devsworld.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
) : AuthViewModel() {

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

}