package com.example.iwatchedthis.presentation.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.iwatchedthis.presentation.navigation.NavItem
import com.example.iwatchedthis.presentation.navigation.routes.HomeRouteScreen
import com.example.iwatchedthis.presentation.navigation.routes.MainRouteScreen
import com.example.iwatchedthis.presentation.navigation.routes.ProfileRoutesScreen
import com.example.iwatchedthis.presentation.screens.comment.CommentScreen
import com.example.iwatchedthis.presentation.screens.home.HomeScreen
import com.example.iwatchedthis.presentation.screens.home.MoviesViewModel
import com.example.iwatchedthis.presentation.screens.profile.ProfileScreen

@Composable
fun MainNavGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    rootNavController: NavController,
    moviesViewModel: MoviesViewModel
) {


    NavHost(
        navController = navHostController,
        startDestination = NavItem.items[0].route,
        route = Graphs.MAIN_GRAPH
    ) {
        composable(
            MainRouteScreen.HomeScreen.route
        ) {
            HomeScreen(moviesViewModel = moviesViewModel, onDetailsButtonClick = {
                rootNavController.navigate("${HomeRouteScreen.MovieDetail.route}/$it")
            })

        }
        composable(
            MainRouteScreen.CommentsScreen.route
        ) {
            CommentScreen()

        }
        composable(
            MainRouteScreen.ProfileScreen.route
        ) {
            ProfileScreen(onItemClick = {
                rootNavController.navigate("${ProfileRoutesScreen.MovieDetail.route}/$it")
            })
        }


    }

}

