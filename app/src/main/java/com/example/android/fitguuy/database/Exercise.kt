package com.example.android.fitguuy.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
//Skaper et en til mange forhold mellom workout og exercise
@Entity(
    foreignKeys = [ForeignKey(
        entity = Workout::class,
        parentColumns = arrayOf("workoutId"),
        childColumns = arrayOf("refKey"),
        onDelete = CASCADE
    )],
    tableName = "exercises"
)
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "exerciseID")
    val exerciseID: Int,
    @ColumnInfo(name = "name")
    var name: String = "",
    @ColumnInfo(name = "sets")
    var sets: Int = 5,
    @ColumnInfo(name = "reps")
    var reps: Int = 5,
    @ColumnInfo(name = "weight")
    var weight: Int = 0,
    @ColumnInfo(name = "refKey")
    var refKey: Int = 0

)


