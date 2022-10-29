package com.silverbullet.devsworld.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.silverbullet.devsworld.presentation.login.LoginScreen
import com.silverbullet.devsworld.presentation.register.RegisterScreen

@Composable
fun AppNavHost(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screen.LoginScreen.route) {

        composable(Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }

        composable(Screen.RegisterScreen.route){
            RegisterScreen(navController = navController)
        }

    }

}