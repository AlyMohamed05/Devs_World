package com.silverbullet.devsworld.feature_activity.data.util

sealed class ActivityTargetType(val type: Int) {

    object Post : ActivityTargetType(1)

    object Comment : ActivityTargetType(2)

    object Unknown : ActivityTargetType(-1)

    companion object {

        fun parse(type: Int): ActivityTargetType {
            return when (type) {

                1 -> Post
                2 -> Comment
                else -> Unknown
            }
        }
    }
}
