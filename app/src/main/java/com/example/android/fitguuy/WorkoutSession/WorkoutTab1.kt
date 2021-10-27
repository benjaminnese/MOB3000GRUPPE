package com.example.android.fitguuy.WorkoutSession

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import fitguuy.R


class WorkoutTab1 : Fragment() {

    private lateinit var editTextWorkoutId: EditText
    private lateinit var editTextWorkoutType: EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layoutinflater = inflater.inflate(R.layout.fragment_workout_tab1, container, false)

        editTextWorkoutId = layoutinflater.findViewById(R.id.editTextWorkoutId)
        editTextWorkoutType = layoutinflater.findViewById(R.id.editTextWorkoutType)

        //(activity as AppCompatActivity?)!!.supportActionBar.setSubtitle(fitguuy.R.string.ic)

        return layoutinflater
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
//        MenuInflater menuInflater
//    }

    private fun saveWorkout(){
        var id: String = editTextWorkoutId.text.toString()
        var type: String = editTextWorkoutId.text.toString()

        Toast.makeText(this.context, "WorkoutSaved", Toast.LENGTH_LONG)
    }

}