package com.example.android.fitguuy.WorkoutHistory

import android.os.Bundle

import androidx.fragment.app.Fragment


import android.view.ViewGroup

import android.view.LayoutInflater
import android.view.View


class HistoryFragmentContainer : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view : View = inflater.inflate(fitguuy.R.layout.activity_workout_history, container, false)
        val fragment: Fragment = HistoryFragment.newInstance()
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(fitguuy.R.id.frame_container, fragment, "history_workout")
        transaction.commit()
        return view
    }

}