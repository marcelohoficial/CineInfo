package com.example.cineinfo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.cineinfo.ui.screens.HomeScreen
import com.example.cineinfo.ui.screens.MovieDetailScreen
import com.example.cineinfo.ui.screens.ProfileScreen

object AppDestinations {
    const val HOME = "home"
    const val MOVIE_DETAIL_ROUTE = "movie_detail"
    const val PROFILE = "profile"
}

@Composable
fun AppNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AppDestinations.HOME
    ) {
        composable(route = AppDestinations.HOME) {
            HomeScreen(navController = navController)
        }

        composable(
            route = "${AppDestinations.MOVIE_DETAIL_ROUTE}/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId")

            MovieDetailScreen(movieId = movieId, navController = navController)
        }

        composable(route = AppDestinations.PROFILE) {
            ProfileScreen(navController = navController)
        }
    }
}