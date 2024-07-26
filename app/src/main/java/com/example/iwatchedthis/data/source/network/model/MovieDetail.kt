package com.example.iwatchedthis.data.source.network.model

import kotlinx.serialization.Serializable

@Serializable
data class MovieDetail(
    val result: Result,
    val success: Boolean
)