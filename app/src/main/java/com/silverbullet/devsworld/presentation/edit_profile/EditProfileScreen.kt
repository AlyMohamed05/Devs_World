package com.silverbullet.devsworld.presentation.edit_profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.silverbullet.devsworld.presentation.ui.theme.ProfilePictureLarge
import com.silverbullet.devsworld.R
import com.silverbullet.devsworld.presentation.components.StandardTextField
import com.silverbullet.devsworld.presentation.components.StandardToolbar
import com.silverbullet.devsworld.presentation.edit_profile.components.BannerEditSection
import com.silverbullet.devsworld.presentation.edit_profile.components.SkillsSelector
import com.silverbullet.devsworld.presentation.ui.theme.PaddingLarge
import com.silverbullet.devsworld.presentation.ui.theme.PaddingSmall

@Composable
fun EditProfileScreen(
    navController: NavController,
    viewModel: EditProfileViewModel = hiltViewModel(),
    profilePictureSize: Dp = ProfilePictureLarge
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        StandardToolbar(
            navController = navController,
            showBackArrow = true,
            title = {
                Text(
                    text = stringResource(id = R.string.edit_your_profile),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
            },
            navActions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = stringResource(id = R.string.save_chagnes),
                        tint = MaterialTheme.colors.onBackground
                    )
                }
            }
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(state = rememberScrollState())
        ) {
            BannerEditSection(
                bannerImage = painterResource(id = R.drawable.art),
                profileImage = painterResource(id = R.drawable.kermit),
                profilePictureSize = profilePictureSize
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(PaddingLarge)

            ) {
                Spacer(modifier = Modifier.height(PaddingLarge))
                StandardTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    singleLine = true,
                    text = viewModel.usernameText.value,
                    hint = stringResource(id = R.string.username_hint),
                    error = viewModel.usernameError.value,
                    onValueChange = { username ->
                        viewModel.onEvent(
                            EditProfileScreenEvents.EditUsernameField(username)
                        )
                    },
                    leadingIcon = Icons.Default.Person
                )
                Spacer(modifier = Modifier.height(PaddingLarge))
                StandardTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    singleLine = true,
                    text = viewModel.githubLinkText.value,
                    hint = stringResource(id = R.string.github_profile_url),
                    onValueChange = { githubLink ->
                        viewModel.onEvent(
                            EditProfileScreenEvents.EditGithubField(githubLink)
                        )
                    },
                    leadingIcon = ImageVector.vectorResource(id = R.drawable.ic_github)
                )
                Spacer(modifier = Modifier.height(PaddingLarge))
                StandardTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    singleLine = true,
                    text = viewModel.instagramLinkText.value,
                    hint = stringResource(id = R.string.instagram_profile_url),
                    onValueChange = { instagramLink ->
                        viewModel.onEvent(
                            EditProfileScreenEvents.EditInstagramLinkField(instagramLink)
                        )
                    },
                    leadingIcon = ImageVector.vectorResource(id = R.drawable.ic_instagram)
                )
                Spacer(modifier = Modifier.height(PaddingLarge))
                StandardTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    singleLine = true,
                    text = viewModel.linkedinLinkText.value,
                    hint = stringResource(id = R.string.linkedin_profile_url),
                    onValueChange = { linkedinLink ->
                        viewModel.onEvent(
                            EditProfileScreenEvents.EditLinkedinLinkField(linkedinLink)
                        )
                    },
                    leadingIcon = ImageVector.vectorResource(id = R.drawable.ic_linkedin)
                )
                Spacer(modifier = Modifier.height(PaddingLarge))
                StandardTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = viewModel.bioText.value,
                    error = viewModel.bioError.value,
                    hint = stringResource(id = R.string.bio),
                    onValueChange = { bio ->
                        viewModel.onEvent(
                            EditProfileScreenEvents.EditBioField(bio)
                        )
                    },
                    maxLines = 3,
                    leadingIcon = Icons.Default.Description
                )
                Spacer(modifier = Modifier.height(PaddingSmall))
                SkillsSelector(modifier = Modifier.fillMaxWidth())
            }
        }
    }
}