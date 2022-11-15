package com.silverbullet.devsworld.feature_profile.presentation.edit_profile

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silverbullet.devsworld.core.domain.model.Profile
import com.silverbullet.devsworld.core.util.Resource
import com.silverbullet.devsworld.feature_profile.domain.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    private val _profileImageUrl = mutableStateOf<String?>(null)
    val profileImageUrl: State<String?> = _profileImageUrl

    private val _profileImageUri = mutableStateOf<Uri?>(null)
    val profileImageUri: State<Uri?> = _profileImageUri

    private val _usernameText = mutableStateOf("")
    val usernameText: State<String> = _usernameText

    private val _githubLinkText = mutableStateOf("")
    val githubLinkText: State<String> = _githubLinkText

    private val _instagramLinkText = mutableStateOf("")
    val instagramLinkText: State<String> = _instagramLinkText


    private val _linkedinLinkText = mutableStateOf("")
    val linkedinLinkText: State<String> = _linkedinLinkText


    private val _bioText = mutableStateOf("")
    val bioText: State<String> = _bioText

    private val _bioError = mutableStateOf<String?>(null)
    val bioError: State<String?> = _bioError

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    init {
        viewModelScope.launch {
            loadProfile()
        }
    }

    fun onEvent(event: EditProfileScreenEvents) {
        when (event) {
            is EditProfileScreenEvents.EditUsernameField -> {
                _usernameText.value = event.username
            }
            is EditProfileScreenEvents.EditGithubField -> {
                _githubLinkText.value = event.githubLink
            }
            is EditProfileScreenEvents.EditInstagramLinkField -> {
                _instagramLinkText.value = event.instagramLink
            }
            is EditProfileScreenEvents.EditLinkedinLinkField -> {
                _linkedinLinkText.value = event.linkedinLink
            }
            is EditProfileScreenEvents.EditBioField -> {
                _bioText.value = event.bio
            }
            is EditProfileScreenEvents.EditProfileImage -> {
                _profileImageUri.value = event.profileImageUri
            }
            EditProfileScreenEvents.Submit -> {
                updateProfile()
            }
        }
    }

    private suspend fun loadProfile() {
        profileRepository
            .getProfile(null)
            .collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        initProfileFields(resource.data)
                    }
                    is Resource.Error ->
                        _isLoading.value = false
                    is Resource.Loading ->
                        _isLoading.value = true
                }
            }
    }

    private fun updateProfile() {
        viewModelScope.launch {
            profileRepository
                .updateProfile(
                    username = _usernameText.value,
                    githubUrl = _githubLinkText.value,
                    linkedinUrl = _linkedinLinkText.value,
                    instagramUrl = _instagramLinkText.value,
                    bio = _bioText.value,
                    skills = emptyList(),
                    profileImageUri = _profileImageUri.value
                )
                .collect { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            Timber.d("Updated Profile !!!")
                        }
                        is Resource.Error -> {
                            _isLoading.value = false
                        }
                        is Resource.Loading ->
                            _isLoading.value = true
                    }
                }
        }
    }

    private fun initProfileFields(profile: Profile) {
        _profileImageUrl.value = profile.profileImageUrl
        _usernameText.value = profile.username
        _githubLinkText.value = profile.githubUrl ?: ""
        _instagramLinkText.value = profile.instagramUrl ?: ""
        _linkedinLinkText.value = profile.linkedinUrl ?: ""
        _bioText.value = profile.bio
    }
}