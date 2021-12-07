package com.example.android.fitguuy.record

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.android.fitguuy.database.Exercise
import com.example.android.fitguuy.database.RepositoryImpl
import com.example.android.fitguuy.database.Workout
import com.example.android.fitguuy.utils.FillDatabaseWithContentWorker
import kotlinx.coroutines.launch
import java.util.*


class RecordViewModel(
    private val repositoryImpl: RepositoryImpl, application: Application
) : AndroidViewModel(application) {
    //Får inn liste med 5 øvelser som standard
    //Etter planen skulle vi lage metoder for å modifisere den defaulte planen
    var allExercise: LiveData<List<Exercise>> = repositoryImpl.getDefaultExercisePlan()

    fun insertExercise(exercise: Exercise) = viewModelScope.launch {
        repositoryImpl.insertExercise(exercise)
    }

    //TODO Basert på aktiv statusen til workout, hente ut den ene workouten som enda ikke er fullført
    //event lage ny hvis den retunere null
    fun getActiveWorkout() {
        // init()
        var workout: Workout = repositoryImpl.getLastWorkout()
        //
        if (workout == null)
            workout = Workout(0, Calendar.HOUR_OF_DAY, true)
        repositoryImpl.insertWorkout(workout)
        if (workout.active)
            allExercise = repositoryImpl.findExerciseForWorkout(workout.workoutId)
        else {
            Log.i("Workout1", workout.toString())
            repositoryImpl.insertWorkout(Workout(0, Calendar.HOUR_OF_DAY, true))
            allExercise = repositoryImpl.findExerciseForWorkout(workout.workoutId)
        }
    }

    //TODO Her var planen for oppdaterting av øvelsene
    fun updateExercise(reps: Int, sets: Int, weight: Int,exerciseName: String) = viewModelScope.launch{
        val currentWorkout = repositoryImpl.getLastWorkout().workoutId.toInt()
        val exerciseId: Int = repositoryImpl.getExerciseFromWorkoutWithNameAsParam(currentWorkout,true,exerciseName )
        repositoryImpl.updateExercise(reps,sets,weight,exerciseId)
    }


}







