package com.silverbullet.devsworld.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Doorbell
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Message
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.silverbullet.devsworld.navigation.BottomNavItem
import com.silverbullet.devsworld.navigation.NavItem
import com.silverbullet.devsworld.navigation.Screen

@Composable
fun StandardScaffold(
    modifier: Modifier = Modifier,
    showBottomBar: Boolean = true,
    bottomNavItems: List<NavItem> = listOf(
        NavItem(
            route = Screen.MainFeedScreen.route,
            icon = Icons.Outlined.Home,
            contentDescription = "Home"
        ),
        NavItem(
            route = Screen.ChatScreen.route,
            icon = Icons.Outlined.Message,
            contentDescription = "Messages"
        ),
        NavItem(
            route = Screen.ActivityScreen.route,
            icon = Icons.Outlined.Doorbell,
            contentDescription = "Activity"
        ),
        NavItem(
            route = Screen.ProfileScreen.route,
            icon = Icons.Outlined.Person,
            contentDescription = "Profile"
        )
    ),
    navController: NavController,
    content: @Composable () -> Unit = {}
) {
    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomAppBar(
                    modifier = Modifier
                        .fillMaxWidth(),
                    backgroundColor = MaterialTheme.colors.surface,
                    cutoutShape = CircleShape,
                    elevation = 5.dp,
                ) {
                    bottomNavItems.forEachIndexed { index, navItem ->
                        BottomNavItem(
                            icon = navItem.icon,
                            onClick = {
                                navController.navigate(navItem.route)
                            },
                            contentDescription = navItem.contentDescription,
                            alertCount = navItem.alertCount,
                            selected = navController.currentDestination?.route == navItem.route,
                        )
                    }
                }
            }
        },
        modifier = modifier
    ) { paddingValues ->
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            content()
        }
    }
}