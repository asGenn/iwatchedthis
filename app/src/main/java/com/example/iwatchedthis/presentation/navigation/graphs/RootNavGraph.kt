package com.example.iwatchedthis.presentation.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.iwatchedthis.presentation.screens.home.MoviesViewModel
import com.example.iwatchedthis.presentation.screens.main.MainScreen

@Composable
fun RootNavGraph(modifier: Modifier = Modifier, moviesViewModel: MoviesViewModel) {
    val rootNavController = rememberNavController()


    NavHost(
        navController = rootNavController,
        startDestination = Graphs.MAIN_GRAPH,
        route = Graphs.ROOT_GRAPH
    ) {
        composable(
            Graphs.MAIN_GRAPH
        ) {
            MainScreen(rootNavController = rootNavController, moviesViewModel = moviesViewModel)
        }
        homeNavGraph()
        profileNavGraph()

    }


}