package com.silverbullet.devsworld.feature_post.presentation.main_feed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.silverbullet.devsworld.R
import com.silverbullet.devsworld.navigation.Screen
import com.silverbullet.devsworld.core.presentation.components.StandardToolbar

@Composable
fun MainFeedScreen(
    navController: NavController
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
    }
}