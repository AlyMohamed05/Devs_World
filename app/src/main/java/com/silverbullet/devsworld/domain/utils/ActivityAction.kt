package com.silverbullet.devsworld.domain.utils

sealed class ActivityAction{

    object LikedPost: ActivityAction()

    object CommentedOnPost: ActivityAction()

}
