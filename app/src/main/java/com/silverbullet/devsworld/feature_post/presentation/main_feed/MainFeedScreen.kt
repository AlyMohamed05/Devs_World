package com.silverbullet.devsworld.feature_post.presentation.main_feed

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.silverbullet.devsworld.R
import com.silverbullet.devsworld.core.presentation.components.Post
import com.silverbullet.devsworld.navigation.Screen
import com.silverbullet.devsworld.core.presentation.components.StandardToolbar

@Composable
fun MainFeedScreen(
    navController: NavController,
    viewModel: MainFeedViewModel = hiltViewModel()
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
            showBackArrow = false,
            navActions = {
                IconButton(onClick = { navController.navigate(Screen.SearchScreen.route) }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = MaterialTheme.colors.onBackground
                    )
                }
            }
        )
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(viewModel.state.value.posts) { post ->
                    Post(
                        post = post,
                        onLikeClick = { viewModel.likePost(post.id, !post.isLiked) },
                        onPostClick = { navController.navigate(Screen.PostDetailScreen.route + "/${post.id}") }
                    )
                }
            }
            if (viewModel.state.value.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .scale(0.5f),
                    color = MaterialTheme.colors.primary
                )
            }
        }
    }
}