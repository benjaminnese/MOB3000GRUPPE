package com.example.android.fitguuy.record

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.fitguuy.database.RepositoryImpl

//Boilerplate
class RecordViewModelFactory(
    private val RepositoryImpl: RepositoryImpl
) : ViewModelProvider.Factory {
    val application: Application = Application()

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecordViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RecordViewModel(RepositoryImpl, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}