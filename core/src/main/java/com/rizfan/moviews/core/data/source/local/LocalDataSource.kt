package com.rizfan.moviews.core.data.source.local

import com.rizfan.moviews.core.data.source.local.entity.MovieEntity
import com.rizfan.moviews.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val movieDao: MovieDao){

    fun getAllMovies() : Flow<List<MovieEntity>> = movieDao.getAllMovies()

    fun searchMovies(query: String) : Flow<List<MovieEntity>> = movieDao.searchMovies(query)

    fun getFavoriteMovies() : Flow<List<MovieEntity>> = movieDao.getFavoriteMovies()

    suspend fun insertMovies(movies: List<MovieEntity>) = movieDao.insertMovies(movies)
    fun setFavoriteMovies(movies: MovieEntity, newState: Boolean) {
        movies.isFavorite = newState
        movieDao.updateFavoriteMovies(movies)
    }
}