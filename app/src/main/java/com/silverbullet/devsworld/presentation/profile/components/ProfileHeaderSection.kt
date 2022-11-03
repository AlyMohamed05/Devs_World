package com.silverbullet.devsworld.presentation.profile.components

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.silverbullet.devsworld.domain.model.User
import com.silverbullet.devsworld.R
import com.silverbullet.devsworld.presentation.ui.theme.PaddingMedium
import com.silverbullet.devsworld.presentation.ui.theme.ProfilePictureLarge

@Composable
fun ProfileHeaderSection(
    modifier: Modifier = Modifier,
    user: User,
    isOwnProfile: Boolean = true,
    onEditClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .offset(y = -ProfilePictureLarge / 2f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.kermit),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(ProfilePictureLarge)
                .clip(CircleShape)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colors.onSurface,
                )
        )
        Row(
            modifier = Modifier.offset(
                x = if (isOwnProfile)
                    (30.dp + PaddingMedium) / 2f
                else
                    0.dp
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = user.username,
                style = MaterialTheme.typography.h1,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )
            if (isOwnProfile) {
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
            text = user.description,
            style = MaterialTheme.typography.body2
        )
        Spacer(modifier = Modifier.height(PaddingMedium))
        ProfileStats(
            user = user,
            isFollowing = true,
            isOwnProfile = isOwnProfile
        )
    }
}