package com.example.moviecatalog.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GetPopularMovieResponse(
    @SerializedName("results")
    val getPopularMovieResponse: List<GetPopularMovieResponseItem>
)
