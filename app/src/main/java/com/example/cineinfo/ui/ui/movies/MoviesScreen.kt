package com.cineinfo.ui.movies

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MoviesScreen(viewModel: MoviesViewModel) {
    val state = viewModel.ui.collectAsState().value

    Box(modifier = Modifier.fillMaxSize().padding(8.dp)) {
        if (state.loading) {
            CircularProgressIndicator(modifier = Modifier.align(androidx.compose.ui.Alignment.Center))
        } else {
            if (state.error != null && state.movies.isEmpty()) {
                Text(
                    text = "Erro: ${state.error}",
                    modifier = Modifier.align(androidx.compose.ui.Alignment.Center)
                )
            }
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.movies) { movie ->
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(movie.title, style = MaterialTheme.typography.titleMedium)
                            Spacer(modifier = Modifier.height(6.dp))
                            Text("Nota: ${movie.voteAverage}", style = MaterialTheme.typography.bodySmall)
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(movie.overview, maxLines = 4, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }
    }
}