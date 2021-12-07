package com.example.android.fitguuy.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workout")//,

data class Workout(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "workoutId")
    var workoutId: Int = 0,
    @ColumnInfo(name = "date")
    var date: Int = 0,
    @ColumnInfo(name = "active")
    var active: Boolean = true
)

