package com.example.moviecatalog.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GetPopularMovieResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val getPopularMovieResponse: List<GetPopularMovieResponseItem>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
