package com.example.moviecatalog.core.data.source

import com.example.moviecatalog.core.data.NetworkBoundResource
import com.example.moviecatalog.core.data.Resource
import com.example.moviecatalog.core.data.source.local.LocalDataSource
import com.example.moviecatalog.core.data.source.remote.RemoteDataSource
import com.example.moviecatalog.core.data.source.remote.network.ApiResponse
import com.example.moviecatalog.core.data.source.remote.response.GetPopularMovieResponseItem
import com.example.moviecatalog.core.domain.model.Movie
import com.example.moviecatalog.core.domain.repository.IMovieRepository
import com.example.moviecatalog.core.utils.AppExecutors
import com.example.moviecatalog.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IMovieRepository {
    override fun getAllPopularMovie(apiKey: String): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<GetPopularMovieResponseItem>>(){
            override fun loadFromDb(): Flow<List<Movie>> {
                return localDataSource.getAllMovie().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<GetPopularMovieResponseItem>>> =
                remoteDataSource.getPopularMovie(apiKey)

            override suspend fun saveCallResult(data: List<GetPopularMovieResponseItem>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

        }.asFlow()

    override fun getFavoriteMovie(): Flow<List<Movie>> =
        localDataSource.getFavoriteMovie().map {
            DataMapper.mapEntitiesToDomain(it)
        }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute{localDataSource.setFavoriteMovie(movieEntity, state)}
    }
}