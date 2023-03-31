package com.example.moviecatalog.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val originalTitle: String,
    val posterPath: String,
    val overview: String,
    val isFavorite: Boolean
): Parcelable
