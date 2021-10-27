package com.example.android.fitguuy.Repository

import androidx.lifecycle.LiveData
import com.example.android.fitguuy.database.Workout
import com.example.android.fitguuy.database.WorkoutDatabaseDao


class WorkoutRepository {
    private lateinit var workoutDatabaseDao: WorkoutDatabaseDao
    private lateinit var allworkout: LiveData<MutableList<Workout>>

}