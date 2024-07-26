package com.example.iwatchedthis.presentation.screens.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iwatchedthis.data.Result
import com.example.iwatchedthis.data.source.local.entitiy.FavoriteMovies
import com.example.iwatchedthis.data.source.local.entitiy.WatchedMovies
import com.example.iwatchedthis.data.source.local.repository.UserMoviesRepository
import com.example.iwatchedthis.data.source.network.model.MovieDetail
import com.example.iwatchedthis.data.source.network.repository.MoviesRepository
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
class DetailsScreenViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
    savedStateHandle: SavedStateHandle,
    private val userMoviesRepository: UserMoviesRepository
) : ViewModel() {
    private val movieId: String =
        savedStateHandle["movieId"] ?: throw IllegalArgumentException("Movie ID is required")

    private val _movieDetail = MutableStateFlow<MovieDetail?>(null)
    val movieDetail = _movieDetail.asStateFlow()

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite = _isFavorite.asStateFlow()
    private val _isWatched = MutableStateFlow(false)
    val isWatched = _isWatched.asStateFlow()


    private val _showCommentDialogChannel = Channel<String>(Channel.CONFLATED) { }
    val showCommentDialogChannel = _showCommentDialogChannel.receiveAsFlow()
    fun addCommentToDb(imdbId: String, comment: String, movieName: String) {
        viewModelScope.launch {
            try {
                userMoviesRepository.insertComment(imdbId, comment, movieName)
                _showCommentDialogChannel.send("Notunuz eklendi")
            } catch (e: Exception) {
                _showCommentDialogChannel.send("Not eklenirken bir hata oluştu")

            }

        }
    }

    init {
        viewModelScope.launch {
            userMoviesRepository.isWatched(movieId).let { isWatched ->
                _isWatched.update {
                    isWatched

                }
            }
            userMoviesRepository.isFavorite(movieId).let { isFavorite ->
                _isFavorite.update {
                    isFavorite

                }
            }
        }
    }

    fun toggleFavorite() {
        viewModelScope.launch {
            if (isFavorite.value) {
                userMoviesRepository.deleteFavoriteMovie(movieId)
                _showCommentDialogChannel.send("Favorilerden çıkarıldı")
            } else {
                _movieDetail.value?.let {
                    addFavoriteMovieToDb(
                        FavoriteMovies(
                            imdbID = it.result.imdbID,
                            title = it.result.title,
                            year = it.result.year,
                            poster = it.result.poster,
                            type = it.result.type
                        )
                    )
                }
            }
            _isFavorite.update {
                !it
            }
        }
    }

    fun toggleWatched() {
        viewModelScope.launch {
            if (isWatched.value) {
                userMoviesRepository.deleteWatchedMovie(movieId)
                _showCommentDialogChannel.send("İzlediklerimden çıkarıldı")
            } else {
                _movieDetail.value?.let {
                    addWatchedMovieToDb(
                        WatchedMovies(
                            imdbID = it.result.imdbID,
                            title = it.result.title,
                            year = it.result.year,
                            poster = it.result.poster,
                            type = it.result.type
                        )
                    )
                }
            }
            _isWatched.update {
                !it
            }
        }
    }

    private fun addWatchedMovieToDb(watchedMovies: WatchedMovies) {
        viewModelScope.launch {
            try {
                userMoviesRepository.insertWatchedMovie(watchedMovies)
                _showCommentDialogChannel.send("İzlediklerime eklendi")
            } catch (e: Exception) {
                _showCommentDialogChannel.send("İzlediklerime eklenirken bir hata oluştu")

            }

        }
    }

    private fun addFavoriteMovieToDb(favoriteMovies: FavoriteMovies) {
        viewModelScope.launch {
            try {
                userMoviesRepository.insertFavoriteMovie(favoriteMovies)
                _showCommentDialogChannel.send("Favorilere eklendi")
            } catch (e: Exception) {
                _showCommentDialogChannel.send("Favorilere eklenirken bir hata oluştu")

            }

        }
    }

    init {
        viewModelScope.launch {
            userMoviesRepository.isFavorite(movieId).let {
                _isFavorite.update {
                    it

                }
            }
            moviesRepository.getMovieDetail(movieId).collectLatest { result ->
                when (result) {
                    is Result.Error -> _showCommentDialogChannel.send("Bir hata oluştu")
                    is Result.Success -> result.data?.let { movieDetail ->
                        _movieDetail.update {
                            movieDetail
                        }
                    }
                }

            }

        }
    }


}