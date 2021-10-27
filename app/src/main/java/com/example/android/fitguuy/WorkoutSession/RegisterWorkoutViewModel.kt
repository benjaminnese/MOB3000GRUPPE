package com.example.android.fitguuy.WorkoutSession

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.AndroidViewModel
import com.example.android.fitguuy.Repository.TempRepositoy
import fitguuy.R

class RegisterWorkoutViewModel ( application: Application) :
    AndroidViewModel(application){
    private var repository: TempRepositoy = TempRepositoy(application)

    fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workout_landing_page, container, false)
    }



    }





