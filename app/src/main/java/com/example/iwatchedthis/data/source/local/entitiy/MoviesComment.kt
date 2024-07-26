package com.example.iwatchedthis.data.source.local.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "comments"
)
data class MoviesComment(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id", defaultValue = "0")
    val id: Int = 0,
    val imdbID: String,
    @ColumnInfo(name = "movie_name", defaultValue = "")
    val movieName: String,
    val comment: String

)