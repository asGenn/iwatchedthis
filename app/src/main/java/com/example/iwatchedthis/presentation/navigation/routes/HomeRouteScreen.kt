package com.example.iwatchedthis.presentation.navigation.routes

sealed class HomeRouteScreen(val route: String) {


    data object MovieDetail : HomeRouteScreen("movie_detail")
}