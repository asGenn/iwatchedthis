package com.example.iwatchedthis.data.source.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopMoviesItem(
    @SerialName("big_image")
    val bigImage: String,
    val description: String,
    val genre: List<String>,
    val id: String,
    val image: String,
    @SerialName("imdb_link")
    val imdbLink: String,
    val imdbid: String,
    val rank: Int,
    val rating: Double,
    val thumbnail: String,
    val title: String,
    val year: Int
)