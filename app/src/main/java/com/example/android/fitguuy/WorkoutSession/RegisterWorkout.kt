package com.example.android.fitguuy.WorkoutSession

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.fitguuy.RegisterWorkoutAdapter
import com.example.android.fitguuy.Repository.TempRepositoy
import com.example.android.fitguuy.WorkoutHistory.HistoryDetailFragment
import com.example.android.fitguuy.WorkoutHistory.HistoryFragment
import com.example.android.fitguuy.WorkoutHistory.RegisterWorkoutFactory
import com.example.android.fitguuy.database.Workout
import com.example.android.fitguuy.database.WorkoutDatabase
import fitguuy.R


class RegisterWorkout : Fragment(), RegisterWorkoutAdapter.ItemClickListener{


    private lateinit var registerWorkoutViewModel: RegisterWorkoutViewModel
    private lateinit var adapter: RegisterWorkoutAdapter

    private lateinit var database: WorkoutDatabase

    lateinit var btnCount1: Button
    lateinit var btnCount2: Button
    lateinit var btnSave: Button
    lateinit var repository: TempRepositoy
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_record, container, false)
        database = WorkoutDatabase.getInstance(this.requireContext(), viewLifecycleOwner.lifecycleScope)

        initRecyclerView(view)
        registerWorkoutViewModel = ViewModelProvider(this, RegisterWorkoutFactory(requireActivity().application)).get(
            RegisterWorkoutViewModel::class.java)
        repository = TempRepositoy(requireActivity().application)


//        btnCount1 = binding.figure1
//        btnCount2 =binding.figure2
//        btnSave = binding.btnFinishWorkout
//        btnSave.setOnClickListener {
//            adapter.insert()
//
//        }

        return view
    }
    private fun initRecyclerView(view: View) {
        val recyclerView: RecyclerView = view.findViewById(R.id.recordRecylerView)
        val layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.layoutManager = layoutManager

        adapter = RegisterWorkoutAdapter(view,this)
        recyclerView.adapter = adapter
    }

    override fun onItemClick(workout: Workout) {
        val fragment: Fragment = HistoryDetailFragment.newInstance(workout)
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        // transaction.replace(R.id.frame_container, fragment, "detail_fragment");
        transaction.hide(requireActivity().supportFragmentManager.findFragmentByTag("workout_history")!!)
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

    private fun insert () {
        Log.i("tets", btnCount1.text.toString().toLong().toString())
        repository.insert(Workout(btnCount1.text.toString().toLong(),btnCount2.text.toString().toLong(),"lagret trening","123","123"));
    }
    fun update (workout: Workout){
        repository.update(workout)
    }

}