package com.example.iwatchedthis.presentation.screens.home.composable

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.iwatchedthis.data.source.network.repository.MoviesRepositoryImp
import com.example.iwatchedthis.utils.RetrofitInstance

class CustomSearchBarViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(CustomSearchBarViewModel::class.java) -> {
                val repository = MoviesRepositoryImp(RetrofitInstance.collectApi)
                CustomSearchBarViewModel(repository) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}