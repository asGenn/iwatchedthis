package com.example.iwatchedthis.data.source.local.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "user_profile"
)
data class UserProfile(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id", defaultValue = "0")
    val id: Int = 0,
    @ColumnInfo(name = "name", defaultValue = "Enter a name")
    val name: String = "Enter a name",
)