package com.silverbullet.devsworld.presentation.register

sealed class RegisterScreenEvent {

    class UserNameFieldChanged(val username: String) : RegisterScreenEvent()

    class EmailFieldChanged(val email: String) : RegisterScreenEvent()

    class PasswordFieldChanged(val password: String) : RegisterScreenEvent()

    class SetPasswordVisibility(val isVisible: Boolean) : RegisterScreenEvent()

}