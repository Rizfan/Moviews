package com.rizfan.moview.favorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rizfan.moview.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel(){

    val favoriteMovies = movieUseCase.getFavoriteMovies().asLiveData()

}