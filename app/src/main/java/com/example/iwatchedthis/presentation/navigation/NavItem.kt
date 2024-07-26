package com.example.iwatchedthis.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.iwatchedthis.presentation.navigation.routes.MainRouteScreen

data class NavItem(
    val route: String,
    val label: String,
    val icon: ImageVector

) {
    companion object {
        val items = listOf(
            NavItem(MainRouteScreen.HomeScreen.route, "Home", Icons.Default.Home),
            NavItem(MainRouteScreen.CommentsScreen.route, "Comments", Icons.Default.AddCircle),
            NavItem(MainRouteScreen.ProfileScreen.route, "Profile", Icons.Default.Person)

        )
    }

}