package com.example.iwatchedthis.data.source.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.iwatchedthis.data.source.local.entitiy.UserProfile

@Dao
interface UserProfileDao {
    @Query("SELECT name FROM user_profile WHERE id = 1")
    suspend fun getUserName(): String?

    @Upsert
    suspend fun insertUserName(userProfile: UserProfile)

}