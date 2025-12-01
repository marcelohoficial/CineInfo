package com.example.cineinfo.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.cineinfo.ui.movies.MoviesViewModel
import com.example.cineinfo.R
import com.example.cineinfo.navigation.AppDestinations
import com.example.cineinfo.ui.components.AppBottomNavigationBar
import com.example.cineinfo.ui.components.MovieSection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: MoviesViewModel) {
    val state by viewModel.ui.collectAsState()
    val movies = state.movies
    
    // Featured movie is the first one, or null
    val featuredMovie = movies.firstOrNull()
    val trendingMovies = movies.take(10)
    val recommendedMovies = movies.drop(10).take(10)

    Scaffold(
        bottomBar = {
            AppBottomNavigationBar(navController = navController)
        },
        containerColor = Color(0xFF1E1C2E)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFF1E1C2E))
                .verticalScroll(rememberScrollState())
        ) {
            if (state.error != null) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Red.copy(alpha = 0.8f))
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Erro: ${state.error}", color = Color.White)
                }
            }

            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFBB86FC)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(Icons.Default.PlayArrow, contentDescription = "App Logo", tint = Color.White, modifier = Modifier.size(24.dp))
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.avatar_profile),
                        contentDescription = "Avatar do usuário",
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF6200EE))
                            .clickable { navController.navigate(AppDestinations.PROFILE) },
                        contentScale = ContentScale.Crop
                    )
                }
            }
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                Text(text = "Olá,", color = Color.LightGray, fontSize = 20.sp)
                Text(text = "Marcelo Henrique 👋", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (featuredMovie != null) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp)
                        .padding(horizontal = 16.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { navController.navigate("${AppDestinations.MOVIE_DETAIL_ROUTE}/${featuredMovie.id}") }
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        AsyncImage(
                            model = "https://image.tmdb.org/t/p/w500${featuredMovie.posterPath}",
                            contentDescription = featuredMovie.title,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.matchParentSize()
                        )
                        Box(
                            modifier = Modifier
                                .matchParentSize()
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f)),
                                        startY = 0f,
                                        endY = 1000f
                                    )
                                )
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                                .align(Alignment.BottomStart),
                            verticalArrangement = Arrangement.Bottom
                        ) {
                            Text(text = featuredMovie.title, color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                            Text(text = "Nota: ${featuredMovie.voteAverage}", color = Color.LightGray, fontSize = 12.sp, modifier = Modifier.padding(vertical = 4.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row {
                                    IconButton(onClick = { /* Like */ }) {
                                        Icon(Icons.Default.ThumbUp, contentDescription = "Gostei", tint = Color.White)
                                    }
                                    IconButton(onClick = { /* Add to List */ }) {
                                        Icon(Icons.Default.AddCircle, contentDescription = "Adicionar", tint = Color.White)
                                    }
                                }
                                FloatingActionButton(
                                    onClick = {},
                                    containerColor = Color(0xFFBB86FC),
                                    shape = CircleShape,
                                    modifier = Modifier.size(48.dp)
                                ) {
                                    Icon(Icons.Default.PlayArrow, contentDescription = "Assistir", tint = Color.White)
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            MovieSection (
                title = "Em alta",
                movies = trendingMovies,
                onMovieClick = { movie ->
                    navController.navigate("${AppDestinations.MOVIE_DETAIL_ROUTE}/${movie.id}")
                },
                onSeeAllClick = { }
            )

            Spacer(modifier = Modifier.height(24.dp))

            MovieSection(
                title = "Recomendado para Você",
                movies = recommendedMovies,
                onMovieClick = { movie ->
                    navController.navigate("${AppDestinations.MOVIE_DETAIL_ROUTE}/${movie.id}")
                },
                onSeeAllClick = {}
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}