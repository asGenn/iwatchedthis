package com.example.iwatchedthis.presentation.screens.main

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.iwatchedthis.presentation.navigation.NavItem
import com.example.iwatchedthis.presentation.navigation.graphs.MainNavGraph
import com.example.iwatchedthis.presentation.screens.home.MoviesViewModel
import com.example.iwatchedthis.ui.theme.HeiSeBlack
import com.example.iwatchedthis.ui.theme.Orange

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    rootNavController: NavHostController,
    moviesViewModel: MoviesViewModel

) {
    Scaffold(bottomBar = {
        BottomBar(navController)
    }) { padingValues ->
        Modifier.padding(padingValues)
        MainNavGraph(
            modifier = modifier,
            navHostController = navController,
            rootNavController = rootNavController,
            moviesViewModel = moviesViewModel
        )

    }

}

@Composable
fun BottomBar(navController: NavHostController) {
    val navBackStackEntry: NavBackStackEntry? by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination

    val bottomBarDestination = NavItem.items.any() { it.route == currentRoute?.route }
    if (bottomBarDestination) {
        NavigationBar(
            containerColor = HeiSeBlack,
            contentColor = Color.White,

            ) {
            NavItem.items.forEach() { item ->
                AddItem(item, currentRoute, navController)
            }

        }

    }


}

@Composable
fun RowScope.AddItem(item: NavItem, currentRoute: NavDestination?, navController: NavController) {
    NavigationBarItem(
        colors = NavigationBarItemDefaults.colors(
            indicatorColor = Orange,
        ),
        selected = currentRoute?.hierarchy?.any {
            it.route == item.route
        } == true,
        label = {
            Text(text = item.label, color = Color.White)
        },
        onClick = {
            navController.navigate(item.route) {
                popUpTo(navController.graph.startDestinationId) {

                }
                launchSingleTop = true
                restoreState = true

            }
        },
        icon = {
            Icon(imageVector = item.icon, contentDescription = null, tint = Color.White)
        },

        )

}
