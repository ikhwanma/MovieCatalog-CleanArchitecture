package com.example.moviecatalog.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GetPopularMovieResponseItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String
)
