package com.example.iwatchedthis.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iwatchedthis.data.Result
import com.example.iwatchedthis.data.source.network.model.TopMoviesItem
import com.example.iwatchedthis.data.source.network.repository.TopMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val topMoviesRepository: TopMoviesRepository
) : ViewModel() {

    private val _movies = MutableStateFlow<List<TopMoviesItem>>(emptyList())
    val movies = _movies.asStateFlow()

    private val _showToastChannel = Channel<Boolean> { }
    val showToastChannel = _showToastChannel.receiveAsFlow()

    init {
        viewModelScope.launch {

            topMoviesRepository.getTopMoviesList().collectLatest { result ->
                when (result) {
                    is Result.Error -> _showToastChannel.send(true)
                    is Result.Success -> result.data?.let { movies ->
                        _movies.update { movies }
                    }
                }
            }
        }
    }
}