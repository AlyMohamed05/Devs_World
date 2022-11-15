package com.silverbullet.devsworld.feature_profile.presentation.edit_profile

import android.net.Uri

sealed class EditProfileScreenEvents{

    class EditProfileImage(val profileImageUri: Uri?): EditProfileScreenEvents()

    class EditUsernameField(val username: String): EditProfileScreenEvents()

    class EditGithubField(val githubLink: String): EditProfileScreenEvents()

    class EditInstagramLinkField(val instagramLink: String): EditProfileScreenEvents()

    class EditLinkedinLinkField(val linkedinLink: String): EditProfileScreenEvents()

    class EditBioField(val bio: String): EditProfileScreenEvents()

    object Submit: EditProfileScreenEvents()
}
