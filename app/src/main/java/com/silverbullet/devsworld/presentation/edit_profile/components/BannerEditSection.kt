package com.silverbullet.devsworld.presentation.edit_profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.silverbullet.devsworld.presentation.ui.theme.ProfilePictureLarge

@Composable
fun BannerEditSection(
    bannerImage: Painter,
    profileImage: Painter,
    profilePictureSize: Dp = ProfilePictureLarge,
    onBannerClick: () -> Unit = {},
    onProfileImageClick: () -> Unit = {}
) {
    val bannerHeight = (LocalConfiguration.current.screenWidthDp / 2.5f).dp
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(bannerHeight + profilePictureSize / 2f)
    ) {
        Image(
            painter = bannerImage,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(bannerHeight)
        )
        Image(
            painter = profileImage,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .size(profilePictureSize)
                .clip(CircleShape)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colors.onSurface,
                    shape = CircleShape
                )
        )
    }
}