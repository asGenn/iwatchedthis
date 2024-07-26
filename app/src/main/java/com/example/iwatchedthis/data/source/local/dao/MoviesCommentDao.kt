package com.example.iwatchedthis.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.iwatchedthis.data.source.local.entitiy.MoviesComment

@Dao
interface MoviesCommentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComment(comment: MoviesComment)

    @Query("SELECT * FROM comments")
    suspend fun getAllComment(): List<MoviesComment>
}