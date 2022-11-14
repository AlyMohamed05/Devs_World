package com.silverbullet.devsworld.core.data.util

sealed class LikeParentType(val type: Int) {

    object Post : LikeParentType(1)

    object Comment : LikeParentType(2)

    object None : LikeParentType(-1)

}
