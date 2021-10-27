package com.example.android.fitguuy.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface WorkoutDatabaseDao {


    @Insert()
     fun insert(exercise: Exercise)

    @Insert()
     fun insert(workout: Workout)

    @Update
     fun update(workout: Workout)

    @Query("SELECT * FROM workout_history_table WHERE id = :key")
     fun get(key: Long): Workout?

    @Query("SELECT * FROM workout_history_table WHERE id = :key")
     fun getID(key: Long): Workout?

    @Query("DELETE FROM workout_history_table")
     fun clear()

    @Query("SELECT * FROM workout_history_table ORDER BY id ASC")
     fun getAll(): LiveData<List<Workout>>

    @Query("SELECT * FROM workout_history_table ORDER BY id DESC LIMIT 1")
     fun getLast(): Workout

    @Query("DELETE FROM workout_history_table")
     fun deleteAll()
     @Query("DELETE FROM workout_history_table WHERE id = :key")
    fun delete(key:Workout) {

    }
    @Query("DELETE FROM sqlite_sequence where name='workout_history_table'")
    suspend fun clearWTab()

    @Query("DELETE FROM workout_history_table")
    suspend fun deleteAllWor()


    //---------------WorkoutSession---------------------------//


    @Query("DELETE FROM sqlite_sequence where name='exercise_plan_table'")
    suspend fun clearExerciseTab()

    @Query("SELECT * FROM exercise_plan_table WHERE id = :key")
    fun getExerciseplan(key: String):LiveData<List<Exercise>>

    @Query("SELECT * FROM exercise_plan_table ORDER BY id DESC LIMIT 1")
    suspend fun getLastExercise(): Exercise

    @Query("SELECT * FROM exercise_plan_table WHERE part_of_name='default'" )
    fun getDefaultExercisePlan(): LiveData<List<Exercise>?>



    @Query("DELETE FROM exercise_plan_table")
    suspend fun deleteAllEx()



}