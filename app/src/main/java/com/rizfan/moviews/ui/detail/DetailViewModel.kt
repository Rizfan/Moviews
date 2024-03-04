package com.rizfan.moviews.ui.detail

import androidx.lifecycle.ViewModel
import com.rizfan.moviews.core.domain.model.Movies
import com.rizfan.moviews.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun setFavoriteMovie(movies: Movies, newStatus: Boolean) =
        movieUseCase.setFavoriteMovies(movies, newStatus)
}