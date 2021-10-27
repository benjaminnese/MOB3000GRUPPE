package com.example.android.fitguuy.WorkoutHistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.fitguuy.database.Workout
import com.example.android.fitguuy.database.WorkoutDatabase
import fitguuy.R


class HistoryFragment : Fragment(), HistoryListAdapter.ItemClickListener {

    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var adapter: HistoryListAdapter
    lateinit var database: WorkoutDatabase



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_history_workout, container, false)
        database = WorkoutDatabase.getInstance(this.requireContext(),viewLifecycleOwner.lifecycleScope)
        buildListData()
        initRecyclerView(view)
        historyViewModel =
            ViewModelProvider(this, HistoryViewModelFactory(requireActivity().application)).get(
                HistoryViewModel::class.java
            )

        historyViewModel.getAllWorkouts().observe(viewLifecycleOwner, {
            if (it != null) {
                //Update recylerview, skjer bare om fragment er "synlig"
                  adapter.setWorkout(it)
                   //adapter.submitList(it)
                Toast.makeText(this.context, "Changed", Toast.LENGTH_LONG).show()
            }
        })

        return view
    }

    private fun initRecyclerView(view: View) {
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager

        adapter = HistoryListAdapter(this)
        recyclerView.adapter = adapter
    }

    private fun buildListData() {

    }


    override fun onItemClick(workout: Workout) {
        val fragment: Fragment = HistoryDetailFragment.newInstance(workout)
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        // transaction.replace(R.id.frame_container, fragment, "detail_fragment");
        transaction.hide(requireActivity().supportFragmentManager.findFragmentByTag("history_workout")!!)
        transaction.add(R.id.frame_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
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