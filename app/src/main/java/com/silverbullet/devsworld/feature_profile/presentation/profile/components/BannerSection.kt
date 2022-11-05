package com.silverbullet.devsworld.feature_profile.presentation.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.silverbullet.devsworld.R
import com.silverbullet.devsworld.core.presentation.ui.theme.PaddingMedium
import com.silverbullet.devsworld.core.presentation.ui.theme.PaddingSmall

@Composable
fun BannerSection(
    modifier: Modifier = Modifier,
    iconSize: Dp = 20.dp,
    onGithubClick: () -> Unit = {},
    onInstagramClick: () -> Unit = {},
    onLinkedInClick: () -> Unit = {}
) {
    Box(modifier = modifier) {
        Image(
            painterResource(id = R.drawable.art),
            contentDescription = stringResource(id = R.string.banner),
            modifier = Modifier
                .fillMaxSize()

        )
        Row(
            modifier = Modifier
                .align(
                    Alignment.BottomStart
                )
                .padding(PaddingSmall)
        ) {
            Spacer(modifier = Modifier.width(PaddingSmall))
            Image(
                painter = painterResource(id = R.drawable.ic_js_logo),
                contentDescription = "JavaScript",
                modifier = Modifier.size(iconSize)
            )
            Spacer(modifier = Modifier.width(PaddingSmall))
            Image(
                painter = painterResource(id = R.drawable.ic_csharp_logo),
                contentDescription = "C#",
                modifier = Modifier.size(iconSize)
            )
            Spacer(modifier = Modifier.width(PaddingSmall))
            Image(
                painter = painterResource(id = R.drawable.ic_kotlin_logo),
                contentDescription = "Kotlin",
                modifier = Modifier.size(iconSize)
            )
        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(PaddingSmall)
        ) {
            Spacer(modifier = Modifier.width(PaddingMedium))
            IconButton(
                onClick = onGithubClick,
                modifier = Modifier.size(iconSize)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_github),
                    contentDescription = "Github",
                )
            }
            Spacer(modifier = Modifier.width(PaddingMedium))
            IconButton(
                onClick = onInstagramClick,
                modifier = Modifier.size(iconSize)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_instagram),
                    contentDescription = "Instagram",
                )
            }
            Spacer(modifier = Modifier.width(PaddingMedium))
            IconButton(
                onClick = onLinkedInClick,
                modifier = Modifier.size(iconSize)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_linkedin),
                    contentDescription = "LinkedIn",
                )
            }
        }
    }
}