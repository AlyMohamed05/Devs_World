package com.silverbullet.devsworld.feature_profile.presentation.profile.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.silverbullet.devsworld.R
import com.silverbullet.devsworld.core.domain.model.Profile
import com.silverbullet.devsworld.core.presentation.ui.theme.PaddingMedium
import com.silverbullet.devsworld.core.presentation.ui.theme.ProfilePictureLarge

@Composable
fun ProfileHeaderSection(
    modifier: Modifier = Modifier,
    profile: Profile?,
    onEditClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .offset(y = -ProfilePictureLarge / 2f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = profile?.profileImageUrl,
            contentDescription = "Profile Picture",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(ProfilePictureLarge)
                .clip(CircleShape)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colors.onSurface,
                )
        )
        profile?.let {
            Row(
                modifier = Modifier.offset(
                    x = if (profile.isOwnProfile)
                        (30.dp + PaddingMedium) / 2f
                    else
                        0.dp
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = profile.username,
                    style = MaterialTheme.typography.h1,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )
                if (profile.isOwnProfile) {
                    Spacer(modifier = Modifier.height(PaddingMedium))
                    IconButton(
                        onClick = onEditClick,
                        modifier = Modifier.size(30.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = stringResource(id = R.string.edit)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(PaddingMedium))
            Text(
                text = profile.bio,
                style = MaterialTheme.typography.body2
            )
            Spacer(modifier = Modifier.height(PaddingMedium))
            ProfileStats(
                profile = profile,
                isFollowing = true
            )
        }
    }
}