package com.silverbullet.devsworld.presentation.create_post

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.silverbullet.devsworld.presentation.components.StandardToolbar
import com.silverbullet.devsworld.R
import com.silverbullet.devsworld.presentation.components.StandardTextField
import com.silverbullet.devsworld.presentation.ui.theme.PaddingMedium
import com.silverbullet.devsworld.presentation.ui.theme.PaddingSmall

@Composable
fun CreatePostScreen(
    navController: NavController,
    viewModel: CreatePostViewModel = hiltViewModel()
) {
    Column(modifier = Modifier.fillMaxSize()) {
        StandardToolbar(
            navController = navController,
            showBackArrow = true,
            title = {
                Text(
                    text = stringResource(id = R.string.create_new_post),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
            },
            navActions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = stringResource(id = R.string.create_new_post)
                    )
                }
            }
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(PaddingMedium)
        ) {
            Box(
                modifier = Modifier
                    .aspectRatio(16f / 9f)
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colors.onBackground,
                        shape = MaterialTheme.shapes.medium
                    )
                    .clickable {

                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.choose_image),
                    tint = MaterialTheme.colors.onBackground
                )
            }
            Spacer(modifier = Modifier.height(PaddingMedium))
            StandardTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                text = viewModel.descriptionText.value,
                hint = stringResource(id = R.string.description),
                onValueChange = { viewModel.setDescriptionText(it) },
                maxLines = 5,
                leadingIcon = Icons.Default.Description
            )
            Spacer(modifier = Modifier.height(PaddingMedium))
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(
                    text = stringResource(id = R.string.post),
                    color = Color.Black
                )
                Spacer(modifier = Modifier.width(PaddingSmall))
                Icon(imageVector = Icons.Default.Send, contentDescription = null)
            }
        }
    }
}