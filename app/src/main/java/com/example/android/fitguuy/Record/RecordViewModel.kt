package com.example.android.fitguuy.Record

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.android.fitguuy.database.Exercise
import com.example.android.fitguuy.database.Workout
import com.example.android.fitguuy.database.WorkoutDatabaseDao
import kotlinx.coroutines.launch


class RecordViewModel (dataSource: WorkoutDatabaseDao,
                       application: Application): ViewModel() {

    val database = dataSource

    private var exercise = MutableLiveData<Exercise>()
    var lastWorkout = MutableLiveData<Workout?>()

    val exerciseplan = database.getDefaultExercisePlan()

    init {
        initializeWorkoutList()
    }

    private fun initializeWorkoutList() {
        viewModelScope.launch {
            //lastWorkout.value = getLastWorkout()
            exercise.value = getWorkoutFromDatabase()
            printExercisePlan()

        }
    }
    private suspend fun getWorkoutFromDatabase(): Exercise {
        var mWorkout = database.getLastExercise()
        // TODO(Legg inn sjekk om det er null verdi?)

        return mWorkout
    }
    private suspend fun getLastWorkout(): Workout? {
        return database.getLast()
    }

    private fun printExercisePlan() {
        Log.i("HistoryViewModel", "exercisePlan: ${database.getDefaultExercisePlan().value}")
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    fun saveWorkoutToDB() {
        viewModelScope.launch {
            val workoutInDb = Workout()
            insert(workoutInDb)
            
        }
    }
    
    private suspend fun insert(workoutInDb: Workout) {
        database.insert(workoutInDb)
    }
}