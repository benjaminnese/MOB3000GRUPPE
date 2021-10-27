package com.example.android.fitguuy.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise_plan_table")
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "part_of_name")
    var partOfName: String = "",
    @ColumnInfo(name = "name")
    var name: String = "",
    @ColumnInfo(name = "sets")
    var sets: Long = 0,
    @ColumnInfo(name = "reps")
    var reps: Long = 0
)

