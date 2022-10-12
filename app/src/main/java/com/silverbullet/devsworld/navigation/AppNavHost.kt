package com.silverbullet.devsworld.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavHost(navController: NavHostController) {

    NavHost(navController = navController, startDestination = "NOT_DEFINED_YET") {

        composable(Screen.LoginScreen.route) {

        }

    }

}