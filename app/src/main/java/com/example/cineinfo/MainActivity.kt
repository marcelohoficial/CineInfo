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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Alignment
import androidx.compose.material3.CircularProgressIndicator

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
                    var isLoading by remember { mutableStateOf(true) }

                    LaunchedEffect(Unit) {
                        delay(2000)
                        isLoading = false
                    }

                    if (isLoading) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    } else {
                        val navController = rememberNavController()
                        AppNavigationGraph(navController = navController, viewModel = vm)
                    }
                }
            }
        }
    }
}