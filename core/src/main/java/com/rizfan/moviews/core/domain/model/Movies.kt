package com.rizfan.moviews.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movies(
    val movieId: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val isFavorite: Boolean
):Parcelable
