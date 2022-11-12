package com.silverbullet.devsworld.core.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.silverbullet.devsworld.R
import com.silverbullet.devsworld.core.presentation.ui.theme.*
import com.silverbullet.devsworld.core.domain.model.Post
import com.silverbullet.devsworld.core.util.Constants

@Composable
fun Post(
    modifier: Modifier = Modifier,
    post: Post,
    showProfileImage: Boolean = true,
    onPostClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(PaddingMedium)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset(
                    y = if (showProfileImage)
                        ProfilePictureMedium / 2f
                    else 0.dp
                )
                .clip(MaterialTheme.shapes.medium)
                .shadow(5.dp)
                .background(MediumGray)
                .clickable { onPostClick() }
        ) {
            AsyncImage(
                model = post.imageUrl,
                contentDescription = "Post Image",
                modifier = Modifier
                    .aspectRatio(16f/9f)
                    .fillMaxWidth()
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(PaddingMedium)
            ) {
                ActionRow(
                    username = post.username,
                    modifier = Modifier.fillMaxWidth(),
                    onLikeClick = {

                    },
                    onCommentClick = {

                    },
                    onShareClick = {

                    },
                    onUsernameClick = {

                    }
                )
                Spacer(modifier = Modifier.height(PaddingSmall))
                Text(
                    text = buildAnnotatedString {
                        append(post.description)
                        withStyle(SpanStyle(color = HintGray)) {
                            append(LocalContext.current.getString(R.string.read_more))
                        }
                    },
                    style = MaterialTheme.typography.body2,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = Constants.MAX_POST_DESCRIPTION_LINES
                )
                Spacer(modifier = Modifier.height(PaddingSmall))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.liked_by_x_people, post.likesCount),
                        style = MaterialTheme.typography.h2, fontSize = 16.sp
                    )
                    Text(
                        text = stringResource(id = R.string.x_comments, post.commentsCount),
                        style = MaterialTheme.typography.h2,
                        fontSize = 16.sp
                    )
                }
            }

        }
        if (showProfileImage) {
            Image(
                painter = painterResource(id = R.drawable.kermit),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(ProfilePictureMedium)
                    .align(Alignment.TopCenter)
            )
        }
    }
}

@Composable
fun EngagementButtons(
    modifier: Modifier = Modifier,
    isLiked: Boolean = false,
    onLikeClick: (Boolean) -> Unit = {},
    onCommentClick: () -> Unit = {},
    onShareClick: () -> Unit = {},
    iconSize: Dp = 24.dp
) {
    Row(horizontalArrangement = Arrangement.SpaceAround, modifier = modifier) {
        IconButton(
            modifier = Modifier.size(iconSize),
            onClick = { onLikeClick(!isLiked) }
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = if (isLiked)
                    stringResource(id = R.string.unlike)
                else
                    stringResource(id = R.string.like),
                tint = if (isLiked) Color.Red else TextWhite
            )
        }
        Spacer(modifier = Modifier.width(PaddingMedium))
        IconButton(
            modifier = Modifier.size(iconSize),
            onClick = onCommentClick
        ) {
            Icon(
                imageVector = Icons.Default.Comment,
                contentDescription = stringResource(id = R.string.comment)
            )
        }
        Spacer(modifier = Modifier.width(PaddingMedium))
        IconButton(
            modifier = Modifier.size(iconSize),
            onClick = onShareClick
        ) {
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = stringResource(id = R.string.share)
            )
        }
    }
}

@Composable
fun ActionRow(
    modifier: Modifier = Modifier,
    username: String,
    isLiked: Boolean = false,
    onLikeClick: (Boolean) -> Unit = {},
    onCommentClick: () -> Unit = {},
    onShareClick: () -> Unit = {},
    onUsernameClick: (String) -> Unit = {}
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = username,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary
            ),
            modifier = Modifier
                .clickable { onUsernameClick(username) }
        )
        EngagementButtons(
            isLiked = isLiked,
            onLikeClick = onLikeClick,
            onCommentClick = onCommentClick,
            onShareClick = onShareClick
        )
    }
}