package com.silverbullet.devsworld.feature_profile.presentation.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.silverbullet.devsworld.core.domain.model.User
import com.silverbullet.devsworld.R
import com.silverbullet.devsworld.core.presentation.ui.theme.PaddingMedium

@Composable
fun ProfileStats(
    modifier: Modifier = Modifier,
    user: User,
    isFollowing: Boolean,
    isOwnProfile: Boolean = true,
    onFollowClick: () -> Unit = {},
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProfileNumber(
            number = user.followersCount,
            text = stringResource(id = R.string.followers)
        )
        Spacer(modifier = Modifier.width(PaddingMedium))
        ProfileNumber(
            number = user.followingCount,
            text = stringResource(id = R.string.following)
        )
        Spacer(modifier = Modifier.width(PaddingMedium))

        ProfileNumber(
            number = user.postCount,
            text = stringResource(id = R.string.posts)
        )
        if (isOwnProfile) {
            Spacer(modifier = Modifier.width(PaddingMedium))
            Button(
                onClick = onFollowClick,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (isFollowing)
                        Color.Red
                    else
                        MaterialTheme.colors.primary
                )
            ) {
                Text(
                    text = if (isFollowing)
                        stringResource(id = R.string.unfollow)
                    else
                        stringResource(id = R.string.follow),
                    color = if (isFollowing)
                        Color.White
                    else
                        MaterialTheme.colors.onPrimary
                )
            }
        }
    }
}