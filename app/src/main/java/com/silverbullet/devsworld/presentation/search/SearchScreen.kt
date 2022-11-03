package com.silverbullet.devsworld.presentation.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.silverbullet.devsworld.R
import com.silverbullet.devsworld.domain.model.User
import com.silverbullet.devsworld.presentation.components.StandardTextField
import com.silverbullet.devsworld.presentation.components.StandardToolbar
import com.silverbullet.devsworld.presentation.components.UserProfileItem
import com.silverbullet.devsworld.presentation.ui.theme.IconSizeMedium
import com.silverbullet.devsworld.presentation.ui.theme.PaddingLarge
import com.silverbullet.devsworld.presentation.ui.theme.PaddingMedium
import com.silverbullet.devsworld.presentation.ui.theme.PaddingSmall

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    Column(modifier = Modifier.fillMaxSize()) {
        StandardToolbar(
            navController = navController,
            title = {
                Text(
                    text = stringResource(id = R.string.search),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
            },
            modifier = Modifier.fillMaxWidth(),
            showBackArrow = true,
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = PaddingLarge)
        ) {
            Spacer(modifier = Modifier.height(PaddingSmall))
            StandardTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                text = viewModel.searchText.value,
                hint = stringResource(id = R.string.search),
                onValueChange = { viewModel.setSearchText(it) },
                leadingIcon = Icons.Default.Search
            )
            Spacer(modifier = Modifier.height(PaddingMedium))
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(20) {
                    UserProfileItem(
                        user = User(
                            "",
                            "Android",
                            "Android OS, I'm on of the most popular ones",
                            33,
                            13,
                            69
                        ),
                        actionIcon = {
                            Icon(
                                imageVector = Icons.Default.PersonAdd,
                                contentDescription = null,
                                modifier = Modifier.size(IconSizeMedium)
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(PaddingSmall))
                }
            }
        }
    }
}