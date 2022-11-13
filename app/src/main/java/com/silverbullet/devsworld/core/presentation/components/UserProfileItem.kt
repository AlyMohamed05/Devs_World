package com.silverbullet.devsworld.core.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.silverbullet.devsworld.core.domain.model.Profile
import com.silverbullet.devsworld.core.presentation.ui.theme.PaddingSmall

@Composable
fun UserProfileItem(
    modifier: Modifier = Modifier,
    actionIcon: @Composable () -> Unit = {},
    profile: Profile,
    onItemClick: () -> Unit = {},
    onActionItemClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .clickable {
                onItemClick()
            },
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(PaddingSmall),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AsyncImage(
                model = profile.profileImageUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(75.dp)
                    .clip(CircleShape)
                    .weight(3f)
            )
            Spacer(modifier = Modifier.padding(PaddingSmall))
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(8f)
            ) {
                Text(
                    text = profile.username,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(PaddingSmall))
                Text(
                    text = profile.bio,
                    style = MaterialTheme.typography.body2,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
            }
            IconButton(
                onClick = onActionItemClick,
                modifier = Modifier
                    .weight(2f)
            ) {
                actionIcon()
            }
        }
    }
}