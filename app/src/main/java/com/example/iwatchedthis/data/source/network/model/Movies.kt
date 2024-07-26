package com.example.iwatchedthis.data.source.network.model

import kotlinx.serialization.Serializable

@Serializable
data class Movies(
    val result: List<Movie>,
    val success: Boolean
)