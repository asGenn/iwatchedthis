package com.example.iwatchedthis.presentation.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.iwatchedthis.presentation.navigation.routes.ProfileRoutesScreen
import com.example.iwatchedthis.presentation.screens.details.DetailsScreen

fun NavGraphBuilder.profileNavGraph() {

    navigation(
        route = Graphs.PROFILE_GRAPH,
        startDestination = ProfileRoutesScreen.MovieDetail.route
    ) {
        composable(
            route = ProfileRoutesScreen.MovieDetail.route + "/{movieId}",
            arguments = listOf(navArgument("movieId") { defaultValue = "" })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId") ?: ""
            DetailsScreen(movieId = movieId)
        }
    }
}