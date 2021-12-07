package com.example.android.fitguuy.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface WorkoutDatabaseDao {

    //Henter alle exercise som har workoutId som fremmednøkkel
    @Query(
        "SELECT *"
                + " FROM exercises " +
                "INNER JOIN workout ON refKey = workout.workoutId" +
                " WHERE :arg0 = refKey"
    )
    fun findExerciseForWorkout(arg0: Int): LiveData<List<Exercise>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWorkout(workout: Workout)

    @Query("UPDATE workout SET active=:active WHERE workoutId = :workoutId")
    fun updateWorkout(active: Boolean, workoutId: Int)

    @Query("UPDATE exercises SET reps = :reps, sets = :sets, weight = :weight WHERE exerciseID = :exerciseID")
    fun updateExercise(reps: Int, sets: Int, weight: Int, exerciseID: Int)

    @Query("SELECT * FROM exercises WHERE exerciseID = :arg0 LIMIT 1")//WHERE workoutId = :key"
    fun getExercise(arg0: Int): LiveData<Exercise>

    @Query("SELECT * FROM exercises WHERE refKey = :arg0")
    fun getExerciseFromWorkoutAsList(arg0:Int): List<Exercise>

    @Query( "SELECT exerciseID"
            + " FROM exercises " +
            "INNER JOIN workout ON refKey = workout.workoutId" +
            " WHERE :arg0 = refKey AND :arg1 = 1 AND :arg2 = name")
    fun getExerciseFromWorkoutWithNameAsParam(arg0: Int, arg1: Boolean, arg2: String): Int

    @Query("SELECT * FROM exercises")
    fun getAllExercise(): LiveData<List<Exercise>>

    @Query("SELECT * FROM workout")
    fun getAllWorkoutAsList(): List<Workout>

    @Query("SELECT * FROM workout")
    fun getAllWorkout(): LiveData<List<Workout>>

    @Query("SELECT * FROM workout WHERE active = 1 ORDER BY workoutId DESC LIMIT 1")
    fun getCurrentActiveWorkout(): Workout

    @Query("SELECT * FROM workout ORDER BY workoutId DESC LIMIT 1")
    fun getLastWorkout(): Workout

    @Query("SELECT * FROM exercises  ORDER BY exerciseID DESC LIMIT 5")
    fun getExercisePlan(): LiveData<List<Exercise>>

    @Insert
    fun insertExercise(exercise: Exercise)

//TODO Ubrukte, ikke ferdig implementerte dao spørringer
//
//    @Query("SELECT * FROM exercises ORDER BY exerciseID ASC")
//    fun getAllExercises(): Flow<List<Exercise>>
//
//    @Query("SELECT COUNT(workoutId) FROM workout")
//    fun getRowCount(): Flow<Int>
//
//    @Query("SELECT * FROM exercises WHERE exerciseID = :arg0")
//    fun getExerciseplan(arg0: Int): Flow<List<Exercise>>
//
//    @Query("SELECT * FROM workout WHERE workoutId = (SELECT MAX(workoutId) FROM workout) ")
//    suspend fun getLastExercise(): Exercise
//
//
//    @Query("DELETE FROM exercises")
//    suspend fun deleteAllEx()
//
//    @Transaction
//    @Query("SELECT * FROM exercises WHERE exerciseID IN (:arg0)")
//    fun getAllExerciesFromWorkout(arg0: Int): Flow<List<Exercise>>

//    @Query("SELECT * FROM exercises")
//    fun getAllAsList(): Flow<List<Exercise>>
//
//    @Query("SELECT EXISTS(SELECT 1 FROM workout WHERE workoutId = :workid LIMIT 1)")
//    suspend fun getLast(workid:String): Workout
//
//    fun deleteAll()
//
//    @Query("DELETE FROM Workout WHERE workoutId = :key")
//    fun delete(key: Workout) {
//
//    }
//
//
//    @Query("DELETE FROM Workout")
//    suspend fun deleteAllWor()
//
//
}