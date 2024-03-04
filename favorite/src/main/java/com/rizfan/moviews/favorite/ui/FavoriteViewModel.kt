package com.rizfan.moviews.favorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rizfan.moviews.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel(){

    val favoriteMovies = movieUseCase.getFavoriteMovies().asLiveData()

}