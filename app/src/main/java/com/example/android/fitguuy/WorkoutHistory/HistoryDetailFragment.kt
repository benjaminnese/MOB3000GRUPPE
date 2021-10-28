package com.example.android.fitguuy.WorkoutHistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.android.fitguuy.database.Workout
import fitguuy.R
import kotlinx.android.synthetic.main.fragment_workout_history_row_detail.*


class HistoryDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var id: String? = null
    private var type1: String? = null
    private var reps1: String? = null
    private var weight1: String? = null
    private var type2: String? = null
    private var reps2: String? = null
    private var weight2: String? = null
    private var type3: String? = null
    private var reps3: String? = null
    private var weight3: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            id = requireArguments().getString(id)

            type1 = requireArguments().getString(TYPE1)
            reps1 = requireArguments().getString(REPS1)
            weight1 = requireArguments().getString(WEIGHT1)
            type2 = requireArguments().getString(TYPE2)
            reps2 = requireArguments().getString(REPS2)
            weight2 = requireArguments().getString(WEIGHT2)
            type3 = requireArguments().getString(TYPE3)
            reps3 = requireArguments().getString(REPS3)
            weight3 = requireArguments().getString(WEIGHT3)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_workout_history_row_detail, container, false)

        val txtVtype1 = view.findViewById<TextView>(R.id.type1)
        val txtVreps1 = view.findViewById<TextView>(R.id.reps1)
        val txtVweight1 = view.findViewById<TextView>(R.id.weight1)
        val txtVtype2 = view.findViewById<TextView>(R.id.type2)
        val txtVreps2 = view.findViewById<TextView>(R.id.reps2)
        val txtVweight2 = view.findViewById<TextView>(R.id.weight2)
        val txtVtype3 = view.findViewById<TextView>(R.id.type3)
        val txtVreps3 = view.findViewById<TextView>(R.id.reps3)
        val txtVweight3 = view.findViewById<TextView>(R.id.weight3)


        //titleTV.text = id
        txtVtype1.text = type1
        txtVreps1.text = reps1
        txtVweight1.text = weight1
        txtVtype2.text = type2
        txtVreps2.text = reps2
        txtVweight2.text = weight2
        txtVtype3.text = type3
        txtVreps3.text = reps3
        txtVweight3.text = weight3
        return view
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val IDJ = "param1"
        private const val TYPE1 = "param2"
        private const val REPS1 = "param3"
        private const val  WEIGHT1 = "param4"
        private const val  TYPE2 = "param5"
        private const val  REPS2 = "param6"
        private const val  WEIGHT2 = "param7"
        private const val  TYPE3 = "param8"
        private const val  REPS3 = "param9"
        private const val  WEIGHT3 = "param10"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment DetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(workout: Workout): HistoryDetailFragment {
            val fragment = HistoryDetailFragment()
            val args = Bundle()

            args.putString(IDJ, workout.id.toString())
            args.putString(TYPE1, workout.type1)
            args.putString(REPS1, workout.reps1)
            args.putString(WEIGHT1, workout.weight1)
            args.putString(TYPE2, workout.type2)
            args.putString(REPS2, workout.reps2)
            args.putString(WEIGHT2, workout.weight2)
            args.putString(TYPE3, workout.type3)
            args.putString(REPS3, workout.reps3)
            args.putString(WEIGHT3, workout.weight3)
            fragment.arguments = args
            return fragment
        }
    }
}