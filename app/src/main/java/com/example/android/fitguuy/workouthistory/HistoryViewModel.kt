package com.example.android.fitguuy.workouthistory

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

import com.example.android.fitguuy.database.RepositoryImpl
import com.example.android.fitguuy.database.Workout


//TODO Husk ingen Activity eller Fragment i viewmodel ettersom den lever gjerne lengre

open class HistoryViewModel(databaseHelperImpl: RepositoryImpl, application: Application) :
    AndroidViewModel(application) {

    val allWorkouts: LiveData<List<Workout>> = databaseHelperImpl.getAllWorkout()
}