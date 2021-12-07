package com.example.android.fitguuy.workouthistory

import android.os.Build.ID
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.android.fitguuy.R

import com.example.android.fitguuy.database.Exercise
import com.example.android.fitguuy.database.RepositoryImpl
import com.example.android.fitguuy.database.Workout
import com.example.android.fitguuy.database.WorkoutDatabase

//Fragmentet som skal vises når man trykker på en historie i listen
//får inn her liste med alle øvelsene fra en øvelse. De blir hentet inn fra HistoryFragment

class HistoryDetailFragment : Fragment() {

    lateinit var exerciseList: List<Exercise>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var workoutId: Int = 0
        if (arguments != null) {
            workoutId  = requireArguments().getInt(id1)
        }
        else
            workoutId = 1
        val repo: RepositoryImpl = RepositoryImpl.getInstance(WorkoutDatabase.getInstance(requireContext()).workoutDatabaseDao())

        exerciseList = repo.getExerciseFromWorkoutAsList(workoutId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        Log.i("HistoryDetailFragment", "inflater")
        return inflater.inflate(R.layout.fragment_history_workout_row_detail, container, false)
    }
    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        Log.i("HistoryDetailFragment", "Kjører onViewCreated")




        val listView: ListView = itemView.findViewById(R.id.listviewDetail) as ListView
        var mutableList:MutableList<String> = arrayListOf()


        //Legg alle variablene fra exercises inn i en String for så i en Listview
        for(i in exerciseList.indices){
            val exercise = exerciseList[i]
            mutableList.add( exercise.name + " " + exercise.exerciseID + " " + exercise.reps +
                    " " + exercise.sets +  " " + exercise.weight)
        }
       // Legger inn listeElementene inn i et adapter
        val adapter: ArrayAdapter<String> = ArrayAdapter(requireContext(), R.layout.fragment_history_workout_row_detail_item, mutableList)
        listView.adapter = adapter
    }

    //Companion kallet fra HistroyFragment
    companion object {
        private const val id1 = "param1"
        fun newInstance(
            workoutId: Int
        ): HistoryDetailFragment {
            Log.i("HistroyDetailFragment", "new instance")
            val fragment = HistoryDetailFragment()
            val args = Bundle()
            args.putInt(id1, workoutId)
            fragment.arguments = args
            return fragment
        }
    }
}