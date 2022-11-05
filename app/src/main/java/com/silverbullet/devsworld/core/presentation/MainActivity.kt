package com.silverbullet.devsworld.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.silverbullet.devsworld.navigation.AppNavHost
import com.silverbullet.devsworld.navigation.Screen
import com.silverbullet.devsworld.core.presentation.components.StandardScaffold
import com.silverbullet.devsworld.core.presentation.ui.theme.DevsWorldTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            DevsWorldTheme {
                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    val navController = rememberNavController()
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    StandardScaffold(
                        navController = navController,
                        showBottomBar = navBackStackEntry?.destination?.route in listOf(
                            Screen.MainFeedScreen.route,
                            Screen.ChatScreen.route,
                            Screen.ActivityScreen.route,
                            Screen.ProfileScreen.route
                        ),
                        onFabClick = {
                            navController.navigate(Screen.CreatePostScreen.route)
                        },
                        content = {
                            AppNavHost(navController = navController)
                        }
                    )
                }
            }
        }
    }

}