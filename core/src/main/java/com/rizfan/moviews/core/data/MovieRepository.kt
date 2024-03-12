package com.rizfan.moviews.core.data

import com.rizfan.moviews.core.data.source.local.LocalDataSource
import com.rizfan.moviews.core.data.source.pref.SettingPreferences
import com.rizfan.moviews.core.data.source.remote.RemoteDataSource
import com.rizfan.moviews.core.data.source.remote.network.ApiResponse
import com.rizfan.moviews.core.data.source.remote.response.ResultsItem
import com.rizfan.moviews.core.domain.model.Movies
import com.rizfan.moviews.core.domain.repository.IMovieRepository
import com.rizfan.moviews.core.utils.AppExecutors
import com.rizfan.moviews.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
    private val settingPreferences: SettingPreferences
) : IMovieRepository {
    override fun getAllMovies(): Flow<Resource<List<Movies>>> =
        object : NetworkBoundResource<List<Movies>, List<ResultsItem>>() {
            override fun loadFromDB(): Flow<List<Movies>> {
                return localDataSource.getAllMovies().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Movies>?): Boolean {
                return data.isNullOrEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsItem>>> {
                return remoteDataSource.getAllMovies()
            }

            override suspend fun saveCallResult(data: List<ResultsItem>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovies(movieList)
            }
        }.asFlow()

    override fun searchMovies(query: String): Flow<Resource<List<Movies>>> {
        return object : NetworkBoundResource<List<Movies>, List<ResultsItem>>() {
            override fun loadFromDB(): Flow<List<Movies>> {
                return localDataSource.searchMovies(query).map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Movies>?): Boolean {
                return data.isNullOrEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsItem>>> {
                return remoteDataSource.searchMovie(query)
            }

            override suspend fun saveCallResult(data: List<ResultsItem>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovies(movieList)
            }
        }.asFlow()
    }

    override fun getFavoriteMovies(): Flow<List<Movies>> {
        return localDataSource.getFavoriteMovies().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteMovies(movies: Movies, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movies)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovies(movieEntity, state) }
    }

    override fun getThemeSetting(): Flow<Boolean> {
        return settingPreferences.getThemeSetting()
    }

    override suspend fun setThemeSetting(state: Boolean) {
        settingPreferences.saveThemeSetting(state)
    }
}