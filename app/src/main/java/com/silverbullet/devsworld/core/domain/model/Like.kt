package com.silverbullet.devsworld.core.domain.model

data class Like(
    val userId: String,
    val parentId: String,
    val parentType: Int
)
