package com.rizfan.moviews.core.utils

import com.rizfan.moviews.core.data.source.local.entity.MovieEntity
import com.rizfan.moviews.core.data.source.remote.response.ResultsItem
import com.rizfan.moviews.core.domain.model.Movies

object DataMapper {
    fun mapResponsesToEntities(input: List<ResultsItem>):List<MovieEntity>{
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                movieId = it.id,
                title = it.title,
                overview = it.overview,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                isFavorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movies> =
        input.map {
            Movies(
                movieId = it.movieId,
                title = it.title,
                overview = it.overview,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Movies) = MovieEntity(
        movieId = input.movieId,
        title = input.title,
        overview = input.overview,
        posterPath = input.posterPath,
        releaseDate = input.releaseDate,
        isFavorite = input.isFavorite
    )
}