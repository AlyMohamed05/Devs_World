package com.silverbullet.devsworld.navigation

sealed class Screen(val route: String) {

    object LoginScreen : Screen("login_screen_route")

    object RegisterScreen : Screen("register_screen_route")

    object MainFeedScreen : Screen("main_feed_screen_route")

    object PostDetailScreen : Screen("post_detail_screen_route")

    object ChatScreen : Screen("chat_screen_route")

    object MessagesScreen : Screen("messages_screen_route")

    object ProfileScreen : Screen("profile_screen_route")

    object EditProfileScreen : Screen("edit_profile_screen_route")

    object PersonListScreen : Screen("person_list_screen_route")

    object CreatePostScreen : Screen("create_post_screen_route")

    object ActivityScreen : Screen("activity_screen_route")

    object SearchScreen : Screen("search_screen_route")

}
