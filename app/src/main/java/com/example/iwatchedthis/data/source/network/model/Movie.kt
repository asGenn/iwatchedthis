package com.example.iwatchedthis.data.source.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val Poster: String,
    @SerialName("Title")
    val title: String,

    val Type: String,
    val Year: String,
    val imdbID: String
)