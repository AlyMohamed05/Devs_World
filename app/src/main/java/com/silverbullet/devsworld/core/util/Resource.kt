package com.silverbullet.devsworld.core.util

sealed class Resource<T>(data: T?, isLoading: Boolean, error: UiText?) {

    class Success<T>(
        val data: T,
        val isLoading: Boolean = false
    ) : Resource<T>(data, isLoading, null)

    class Error<T>(
        val data: T? = null,
        val error: UiText?
    ) : Resource<T>(data = data, isLoading = false, error = error)

    class Loading<T>(
        val data: T? = null
    ) : Resource<T>(data = data, isLoading = true, error = null)
}
