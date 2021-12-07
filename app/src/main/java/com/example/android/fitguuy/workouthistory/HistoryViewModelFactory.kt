package com.example.android.fitguuy.workouthistory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.fitguuy.database.RepositoryImpl
//Boilerplate
class HistoryViewModelFactory(
    private val databaseHelperImpl: RepositoryImpl,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HistoryViewModel(databaseHelperImpl, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}