package com.rizfan.moviews.core.domain.usecase

import com.rizfan.moviews.core.domain.model.Movies
import com.rizfan.moviews.core.domain.repository.IMovieRepository
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val movieRepository: IMovieRepository) :
    MovieUseCase {
    override fun getAllMovies() = movieRepository.getAllMovies()

    override fun getFavoriteMovies() = movieRepository.getFavoriteMovies()

    override fun setFavoriteMovies(movies: Movies, state: Boolean) = movieRepository.setFavoriteMovies(movies, state)
    override fun searchMovies(query: String) = movieRepository.searchMovies(query)
    override fun getThemeSetting() = movieRepository.getThemeSetting()
    override suspend fun setThemeSetting(state: Boolean) = movieRepository.setThemeSetting(state)
}