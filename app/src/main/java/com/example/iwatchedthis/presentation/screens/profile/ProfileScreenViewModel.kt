package com.example.iwatchedthis.presentation.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iwatchedthis.data.source.local.entitiy.FavoriteMovies
import com.example.iwatchedthis.data.source.local.entitiy.UserProfile
import com.example.iwatchedthis.data.source.local.entitiy.WatchedMovies
import com.example.iwatchedthis.data.source.local.repository.UserMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val userMoviesRepository: UserMoviesRepository
) : ViewModel() {
    private val _userName = MutableStateFlow<String>("")
    val userName = _userName.asStateFlow()

    private val _isInEditMode = MutableStateFlow(false)
    val isInEditMode = _isInEditMode.asStateFlow()

    private val _favoriteMovieList = MutableStateFlow<List<FavoriteMovies>>(emptyList())
    val favoriteMovies = _favoriteMovieList.asStateFlow()
    private val _watchedMovieList = MutableStateFlow<List<WatchedMovies>>(emptyList())
    val watchedMovies = _watchedMovieList.asStateFlow()

    init {
        viewModelScope.launch {
            _userName.update {

                if (userMoviesRepository.getUserName() != null) {
                    userMoviesRepository.getUserName()!!
                } else {
                    userMoviesRepository.updateUserName(UserProfile(name = "Enter a name", id = 1))
                    "Enter a name"
                }
            }


        }
        viewModelScope.launch {
            userMoviesRepository.getAllFavoriteMovies().collectLatest { favoriteMovies ->
                _favoriteMovieList.update { favoriteMovies }

            }

            userMoviesRepository.getAllWatchedMovies().collect { watchedMovies ->
                _watchedMovieList.update { watchedMovies }
            }
        }
    }

    fun refreshItems() {
        viewModelScope.launch {
            userMoviesRepository.getAllFavoriteMovies().collectLatest { favoriteMovies ->
                _favoriteMovieList.update { favoriteMovies }

            }

            userMoviesRepository.getAllWatchedMovies().collectLatest { watchedMovies ->
                _watchedMovieList.update { watchedMovies }
            }
        }
    }

    fun onUserNameChange(newName: String) {
        _userName.value = newName
    }

    private fun setUserName(newName: String) {
        viewModelScope.launch {
            userMoviesRepository.updateUserName(UserProfile(name = newName, id = 1))
        }
    }

    fun onEditModeToggle() {
        if (_isInEditMode.value) {
            setUserName(_userName.value)
        }
        _isInEditMode.value = !_isInEditMode.value
    }


}