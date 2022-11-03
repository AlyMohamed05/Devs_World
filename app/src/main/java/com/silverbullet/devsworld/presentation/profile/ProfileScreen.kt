package com.silverbullet.devsworld.presentation.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.silverbullet.devsworld.R
import com.silverbullet.devsworld.domain.model.Post
import com.silverbullet.devsworld.domain.model.User
import com.silverbullet.devsworld.navigation.Screen
import com.silverbullet.devsworld.presentation.components.Post
import com.silverbullet.devsworld.presentation.components.StandardToolbar
import com.silverbullet.devsworld.presentation.profile.components.BannerSection
import com.silverbullet.devsworld.presentation.profile.components.ProfileHeaderSection
import com.silverbullet.devsworld.presentation.ui.theme.PaddingSmall
import com.silverbullet.devsworld.presentation.ui.theme.ProfilePictureLarge

@Composable
fun ProfileScreen(navController: NavController) {
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
                    user = User(
                        "",
                        "Android",
                        "Android OS, I'm on of the most popular ones",
                        33,
                        13,
                        69
                    )
                )
            }
            items(1024) {
                Post(
                    post = Post(
                        username = "Android",
                        "",
                        "",
                        "This is a description written to test how the description appears in the post item in the main feed screen and not in the post detail screen as the main screen description should contain only three lines and then any additional lines should be added to read more section",
                        likeCount = 33,
                        commentCount = 13
                    ),
                    onPostClick = { navController.navigate(Screen.PostDetailScreen.route) },
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
}