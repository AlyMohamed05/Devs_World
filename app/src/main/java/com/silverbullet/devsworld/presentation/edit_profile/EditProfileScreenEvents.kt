package com.silverbullet.devsworld.presentation.edit_profile

sealed class EditProfileScreenEvents{

    class EditUsernameField(val username: String): EditProfileScreenEvents()

    class EditGithubField(val githubLink: String): EditProfileScreenEvents()

    class EditInstagramLinkField(val instagramLink: String): EditProfileScreenEvents()

    class EditLinkedinLinkField(val linkedinLink: String): EditProfileScreenEvents()

    class EditBioField(val bio: String): EditProfileScreenEvents()
}
