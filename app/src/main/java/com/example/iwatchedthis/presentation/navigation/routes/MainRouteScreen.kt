package com.example.iwatchedthis.presentation.navigation.routes

sealed class MainRouteScreen(val route: String) {
    data object HomeScreen : MainRouteScreen("home_screen")
    data object CommentsScreen : MainRouteScreen("comments_screen")
    data object ProfileScreen : MainRouteScreen("profile_screen")
}