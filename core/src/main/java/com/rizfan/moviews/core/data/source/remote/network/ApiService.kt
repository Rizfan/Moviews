package com.rizfan.moviews.core.data.source.remote.network

import com.rizfan.moviews.core.data.source.remote.response.DetailMovieResponse
import com.rizfan.moviews.core.data.source.remote.response.ListMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("trending/movie/day")
    suspend fun getMovies(): ListMoviesResponse

    @GET("movie/{movie_id}")
    suspend fun getDetailMovie(
        @Path("movie_id") movie_id: Int
    ): DetailMovieResponse

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("language") language: String = "id-ID"
    ): ListMoviesResponse
}