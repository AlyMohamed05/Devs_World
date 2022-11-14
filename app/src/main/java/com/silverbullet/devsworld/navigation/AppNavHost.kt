package com.silverbullet.devsworld.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.silverbullet.devsworld.feature_activity.presentation.ActivityScreen
import com.silverbullet.devsworld.feature_chat.presentation.ChatScreen
import com.silverbullet.devsworld.feature_post.presentation.create_post.CreatePostScreen
import com.silverbullet.devsworld.feature_profile.presentation.edit_profile.EditProfileScreen
import com.silverbullet.devsworld.feature_auth.presentation.login.LoginScreen
import com.silverbullet.devsworld.feature_post.presentation.main_feed.MainFeedScreen
import com.silverbullet.devsworld.feature_post.presentation.person_list.PersonListScreen
import com.silverbullet.devsworld.feature_post.presentation.post_detail.PostDetailScreen
import com.silverbullet.devsworld.feature_profile.presentation.profile.ProfileScreen
import com.silverbullet.devsworld.feature_auth.presentation.register.RegisterScreen
import com.silverbullet.devsworld.feature_search.presentation.SearchScreen

@Composable
fun AppNavHost(navController: NavHostController, startDestination: String) {

    NavHost(navController = navController, startDestination = startDestination) {

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

        composable(Screen.CreatePostScreen.route) {
            CreatePostScreen(navController = navController)
        }

        composable(
            Screen.PostDetailScreen.route + "/{postId}",
            arguments = listOf(
                navArgument("postId"){
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) {
            PostDetailScreen(navController = navController)
        }

        composable(Screen.SearchScreen.route) {
            SearchScreen(navController = navController)
        }

        composable(Screen.EditProfileScreen.route) {
            EditProfileScreen(navController = navController)
        }

        composable(Screen.PersonListScreen.route) {
            PersonListScreen(navController = navController)
        }

    }

}