package com.example.cineinfo.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cineinfo.data.local.MovieEntity
import com.example.cineinfo.data.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class MoviesUiState(
    val loading: Boolean = false,
    val movies: List<MovieEntity> = emptyList(),
    val searchResults: List<MovieEntity> = emptyList(),
    val error: String? = null
)

class MoviesViewModel(private val repo: MovieRepository) : ViewModel() {
    private val _ui = MutableStateFlow(MoviesUiState(loading = true))
    val ui: StateFlow<MoviesUiState> = _ui.asStateFlow()

    init {
        // Observa DB e atualiza UI
        viewModelScope.launch {
            repo.moviesFlow.collect { list ->
                _ui.value = _ui.value.copy(movies = list, loading = false, error = null)
            }
        }

        // Tenta sincronizar com a rede
        viewModelScope.launch {
            try {
                _ui.value = _ui.value.copy(loading = true)
                repo.refreshFromNetwork()
            } catch (e: Exception) {
                _ui.value = _ui.value.copy(error = e.message, loading = false)
            }
        }
    }

    fun search(query: String) {
        if (query.isBlank()) {
            _ui.value = _ui.value.copy(searchResults = emptyList())
            return
        }
        viewModelScope.launch {
            try {
                _ui.value = _ui.value.copy(loading = true)
                val results = repo.searchMovies(query)
                _ui.value = _ui.value.copy(searchResults = results, loading = false)
            } catch (e: Exception) {
                _ui.value = _ui.value.copy(error = e.message, loading = false)
            }
        }
    }

    fun getMovieById(id: Int): MovieEntity? {
        return ui.value.movies.find { it.id == id } ?: ui.value.searchResults.find { it.id == id }
    }

    class Factory(private val repo: MovieRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return MoviesViewModel(repo) as T
        }
    }
}