package com.silverbullet.devsworld.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.silverbullet.devsworld.core.domain.model.Post
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

        composable(Screen.CreatePostScreen.route) {
            CreatePostScreen(navController = navController)
        }

        composable(Screen.PostDetailScreen.route) {
            PostDetailScreen(
                navController = navController,
                post = Post(
                    username = "Android",
                    "",
                    "",
                    "This is a description written to test how the description appears in the post item in the main feed screen and not in the post detail screen as the main screen description should contain only three lines and then any additional lines should be added to read more section",
                    likeCount = 33,
                    commentCount = 13
                )
            )
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