package com.silverbullet.devsworld.feature_post.presentation.create_post

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.silverbullet.devsworld.core.presentation.components.StandardToolbar
import com.silverbullet.devsworld.R
import com.silverbullet.devsworld.core.presentation.components.StandardTextField
import com.silverbullet.devsworld.core.presentation.ui.theme.PaddingMedium
import com.silverbullet.devsworld.core.presentation.ui.theme.PaddingSmall
import com.silverbullet.devsworld.core.presentation.util.CropActivityResultContract
import com.silverbullet.devsworld.core.util.fileName
import java.io.File
import java.util.UUID

@Composable
fun CreatePostScreen(
    navController: NavController,
    viewModel: CreatePostViewModel = hiltViewModel()
) {
    val cropActivityLauncher = rememberLauncherForActivityResult(
        contract = CropActivityResultContract(
            Uri.fromFile(
                File(
                    LocalContext.current.cacheDir,
                    UUID.randomUUID().toString()
                )
            )
        )
    ) {
        it?.let { uri ->
            viewModel.onEvent(
                CreatePostScreenEvent.SetCropImageUri(uri)
            )
        }
    }
    val context = LocalContext.current
    val pickImageLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) {
            it?.let { uri ->
                viewModel.onEvent(
                    CreatePostScreenEvent.SetSelectedImageFileName(uri.fileName(context))
                )
                cropActivityLauncher.launch(uri)
            }
        }
    LaunchedEffect(key1 = Unit) {
        viewModel.uiEvents.collect { event ->
            when (event) {
                is CreatePostViewModel.UiEvent.ShowToast -> Toast.makeText(
                    context,
                    event.message.asString(context),
                    Toast.LENGTH_SHORT
                ).show()

                CreatePostViewModel.UiEvent.NavigateUP -> navController.navigateUp()
            }
        }
    }

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
                IconButton(onClick = { viewModel.onEvent(CreatePostScreenEvent.Post) }) {
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
                    .clip(MaterialTheme.shapes.medium)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colors.onBackground,
                        shape = MaterialTheme.shapes.medium
                    )
                    .clickable {
                        pickImageLauncher.launch("image/*")
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.choose_image),
                    tint = MaterialTheme.colors.onBackground
                )
                viewModel.croppedImageUri.value.let {
                    AsyncImage(
                        model = it,
                        contentDescription = "image",
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            Spacer(modifier = Modifier.height(PaddingMedium))
            StandardTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                text = viewModel.descriptionText.value,
                hint = stringResource(id = R.string.description),
                onValueChange = {
                    viewModel.onEvent(
                        CreatePostScreenEvent.EditDescription(it)
                    )
                },
                maxLines = 5,
                leadingIcon = Icons.Default.Description
            )
            Spacer(modifier = Modifier.height(PaddingMedium))
            Button(
                onClick = { viewModel.onEvent(CreatePostScreenEvent.Post) },
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