package com.silverbullet.devsworld.core.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.silverbullet.devsworld.navigation.BottomNavItem
import com.silverbullet.devsworld.navigation.NavItem
import com.silverbullet.devsworld.navigation.Screen
import com.silverbullet.devsworld.R

@Composable
fun StandardScaffold(
    modifier: Modifier = Modifier,
    showBottomBar: Boolean = true,
    onFabClick: () -> Unit = {},
    navController: NavController,
    content: @Composable () -> Unit = {},
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
        NavItem(route = "NO_ROUTE"),
        NavItem(
            route = Screen.ActivityScreen.route,
            icon = Icons.Outlined.Notifications,
            contentDescription = "Activity",
            alertCount = 3
        ),
        NavItem(
            route = Screen.ProfileScreen.route,
            icon = Icons.Outlined.Person,
            contentDescription = "Profile"
        )
    ),
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
                    bottomNavItems.forEach { navItem ->
                        BottomNavItem(
                            icon = navItem.icon,
                            onClick = {
                                if (navController.currentDestination?.route != navItem.route) {
                                    navController
                                        .navigate(navItem.route) {
                                            popUpTo(Screen.MainFeedScreen.route) {
                                                inclusive =
                                                    navItem.route == Screen.MainFeedScreen.route
                                            }
                                        }
                                }
                            },
                            contentDescription = navItem.contentDescription,
                            alertCount = navItem.alertCount,
                            selected = navController.currentDestination?.route == navItem.route,
                            enabled = navItem.icon != null
                        )
                    }
                }
            }
        },
        floatingActionButton = {
            if (showBottomBar) {
                FloatingActionButton(
                    onClick = onFabClick,
                    backgroundColor = MaterialTheme.colors.primary
                ) {
                    Icon(
                        imageVector = Icons.Default.Add, contentDescription = stringResource(
                            id = R.string.make_post
                        )
                    )
                }
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
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