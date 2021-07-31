package com.example.pedulidigital.module

import com.google.gson.annotations.SerializedName

data class Movie(
    val title: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("vote_average")
    val voteAverage: Double
)