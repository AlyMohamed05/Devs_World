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
import com.silverbullet.devsworld.feature_activity.data.util.ActivityAction
import com.silverbullet.devsworld.R
import com.silverbullet.devsworld.core.presentation.ui.theme.TextWhite
import com.silverbullet.devsworld.feature_activity.data.util.ActivityTargetType

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
            val actionText = when (activity.type) {
                ActivityAction.Liked -> stringResource(id = R.string.liked)
                ActivityAction.Commented -> stringResource(id = R.string.commented_on)
                ActivityAction.Followed -> stringResource(id = R.string.followed)
                ActivityAction.None -> "???"
            }
            val targetText = activity.targetType?.let { targetType ->
                when (targetType) {
                    ActivityTargetType.Post -> stringResource(id = R.string.your_post)
                    ActivityTargetType.Comment -> stringResource(id = R.string.your_comment)
                    else -> null
                }
            } ?: stringResource(id = R.string.you)
            Text(
                text = buildAnnotatedString {
                    val boldStyle = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = TextWhite
                    )
                    withStyle(boldStyle) {
                        append(activity.issuerName)
                    }
                    append(" $actionText ")
                    withStyle(boldStyle) {
                        append(targetText)
                    }
                }
            )
            Text(
                text = activity.timestamp,
                fontSize = 12.sp
            )
        }
    }
}