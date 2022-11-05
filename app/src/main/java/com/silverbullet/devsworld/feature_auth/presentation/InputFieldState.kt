package com.silverbullet.devsworld.feature_auth.presentation

import android.util.Patterns
import com.silverbullet.devsworld.core.util.UiText

data class InputFieldState(
    val text: String = "",
    val error: UiText? = null
) {

    fun validateAsEmail(): Boolean {
        if (Patterns.EMAIL_ADDRESS.matcher(text).matches()) {
            return true
        }
        return false
    }

    fun validateAsUsername(): String? {
        if (text.isBlank()) {
            return "Field can't be blank"
        }
        return null
    }

    fun validateAsPassword(): String? {
        if (text.isBlank()) {
            return "Field can't be blank"
        }
        if (text.length < 8) {
            return "Password must be 8 chars at least"
        }
        return null
    }
}