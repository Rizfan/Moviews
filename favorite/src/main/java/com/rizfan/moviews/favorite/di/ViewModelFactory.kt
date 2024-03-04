package com.rizfan.moviews.favorite.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rizfan.moviews.core.domain.usecase.MovieUseCase
import com.rizfan.moviews.favorite.ui.FavoriteViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val movieUseCase: MovieUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(movieUseCase) as T
        } else {
            throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}