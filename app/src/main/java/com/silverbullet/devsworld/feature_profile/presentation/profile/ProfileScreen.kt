package com.silverbullet.devsworld.feature_profile.presentation.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.silverbullet.devsworld.R
import com.silverbullet.devsworld.navigation.Screen
import com.silverbullet.devsworld.core.presentation.components.Post
import com.silverbullet.devsworld.core.presentation.components.StandardToolbar
import com.silverbullet.devsworld.feature_profile.presentation.profile.components.BannerSection
import com.silverbullet.devsworld.feature_profile.presentation.profile.components.ProfileHeaderSection
import com.silverbullet.devsworld.core.presentation.ui.theme.PaddingSmall
import com.silverbullet.devsworld.core.presentation.ui.theme.ProfilePictureLarge

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    Column(modifier = Modifier.fillMaxSize()) {
        StandardToolbar(
            navController = navController,
            title = {
                Text(
                    text = stringResource(id = R.string.your_profile),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
            },
            modifier = Modifier.fillMaxWidth(),
            showBackArrow = false,
        )
        Box(modifier = Modifier.fillMaxSize()) {
            viewModel.state.value.profile?.let { profile ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    item {
                        BannerSection(
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    item {
                        ProfileHeaderSection(
                            profile = profile,
                            onEditClick = { navController.navigate(Screen.EditProfileScreen.route) }
                        )

                    }
                    items(viewModel.state.value.posts) {
                        Post(
                            post = it,
                            onPostClick = {
                                navController.navigate(Screen.PostDetailScreen.route + "/${it.id}")
                            },
                            onLikeClick = { isLiked -> viewModel.likePost(it.id, isLiked) },
                            showProfileImage = false,
                            modifier = Modifier
                                .offset(y = -ProfilePictureLarge / 2f)
                        )
                        Spacer(
                            modifier = Modifier
                                .height(PaddingSmall)
                                .offset(y = -ProfilePictureLarge / 2f)
                        )
                    }
                }
            }
            if (viewModel.state.value.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .scale(0.8f),
                    color = MaterialTheme.colors.primary
                )
            }
        }
    }
}