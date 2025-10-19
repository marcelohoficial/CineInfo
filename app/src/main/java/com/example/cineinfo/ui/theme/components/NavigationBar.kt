package com.example.cineinfo.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.cineinfo.R
import com.example.cineinfo.navigation.AppDestinations

data class BottomNavItem(
    val label: String,
    val icon: @Composable () -> Unit,
    val selectedIcon: @Composable () -> Unit,
    val route: String
)

@Composable
fun AppBottomNavigationBar(navController: NavController) {
    val navItems = listOf(
        BottomNavItem(
            label = "Início",
            icon = { Icon(Icons.Default.Home, contentDescription = "Início", tint = Color.LightGray) },
            selectedIcon = { Icon(Icons.Default.Home, contentDescription = "Início", tint = Color(0xFFBB86FC)) },
            route = AppDestinations.HOME
        ),
        BottomNavItem(
            label = "Procurar",
            icon = { Icon(Icons.Default.Search, contentDescription = "Procurar", tint = Color.LightGray) },
            selectedIcon = { Icon(Icons.Default.Search, contentDescription = "Procurar", tint = Color(0xFFBB86FC)) },
            route = "search"
        ),
        BottomNavItem(
            label = "Favoritos",
            icon = { Icon(Icons.Default.Star, contentDescription = "Favoritos", tint = Color.LightGray) },
            selectedIcon = { Icon(Icons.Default.Star, contentDescription = "Favoritos", tint = Color(0xFFBB86FC)) },
            route = AppDestinations.PROFILE
        )
    )

    NavigationBar(
        containerColor = Color(0xFF1E1C2E),
        contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        navItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
                icon = { if (currentRoute == item.route) item.selectedIcon() else item.icon() },
                label = { Text(item.label) },
                colors = NavigationBarItemDefaults.colors(
                    selectedTextColor = Color(0xFFBB86FC),
                    unselectedTextColor = Color.LightGray,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}