package com.example.android.fitguuy.record


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.fitguuy.R
import com.example.android.fitguuy.database.Exercise
import com.example.android.fitguuy.database.RepositoryImpl
import com.example.android.fitguuy.database.WorkoutDatabase

//https://developer.android.com/codelabs/basic-android-kotlin-training-viewmodel
//RecordFragment tar vi bruk recylerview for å vise frem liste over øvelsene
//Ettersom det ikke er store mengde med øvelser kunne vi fint brukt en vanlig listview
//Mye boilerplate kode for å få opp recylerview

class RecordFragment : Fragment() {

    private lateinit var recordViewModel: RecordViewModel
    lateinit var repo: RepositoryImpl
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_record, container, false)

    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        repo = RepositoryImpl.getInstance(
            WorkoutDatabase.getInstance(requireContext().applicationContext).workoutDatabaseDao()
        )
        recordViewModel = RecordViewModel(repo, requireActivity().application)
        val recyclerView: RecyclerView = itemView.findViewById(R.id.recordRecylerView)
        val adapter = RecordViewAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this.requireContext())

        Log.i("RecordFragment", "RecordViewModelCreated")

        recordViewModel =
            ViewModelProvider(this, RecordViewModelFactory(repo))[RecordViewModel::class.java]

        recordViewModel.allExercise.observe(requireActivity()) { list: List<Exercise> ->
            list.let { adapter.submitList(it) }
        }

        val button = itemView.findViewById<Button>(R.id.btnEditWorkout)
        button.setOnClickListener {
            Log.i("RecordFragment", "Added Exercise")
            repo.insertExercise(
                Exercise(
                    0,
                    (itemView.findViewById<EditText>(R.id.editTextExerciseName).text.toString()),
                    5,
                    5,
                    50,
                    repo.getLastWorkout().workoutId
                )
            )
            //findNavController().navigate(R.id.action_navigation_fragment_record_to_recordEditFragment2)
        }
        val btnLagreRecord = itemView.findViewById<Button>(R.id.btnFinishWorkout)
        //TODO fant ingen løsning tidsnok til å aksesere dataen fra viewt som vi kunne ha
        //fått oppdatert entiteten
        //Planen her var å få tak i exerciseID også kalle på updateExercise(exerciseID: Int, nyData: Int)
        btnLagreRecord.setOnClickListener {
            Log.i("RecordFragmentViewModel", recordViewModel.allExercise.value.toString())
            Log.i("RecordFragment", "Save workout")
            Log.i("RecordFragment", repo.getLastWorkout().toString())
            repo.updateWorkout(false, repo.getLastWorkout().workoutId)

            //Log.i("RecordFragment", repo.getLastWorkout().active.toString())
            repo.defaultExercisePlan()
            //findNavController().navigate(R.id.action_navigation_fragment_record_to_recordEditFragment2)
        }

    }


}













