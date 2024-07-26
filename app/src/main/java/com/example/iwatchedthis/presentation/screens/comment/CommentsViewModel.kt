package com.example.iwatchedthis.presentation.screens.comment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iwatchedthis.data.source.local.entitiy.MoviesComment
import com.example.iwatchedthis.data.source.local.repository.UserMoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentsViewModel @Inject constructor(
    userMoviesRepository: UserMoviesRepository
) : ViewModel() {

    private val _comments = MutableStateFlow(emptyList<MoviesComment>())
    val comments = _comments.asStateFlow()

    init {
        viewModelScope.launch {
            userMoviesRepository.getAllComment().collectLatest { comments ->
                _comments.update {
                    comments
                }
            }
        }
    }


}