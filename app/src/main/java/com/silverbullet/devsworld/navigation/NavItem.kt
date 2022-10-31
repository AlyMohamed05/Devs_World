package com.silverbullet.devsworld.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class NavItem(
    val icon: ImageVector,
    val contentDescription: String? = null,
    val alertCount: Int? = null,
    val route: String
)
