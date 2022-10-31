package com.silverbullet.devsworld.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.silverbullet.devsworld.presentation.activity.ActivityScreen
import com.silverbullet.devsworld.presentation.chat.ChatScreen
import com.silverbullet.devsworld.presentation.login.LoginScreen
import com.silverbullet.devsworld.presentation.main_feed.MainFeedScreen
import com.silverbullet.devsworld.presentation.profile.ProfileScreen
import com.silverbullet.devsworld.presentation.register.RegisterScreen

@Composable
fun AppNavHost(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screen.LoginScreen.route) {

        composable(Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }

        composable(Screen.RegisterScreen.route) {
            RegisterScreen(navController = navController)
        }

        composable(Screen.MainFeedScreen.route) {
            MainFeedScreen(navController)
        }

        composable(Screen.ChatScreen.route) {
            ChatScreen(navController = navController)
        }

        composable(Screen.ActivityScreen.route) {
            ActivityScreen(navController = navController)
        }

        composable(Screen.ProfileScreen.route) {
            ProfileScreen(navController = navController)
        }

    }

}