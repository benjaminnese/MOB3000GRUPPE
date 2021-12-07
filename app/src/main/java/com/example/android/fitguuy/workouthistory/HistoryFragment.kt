package com.example.android.fitguuy.workouthistory

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.fitguuy.R
import com.example.android.fitguuy.database.*


class HistoryFragment: Fragment(),
    HistoryViewAdapter.ItemClickListener {

    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var adapter: HistoryViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("HistoryFragment", "Inflater ")
        return inflater.inflate(R.layout.fragment_history_workout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("HistoryFragment", "I onViewCreated")
        val databaseHelperImpl = RepositoryImpl.getInstance(WorkoutDatabase.getInstance(requireActivity().application).workoutDatabaseDao())
        val recyclerView: RecyclerView = view.findViewById(R.id.workoutHistoryRecylerView)
        adapter = HistoryViewAdapter(this ,databaseHelperImpl)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this.requireContext())

        historyViewModel =
            ViewModelProvider(this, HistoryViewModelFactory(databaseHelperImpl,requireActivity().application))[HistoryViewModel::class.java]
        historyViewModel.allWorkouts.observe(requireActivity()) { list: List<Workout> ->
            list.let { adapter.submitList(it) }
        }
    }


    //Her kalles fragmentManager for å bytte fragment til en detaljert liste over en spesefikk workout

    override fun onItemClick(workout: Workout) {
        Log.i("HistoryFragment", "Trykket på liste")
        HistoryDetailFragment.newInstance(workout.workoutId)
        findNavController().navigate(R.id.action_historyFragment_to_historyDetailFragment)
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(): HistoryFragment {
            return HistoryFragment()
        }


    }

}