package com.example.cineinfo

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.cineinfo.data.local.AppDatabase
import com.example.cineinfo.data.remote.RetrofitClient
import com.example.cineinfo.data.repository.MovieRepository
import com.example.cineinfo.ui.movies.MoviesViewModel
import com.example.cineinfo.navigation.AppNavigationGraph
import com.example.cineinfo.ui.theme.CineInfoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "cine.db")
            .fallbackToDestructiveMigration()
            .build()
        val api = RetrofitClient.api
        // val apiKey = BuildConfig.TMDB_API_KEY
        val apiKey = "3b0556ebbe5c31e1998ac2d186e213b1"
        val repo = MovieRepository(api, db.movieDao(), apiKey)
        val vm = ViewModelProvider(this, MoviesViewModel.Factory(repo)).get(MoviesViewModel::class.java)

        setContent {
            CineInfoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    AppNavigationGraph(navController = navController, viewModel = vm)
                }
            }
        }
    }
}