package com.silverbullet.devsworld.feature_activity.data.util

sealed class ActivityAction(val type: Int) {

    object Liked : ActivityAction(1)

    object Commented : ActivityAction(2)

    object Followed : ActivityAction(3)

    object None : ActivityAction(-1)

    companion object {

        fun parse(type: Int): ActivityAction {
            return when (type) {
                1 -> Liked
                2 -> Commented
                3 -> Followed
                else -> None
            }
        }
    }

}