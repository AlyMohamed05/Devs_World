package com.silverbullet.devsworld.feature_activity.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.silverbullet.devsworld.feature_activity.domain.model.Activity
import com.silverbullet.devsworld.feature_activity.domain.ActivityAction
import com.silverbullet.devsworld.R
import com.silverbullet.devsworld.core.presentation.ui.theme.TextWhite

@Composable
fun ActivityItem(
    modifier: Modifier = Modifier,
    activity: Activity,
    contentPadding: Dp = 0.dp
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        backgroundColor = MaterialTheme.colors.onSurface,
        elevation = 5.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(contentPadding),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val actionText = when (activity.actionType) {
                ActivityAction.LikedPost -> stringResource(id = R.string.liked)
                ActivityAction.CommentedOnPost -> stringResource(id = R.string.commented_on)
            }
            Text(
                text = buildAnnotatedString {
                    val boldStyle = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = TextWhite
                    )
                    withStyle(boldStyle) {
                        append(activity.username)
                    }
                    append(" $actionText ")
                    withStyle(boldStyle) {
                        append(stringResource(id = R.string.your_post))
                    }
                }
            )
            Text(
                text = activity.formattedTime,
                fontSize = 12.sp
            )
        }
    }
}