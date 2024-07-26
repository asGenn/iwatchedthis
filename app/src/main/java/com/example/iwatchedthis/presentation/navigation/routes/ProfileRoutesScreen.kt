package com.example.iwatchedthis.presentation.navigation.routes

sealed class ProfileRoutesScreen(val route: String) {
    data object MovieDetail : ProfileRoutesScreen("movie_detail")
}