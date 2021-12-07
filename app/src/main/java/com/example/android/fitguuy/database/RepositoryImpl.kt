package com.example.android.fitguuy.database

import android.util.Log
import androidx.lifecycle.LiveData

//https://blog.mindorks.com/room-database-with-kotlin-coroutines-in-android
//https://stackoverflow.com/questions/46847734/private-constructor-in-kotlin

//Using private to make object singleton with having to specify
class RepositoryImpl private constructor(
    private val workoutDatabaseDao: WorkoutDatabaseDao
) /*: Repository */ {

    //Liste med default øvelser som blir hentet inn når bruker opprett nye workout
    private val defaultExercisePlan: List<String> =
        listOf("Benchpress", "Bulgarian", "Squat", "Row", "Arnold")

    init {
        defaultExercisePlan()
    }

    fun insertWorkout(workout: Workout) {
        workoutDatabaseDao.insertWorkout(workout)
        Log.i("DatabaseHelperImpl", "insert kjørt")
    }

    fun getLastWorkout(): Workout = workoutDatabaseDao.getCurrentActiveWorkout()

    fun getAllWorkout(): LiveData<List<Workout>> {
        return workoutDatabaseDao.getAllWorkout()
    }

    fun getAllAsList(): List<Workout> =
        workoutDatabaseDao.getAllWorkoutAsList()

    fun updateWorkout(active: Boolean, workoutId: Int) {
        workoutDatabaseDao.updateWorkout(active, workoutId)
    }

    fun findExerciseForWorkout(arg0: Int): LiveData<List<Exercise>> {
        return workoutDatabaseDao.findExerciseForWorkout(arg0)
    }

    fun insertExercise(exercise: Exercise) {
        workoutDatabaseDao.insertExercise(exercise)
        Log.i("DatabaseHelperImpl", "insert kjørt")

    }

    fun get(exerciseID: Int): LiveData<Exercise> =
        workoutDatabaseDao.getExercise(exerciseID)

    fun getExerciseFromWorkoutWithNameAsParam(arg0: Int, arg1: Boolean, arg2: String): Int{
        return workoutDatabaseDao.getExerciseFromWorkoutWithNameAsParam(arg0, arg1, arg2)
    }

    fun getExerciseFromWorkoutAsList(arg0:Int): List<Exercise> {
        return workoutDatabaseDao.getExerciseFromWorkoutAsList(arg0)
    }


    fun getDefaultExercisePlan(): LiveData<List<Exercise>> {
        return workoutDatabaseDao.getExercisePlan()
    }

    fun updateExercise(reps: Int, sets: Int, weight: Int, exerciseID: Int){
        workoutDatabaseDao.updateExercise(reps,sets,weight,exerciseID)
    }

    fun defaultExercisePlan() {
        workoutDatabaseDao.insertWorkout(Workout(0, 24, true))
        workoutDatabaseDao.insertExercise(Exercise(0, defaultExercisePlan[0], 5, 5, 70, 1))
        workoutDatabaseDao.insertExercise(Exercise(0, defaultExercisePlan[1], 5, 5, 20, 1))
        workoutDatabaseDao.insertExercise(Exercise(0, defaultExercisePlan[2], 5, 5, 45, 1))
        workoutDatabaseDao.insertExercise(Exercise(0, defaultExercisePlan[3], 5, 5, 30, 1))
        workoutDatabaseDao.insertExercise(Exercise(0, defaultExercisePlan[4], 5, 5, 18, 1))
    }


    fun clear() {
        TODO("Not yet implemented")
    }

    fun getLast(): Workout {
        TODO("Not yet implemented")
    }

    companion object {
        //TODO Blir logikken feil? Redd for at en repository kan lage krøll om det er mye data aksess
        private var workoutDatabaseDao: WorkoutDatabaseDao? = null

        // For Singleton instantiation
        @Volatile
        private var instance: RepositoryImpl? = null

        fun getInstance(workoutDatabaseDao: WorkoutDatabaseDao) =
            instance ?: synchronized(this) {
                instance ?: RepositoryImpl(workoutDatabaseDao).also { instance = it }
            }
    }
}
