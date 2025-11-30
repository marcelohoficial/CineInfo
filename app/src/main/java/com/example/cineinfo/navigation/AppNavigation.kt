package com.example.cineinfo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.cineinfo.ui.movies.MoviesViewModel
import com.example.cineinfo.ui.screens.HomeScreen
import com.example.cineinfo.ui.screens.MovieDetailScreen // Ensure this import is correct or fix it if it's in theme.screens
import com.example.cineinfo.ui.screens.ProfileScreen
import com.example.cineinfo.ui.screens.SearchScreen

object AppDestinations {
    const val HOME = "home"
    const val MOVIE_DETAIL_ROUTE = "movie_detail"
    const val PROFILE = "profile"
    const val SEARCH = "search"
}

@Composable
fun AppNavigationGraph(navController: NavHostController, viewModel: MoviesViewModel) {
    NavHost(
        navController = navController,
        startDestination = AppDestinations.HOME
    ) {
        composable(route = AppDestinations.HOME) {
            HomeScreen(navController = navController, viewModel = viewModel)
        }

        composable(route = AppDestinations.SEARCH) {
            SearchScreen(navController = navController, viewModel = viewModel)
        }

        composable(
            route = "${AppDestinations.MOVIE_DETAIL_ROUTE}/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId")

            // Note: MovieDetailScreen might need update to fetch from VM or API
            // For now assuming it takes movieId
            MovieDetailScreen(movieId = movieId, navController = navController)
        }

        composable(route = AppDestinations.PROFILE) {
            ProfileScreen(navController = navController)
        }
    }
}