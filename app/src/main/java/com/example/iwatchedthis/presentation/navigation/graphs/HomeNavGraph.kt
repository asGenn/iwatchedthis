package com.example.iwatchedthis.presentation.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.iwatchedthis.presentation.navigation.routes.HomeRouteScreen
import com.example.iwatchedthis.presentation.screens.details.DetailsScreen

fun NavGraphBuilder.homeNavGraph() {
    navigation(
        route = Graphs.HOME_GRAPH,
        startDestination = HomeRouteScreen.MovieDetail.route

    ) {
        composable(
            route = HomeRouteScreen.MovieDetail.route + "/{movieId}",
            arguments = listOf(navArgument("movieId") { defaultValue = "" })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId") ?: ""
            DetailsScreen(movieId = movieId)
        }
    }
}