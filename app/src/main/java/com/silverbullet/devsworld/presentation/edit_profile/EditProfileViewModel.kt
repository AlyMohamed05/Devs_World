package com.silverbullet.devsworld.presentation.edit_profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor() : ViewModel() {

    private val _usernameText = mutableStateOf("")
    val usernameText: State<String> = _usernameText

    private val _usernameError = mutableStateOf<String?>(null)
    val usernameError: State<String?> = _usernameError

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
        }
    }
}