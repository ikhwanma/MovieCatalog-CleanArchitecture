package com.example.moviecatalog.core.data.source.remote

import com.example.moviecatalog.core.data.source.remote.network.ApiResponse
import com.example.moviecatalog.core.data.source.remote.network.ApiService
import com.example.moviecatalog.core.data.source.remote.response.GetPopularMovieResponseItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getPopularMovie(apiKey: String): Flow<ApiResponse<List<GetPopularMovieResponseItem>>>{
        return flow{
            try{
                val response = apiService.getPopularMovie(apiKey)
                val dataArray = response.getPopularMovieResponse
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.getPopularMovieResponse))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e: java.lang.Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}