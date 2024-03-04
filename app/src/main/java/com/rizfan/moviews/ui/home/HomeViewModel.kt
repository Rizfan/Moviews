package com.rizfan.moviews.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rizfan.moviews.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {

    var movies = movieUseCase.getAllMovies().asLiveData()
}