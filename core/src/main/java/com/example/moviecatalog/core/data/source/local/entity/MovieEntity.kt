package com.example.moviecatalog.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "id_movie") var idMovie: Int,
    @ColumnInfo(name = "name_movie") var nameMovie: String,
    @ColumnInfo(name = "overview") var overview: String,
    @ColumnInfo(name = "img_movie") var imgMovie: String,
    @ColumnInfo(name = "isFavorite") var isFavorite: Boolean = false
)
