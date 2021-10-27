package com.example.android.fitguuy.WorkoutHistory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.fitguuy.WorkoutSession.RegisterWorkoutViewModel

class RegisterWorkoutFactory (
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RegisterWorkoutViewModel::class.java)) {
            return RegisterWorkoutViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}