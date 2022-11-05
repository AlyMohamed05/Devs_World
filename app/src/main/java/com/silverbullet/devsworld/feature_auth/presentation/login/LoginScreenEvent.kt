package com.silverbullet.devsworld.feature_auth.presentation.login

sealed class LoginScreenEvent{

    class EmailFieldChanged(val email: String): LoginScreenEvent()

    class PasswordFieldChanged(val password: String): LoginScreenEvent()

    class SetPasswordVisibility(val isVisible: Boolean): LoginScreenEvent()

    object Login: LoginScreenEvent()

}
