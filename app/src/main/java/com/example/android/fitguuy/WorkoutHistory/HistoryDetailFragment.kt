package com.example.android.fitguuy.WorkoutHistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.android.fitguuy.database.Workout
import fitguuy.R


class HistoryDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var id: String? = null
    private var date:String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            id = requireArguments().getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_workout_history_row_detail, container, false)
        val titleTV = view.findViewById<TextView>(R.id.titleTV)
        titleTV.text = id
        return view
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_PARAM1 = "param1"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment DetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(id: Workout): HistoryDetailFragment {
            val fragment = HistoryDetailFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, id.id.toString())
            fragment.arguments = args
            return fragment
        }
    }
}