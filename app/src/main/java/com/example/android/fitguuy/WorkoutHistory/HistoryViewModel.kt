package com.example.android.fitguuy.WorkoutHistory

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.android.fitguuy.Repository.TempRepositoy
import com.example.android.fitguuy.database.Workout


//Husk ingen Activity eller Fragment i viewmodel ettersom den lever gjerne lengre
open class HistoryViewModel(application: Application) : AndroidViewModel(application) {


    private lateinit var repository: TempRepositoy
    private lateinit var allWorkouts: LiveData<List<Workout>>



   init{
       repository = TempRepositoy(application)
       allWorkouts = repository.allWorkout
   }
    fun insert (workout: Workout){
        repository.insert(workout)
    }
    fun update (workout: Workout){
        repository.update(workout)
    }
    fun delete (workout: Workout){
        repository.delete(workout)
    }
    fun deleteAll (){
        repository.deleteAllWorkouts()
    }
    fun getAllWorkouts(): LiveData<List<Workout>>{
        return allWorkouts
    }
}