package com.silverbullet.devsworld.feature_post.presentation.post_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.silverbullet.devsworld.feature_post.domain.model.Comment
import com.silverbullet.devsworld.core.presentation.ui.theme.PaddingMedium
import com.silverbullet.devsworld.R
import com.silverbullet.devsworld.core.presentation.ui.theme.PaddingSmall
import com.silverbullet.devsworld.core.presentation.ui.theme.ProfilePictureSizeSmall

@Composable
fun Comment(
    modifier: Modifier = Modifier,
    comment: Comment,
    onLikeClick: (Boolean) -> Unit = {}
) {
    Card(
        modifier = modifier,
        elevation = 5.dp,
        shape = MaterialTheme.shapes.medium,
        backgroundColor = MaterialTheme.colors.onSurface
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(PaddingMedium)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.kermit),
                        contentDescription = null,
                        modifier = Modifier
                            .size(ProfilePictureSizeSmall)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(PaddingSmall))
                    Text(
                        text = comment.username,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.body1
                    )
                }
                Text(
                    text = "13 days ago",
                    color = MaterialTheme.colors.onBackground
                )
            }
            Spacer(modifier = Modifier.height(PaddingMedium))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = comment.comment,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onBackground,
                    modifier = Modifier.weight(9.2f)
                )
                Spacer(modifier = Modifier.width(PaddingMedium))
                IconButton(
                    onClick = { onLikeClick(!comment.isLiked) },
                    modifier = Modifier.weight(0.8f)
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = if (comment.isLiked)
                            stringResource(id = R.string.unlike)
                        else
                            stringResource(id = R.string.like)
                    )
                }
            }
            Spacer(modifier = Modifier.height(PaddingMedium))
            Text(
                text = stringResource(id = R.string.liked_by_x_people, comment.likeCount),
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onBackground

            )
        }
    }
}