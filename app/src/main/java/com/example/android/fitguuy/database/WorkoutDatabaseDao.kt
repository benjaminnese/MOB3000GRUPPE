package com.example.android.fitguuy.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface WorkoutDatabaseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(workout: Workout)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(exercise: Exercise)


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

    @Query("DELETE FROM workout_history_table")
    suspend fun deleteAllWor()


    //---------------WorkoutSession---------------------------//


    @Query("DELETE FROM sqlite_sequence where name='exercise_plan_table'")
    suspend fun clearETab()

    @Query("SELECT * FROM exercise_plan_table WHERE id = :key")
    fun getExerciseplan(key: String):LiveData<List<Exercise>>

    @Query("SELECT * FROM exercise_plan_table ORDER BY id DESC LIMIT 1")
    suspend fun getLastExercise(): Exercise

    @Query("SELECT * FROM exercise_plan_table WHERE part_of_name='default'" )
    fun getDefaultExercisePlan(): LiveData<List<Exercise>?>

    @Query("DELETE FROM sqlite_sequence where name='workout_history_table'")
    suspend fun clearWTab()

    @Query("DELETE FROM exercise_plan_table")
    suspend fun deleteAllEx()



}