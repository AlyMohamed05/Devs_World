package com.silverbullet.devsworld.feature_post.presentation.person_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.silverbullet.devsworld.core.presentation.components.StandardToolbar
import com.silverbullet.devsworld.core.presentation.ui.theme.PaddingSmall
import com.silverbullet.devsworld.R
import com.silverbullet.devsworld.core.presentation.ui.theme.PaddingMedium

@Composable
fun PersonListScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        StandardToolbar(
            navController = navController,
            showBackArrow = true,
            title = {
                Text(
                    text = stringResource(id = R.string.liked_by),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
            }
        )
        Spacer(modifier = Modifier.height(PaddingSmall))
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = PaddingMedium)
        ) {

        }
    }
}