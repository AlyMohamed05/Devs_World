package com.silverbullet.devsworld.feature_post.presentation.post_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.silverbullet.devsworld.R
import com.silverbullet.devsworld.core.domain.model.Post
import com.silverbullet.devsworld.navigation.Screen
import com.silverbullet.devsworld.core.presentation.components.ActionRow
import com.silverbullet.devsworld.core.presentation.components.StandardToolbar
import com.silverbullet.devsworld.core.presentation.ui.theme.*

@Composable
fun PostDetailScreen(
    navController: NavController,
    post: Post
) {
    Column(modifier = Modifier.fillMaxSize()) {
        StandardToolbar(
            navController = navController,
            title = {
                Text(
                    text = stringResource(id = R.string.your_feed),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
            },
            modifier = Modifier.fillMaxWidth(),
            showBackArrow = true,
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface)
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colors.background)
                ) {
                    Spacer(
                        modifier = Modifier
                            .height(PaddingMedium)
                    )
                    Box(modifier = Modifier.fillMaxSize()) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .offset(y = ProfilePictureMedium / 2f)
                                .clip(MaterialTheme.shapes.medium)
                                .shadow(5.dp)
                                .background(MediumGray)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.kermit),
                                contentDescription = "Post Image",
                                modifier = Modifier.fillMaxWidth()
                            )
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(PaddingLarge)
                            ) {
                                ActionRow(
                                    username = "Android",
                                    modifier = Modifier.fillMaxWidth(),
                                    onLikeClick = {

                                    },
                                    onCommentClick = {

                                    },
                                    onShareClick = {

                                    },
                                    onUsernameClick = {

                                    }
                                )
                                Spacer(modifier = Modifier.height(PaddingSmall))
                                Text(
                                    text = post.description,
                                    style = MaterialTheme.typography.body2
                                )
                                Spacer(modifier = Modifier.height(PaddingSmall))
                                Text(
                                    text = stringResource(
                                        id = R.string.liked_by_x_people,
                                        post.likeCount
                                    ),
                                    style = MaterialTheme.typography.h2, fontSize = 16.sp,
                                    modifier = Modifier.clickable {
                                        navController.navigate(Screen.PersonListScreen.route)
                                    }
                                )
                            }
                        }
                        Image(
                            painter = painterResource(id = R.drawable.kermit),
                            contentDescription = "Profile Picture",
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(ProfilePictureMedium)
                                .align(Alignment.TopCenter)
                        )
                    }
                    Spacer(modifier = Modifier.height(PaddingLarge))
                }
            }
            items(20) {
                Comment(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = PaddingLarge, vertical = PaddingSmall),
                    comment = com.silverbullet.devsworld.feature_post.domain.model.Comment(
                        username = "Android",
                        comment = "This is just a comment added by android",
                        likeCount = it
                    )
                )
                Spacer(modifier = Modifier.height(PaddingSmall))
            }
        }
    }
}