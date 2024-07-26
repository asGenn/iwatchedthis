package com.example.iwatchedthis.presentation.screens.home.composable

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iwatchedthis.data.Result
import com.example.iwatchedthis.data.source.network.model.Movies
import com.example.iwatchedthis.data.source.network.repository.MoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CustomSearchBarViewModel(
    private val moviesRepository: MoviesRepository
) : ViewModel() {
    private val _queryText = MutableStateFlow<String>("")
    val queryText = _queryText.asStateFlow()
    private val active = MutableStateFlow<Boolean>(false)
    val isActive = active.asStateFlow()

    private val _movies = MutableStateFlow(Movies(emptyList(), false))
    val movies = _movies.asStateFlow()

    fun onQueryChange(query: String) {
        _queryText.value = query
    }

    fun onActiveChange(isActive: Boolean) {
        active.value = isActive
    }

    fun getMoviesList(movieName: String) {
        viewModelScope.launch {
            moviesRepository.getMoviesList(movieName).collectLatest { result ->
                when (result) {
                    is Result.Error -> active.emit(false)
                    is Result.Success -> result.data?.let { movies ->
                        _movies.update { movies }
                    }
                }

            }
        }
    }


}