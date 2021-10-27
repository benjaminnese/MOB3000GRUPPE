package com.example.android.fitguuy.database
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "workout_history_table")

data class Workout(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "date")
    val date: Long = 0,
    @ColumnInfo(name = "1_exercise_type")
    var type1: String = "",
    @ColumnInfo(name = "1_exercise_reps")
    var reps1: String = "",
    @ColumnInfo(name = "1_exercise_kg")
    var weight1: String = "-1",
    @ColumnInfo(name = "2_exercise_type")
    var type2: String = "",
    @ColumnInfo(name = "2_exercise_reps")
    var reps2: String = "",
    @ColumnInfo(name = "2_exercise_kg")
    var weight2: String = "-1",
    @ColumnInfo(name = "3_exercise_type")
    var type3: String = "",
    @ColumnInfo(name = "3_exercise_reps")
    var reps3: String = "",
    @ColumnInfo(name = "3_exercise_kg")
    var weight3: String = "-1",
    @ColumnInfo(name = "4_exercise_type")
    var type4: String = "",
    @ColumnInfo(name = "4_exercise_reps")
    var reps4: String = "",
    @ColumnInfo(name = "4_exercise_kg")
    var weight4: String = "-1",
    @ColumnInfo(name = "5_exercise_type")
    var type5: String = "",
    @ColumnInfo(name = "5_exercise_reps")
    var reps5: String = "",
    @ColumnInfo(name = "5_exercise_kg")
    var weight5: String = "-1",
    @ColumnInfo(name = "6_exercise_type")
    var type6: String = "",
    @ColumnInfo(name = "6_exercise_reps")
    var reps6: String = "",
    @ColumnInfo(name = "6_exercise_kg")
    var weight6: String = "-1"

)

