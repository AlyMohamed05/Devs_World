package com.silverbullet.devsworld.feature_activity.domain

sealed class ActivityAction{

    object LikedPost: ActivityAction()

    object CommentedOnPost: ActivityAction()

}
