package com.rizfan.moviews.core.domain.repository

import com.rizfan.moviews.core.data.Resource
import com.rizfan.moviews.core.domain.model.Movies
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getAllMovies() : Flow<Resource<List<Movies>>>
    fun getFavoriteMovies() : Flow<List<Movies>>
    fun setFavoriteMovies(movies: Movies, state: Boolean)
    fun searchMovies(query: String) : Flow<Resource<List<Movies>>>
    fun getThemeSetting() : Flow<Boolean>
    suspend fun setThemeSetting(state: Boolean)
}