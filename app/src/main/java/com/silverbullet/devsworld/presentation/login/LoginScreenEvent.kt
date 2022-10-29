package com.silverbullet.devsworld.presentation.login

sealed class LoginScreenEvent{

    class UserNameFieldChanged(val username: String): LoginScreenEvent()

    class PasswordFieldChanged(val password: String): LoginScreenEvent()

    class SetPasswordVisibility(val isVisible: Boolean): LoginScreenEvent()

}
