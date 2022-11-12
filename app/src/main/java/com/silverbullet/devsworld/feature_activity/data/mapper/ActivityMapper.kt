package com.silverbullet.devsworld.feature_activity.data.mapper

import com.silverbullet.devsworld.core.util.DateFormatUtil
import com.silverbullet.devsworld.feature_activity.data.remote.dto.ActivityDto
import com.silverbullet.devsworld.feature_activity.data.util.ActivityAction
import com.silverbullet.devsworld.feature_activity.data.util.ActivityTargetType
import com.silverbullet.devsworld.feature_activity.domain.model.Activity

fun ActivityDto.toActivity(): Activity {
    val formattedTime = DateFormatUtil.timestampToFormattedString(timestamp, "MMM dd, HH:mm")
    return Activity(
        ownerId = ownerId,
        type = type.let { ActivityAction.parse(it) },
        issuerId = issuerId,
        issuerName = issuerName,
        targetType = targetType?.let { ActivityTargetType.parse(targetType) },
        targetId = targetId,
        timestamp = formattedTime
    )
}