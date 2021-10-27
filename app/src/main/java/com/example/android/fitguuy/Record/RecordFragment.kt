package com.example.android.fitguuy.Record


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.fitguuy.database.Exercise
import com.example.android.fitguuy.database.Workout
import com.example.android.fitguuy.database.WorkoutDatabase
import com.example.android.fitguuy.database.WorkoutDatabaseDao
import fitguuy.R
import fitguuy.databinding.FragmentRecordBinding
import kotlinx.coroutines.launch


class RecordFragment : Fragment() {

    private lateinit var recordViewModel: RecordViewModel
    private lateinit var recordViewModelFactory: RecordViewModelFactory
    private lateinit var linLayoutMgr: RecyclerView.LayoutManager

    private lateinit var recordRecyclerView: RecyclerView
    private lateinit var _binding: FragmentRecordBinding

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding
    lateinit var textView: TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecordBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val scope = viewLifecycleOwner.lifecycleScope
        val mContext = activity

        val application = requireNotNull(this.activity).application
        val dataSource = WorkoutDatabase.getInstance( application,viewLifecycleOwner.lifecycleScope).workoutDatabaseDao

        recordViewModelFactory = RecordViewModelFactory(dataSource, application)
        recordViewModel =
            ViewModelProvider(this, recordViewModelFactory).get(RecordViewModel::class.java)
        //  linLayoutMgr.setLayoutManager(new LinearLayoutManager(this));
        linLayoutMgr = LinearLayoutManager(context)
        val recordAdapter = RecordListAdapter()
        binding.recordRecylerView.adapter = recordAdapter
        recordViewModel.exerciseplan.observe(viewLifecycleOwner, Observer {
            it?.let {
                recordAdapter.submitList(it)
            }
        })
        recordViewModel.lastWorkout.observe(viewLifecycleOwner, Observer{
            if (it != null) {
                recordAdapter.lastWorkout = it
            }
        })
        recordRecyclerView = binding.recordRecylerView.apply {
            setHasFixedSize(false)
            layoutManager = linLayoutMgr
            adapter = recordAdapter
        }
        binding.btnFinishWorkout.setOnClickListener{
            //val exerciseCount = recordAdapter.listItemsHolder.size
            var i = 0
            while (i<1) {
                Log.i("RecordFragment",
                    "Løkke: ${
                        recordAdapter.listItemsHolder[i].exerciseName.text.toString()
                    }")
                i++
            }
            Log.i("RecordFragment1", "Button clicklistner run")
            //Log.i("RecordFragment", "retreving children ${linLayoutMgr.getChildAt(0)?.findViewById<RelativeLayout>(R.id.relLayoutRecord)?.findViewById<TextView>(R.id.txtExerciseName)?.text}")
            //Log.i("RecordFragment", "retreving childrenCount ${linLayoutMgr.childCount}")
            //val childCount = linLayoutMgr.childCount
            /*
            var i = 0
            var childViewList: MutableList<RelativeLayout> = mutableListOf()
            while (i<6) {
                //Log.i("RecordFragment", linLayoutMgr.getChildAt(i)?.findViewById<RelativeLayout>(R.id.relLayoutRecord).toString())
                childViewList.add(linLayoutMgr.getChildAt(i)!!.findViewById<RelativeLayout>(R.id.relLayoutRecord))
                Log.i("RecordFragment2",
                    "Løkke: ${
                        linLayoutMgr.getChildAt(i)
                            ?.findViewById<RelativeLayout>(R.id.relLayoutRecord)
                            ?.findViewById<TextView>(R.id.txtExerciseName)?.text.toString()
                    }")
                i++
            }
            Log.i("RecordFragment3", childViewList.toString())

             */
            //TODO(Håkon) Legg inn i ViewModel
            dataSource.let { database ->
                scope.launch {
                    //dataSource.insert(getDataFromView(childViewList))
                    dataSource.insert(getDataFromItemViewHolder(recordAdapter.listItemsHolder))
                }
            }



        }

        /*
        recordViewModel._workout.observe(viewLifecycleOwner, Observer {
            it?.let {
                recordAdapter.submitList(List<WorkoutInDb>(it))
            }
        })

         */
        dataSource.let { database ->
            scope.launch { //deleteAll(database)
             populateDatabase(database)
               // deleteAll(database)
            }
        }
        return root


    }
    fun getDataFromItemViewHolder(list:MutableList<RecordListAdapter.RecordViewHolder>) : Workout {
        Log.i("RecordFragment", "getDataFromItemViewHolder kjørt. lista:  ${list.toString()}")
        val newWorkout = Workout()
        newWorkout.type1 = list[0].exerciseName.text.toString()
        newWorkout.reps1 = list[0].button1.text.toString()
        newWorkout.weight1 = list[0].currentWeight.text.toString()
        newWorkout.type2 = list[1].exerciseName.text.toString()
        newWorkout.reps2 = list[1].button1.text.toString()
        newWorkout.weight2 = list[1].currentWeight.text.toString()
        newWorkout.type3 = list[2].exerciseName.text.toString()
        newWorkout.reps3 = list[2].button1.text.toString()
        newWorkout.weight3 = list[2].currentWeight.text.toString()
        newWorkout.type4 = list[3].exerciseName.text.toString()
        newWorkout.reps4 = list[3].button1.text.toString()
        newWorkout.weight4 = list[3].currentWeight.text.toString()
        newWorkout.type5 = list[4].exerciseName.text.toString()
        newWorkout.reps5 = list[4].button1.text.toString()
        newWorkout.weight5 = list[4].currentWeight.text.toString()
        newWorkout.type6 = list[5].exerciseName.text.toString()
        newWorkout.reps6 = list[5].button1.text.toString()
        newWorkout.weight6 = list[5].currentWeight.text.toString()
        return newWorkout
    }
    fun getDataFromView(list:MutableList<RelativeLayout>) : Workout {
        //TODO(Alle): Lag en metode som henter reps fra alle knappene og putter det i en string.
        Log.i("RecordFragment4", "getDataFromView kjørt. lista:  ${list[0].findViewById<TextView>(R.id.txtExerciseName).toString()}")
        val newWorkout = Workout()
        newWorkout.type1 = list[0].findViewById<TextView>(R.id.txtExerciseName).text.toString()
        newWorkout.reps1 = list[0].findViewById<TextView>(R.id.figure_1).text.toString()
        newWorkout.weight1 = list[0].findViewById<TextView>(R.id.txtCurrentWeight).text.toString()
        newWorkout.type2 = list[1].findViewById<TextView>(R.id.txtExerciseName).text.toString()
        newWorkout.reps2 = list[1].findViewById<TextView>(R.id.figure_1).text.toString()
        newWorkout.weight2 = list[1].findViewById<TextView>(R.id.txtCurrentWeight).text.toString()
        newWorkout.type3 = list[2].findViewById<TextView>(R.id.txtExerciseName).text.toString()
        newWorkout.reps3 = list[2].findViewById<TextView>(R.id.figure_1).text.toString()
        newWorkout.weight3 = list[2].findViewById<TextView>(R.id.txtCurrentWeight).text.toString()
        newWorkout.type4 = list[3].findViewById<TextView>(R.id.txtExerciseName).text.toString()
        newWorkout.reps4 = list[3].findViewById<TextView>(R.id.figure_1).text.toString()
        newWorkout.weight4 = list[3].findViewById<TextView>(R.id.txtCurrentWeight).text.toString()
        newWorkout.type5 = list[4].findViewById<TextView>(R.id.txtExerciseName).text.toString()
        newWorkout.reps5 = list[4].findViewById<TextView>(R.id.figure_1).text.toString()
        newWorkout.weight5 = list[4].findViewById<TextView>(R.id.txtCurrentWeight).text.toString()
        newWorkout.type6 = list[5].findViewById<TextView>(R.id.txtExerciseName).text.toString()
        newWorkout.reps6 = list[5].findViewById<TextView>(R.id.figure_1).text.toString()
        newWorkout.weight6 = list[5].findViewById<TextView>(R.id.txtCurrentWeight).text.toString()
        return newWorkout
    }
    companion object {
        @JvmStatic
        suspend fun populateDatabase(workoutDatabaseDao: WorkoutDatabaseDao) {

            // Delete all content here

            //Legg til eksempler på ord
            Log.i("RecordFragment5", "populateDatabase kjørt")
            var exercise = Exercise()
            val exercises = listOf("Benkpress","Knebøy","Markløft","Roing","Nedtrekk","Biceps curl","test","test1")
            exercises.forEach {
                exercise.partOfName = "default"
                exercise.name = it
                exercise.reps = 5
                exercise.sets = 5
                workoutDatabaseDao.insert(exercise)
            }

            Log.i("RecordFragmen6t", "populateDatabase.insert kjørt")
            /*
            var workout = WorkoutInDb()
            workout.type1 = "Benkpress"
            workout.reps1 = "5"
            workout.weight1 = "100"
            workout.type2 = "Knebøy"
            workout.reps2 = "5"
            workout.weight2 = "150"
            workout.type3 = "Markløft"
            workout.reps3 = "5"
            workout.weight3 = "200"
            workoutDatabaseDao.insert(workout)
            workout = WorkoutInDb()
            workout.type1 = "Benkpress"
            workout.reps1 = "5"
            workout.weight1 = "100"
            workout.type2 = "Knebøy"
            workout.reps2 = "5"
            workout.weight2 = "150"
            workout.type3 = "Markløft"
            workout.reps3 = "5"
            workout.weight3 = "200"
            workoutDatabaseDao.insert(workout)
            workout = WorkoutInDb()
            workout.type1 = "Benkpress"
            workout.reps1 = "5"
            workout.weight1 = "100"
            workout.type2 = "Knebøy"
            workout.reps2 = "5"
            workout.weight2 = "150"
            workout.type3 = "Markløft"
            workout.reps3 = "5"
            workout.weight3 = "200"
            workoutDatabaseDao.insert(workout)
            workout = WorkoutInDb()
            workout.type1 = "Benkpress"
            workout.reps1 = "5"
            workout.weight1 = "100"
            workout.type2 = "Knebøy"
            workout.reps2 = "5"
            workout.weight2 = "150"
            workout.type3 = "Markløft"
            workout.reps3 = "5"
            workout.weight3 = "200"
            workoutDatabaseDao.insert(workout)
            workout = WorkoutInDb()
            workout.type1 = "Benkpress"
            workout.reps1 = "5"
            workout.weight1 = "100"
            workout.type2 = "Knebøy"
            workout.reps2 = "5"
            workout.weight2 = "150"
            workout.type3 = "Markløft"
            workout.reps3 = "5"
            workout.weight3 = "200"
            workoutDatabaseDao.insert(workout)
            workout = WorkoutInDb()
            workout.type1 = "Benkpress"
            workout.reps1 = "5"
            workout.weight1 = "100"
            workout.type2 = "Knebøy"
            workout.reps2 = "5"
            workout.weight2 = "150"
            workout.type3 = "Markløft"
            workout.reps3 = "5"
            workout.weight3 = "200"
            workoutDatabaseDao.insert(workout)
            workout = WorkoutInDb()
            workout.type1 = "Benkpress"
            workout.reps1 = "5"
            workout.weight1 = "100"
            workout.type2 = "Knebøy"
            workout.reps2 = "5"
            workout.weight2 = "150"
            workout.type3 = "Markløft"
            workout.reps3 = "5"
            workout.weight3 = "200"
            workoutDatabaseDao.insert(workout)
            workout = WorkoutInDb()
            workout.type1 = "Benkpress"
            workout.reps1 = "5"
            workout.weight1 = "100"
            workout.type2 = "Knebøy"
            workout.reps2 = "5"
            workout.weight2 = "150"
            workout.type3 = "Markløft"
            workout.reps3 = "5"
            workout.weight3 = "200"
            workoutDatabaseDao.insert(workout)
            workout = WorkoutInDb()
            workout.type1 = "Benkpress"
            workout.reps1 = "5"
            workout.weight1 = "100"
            workout.type2 = "Knebøy"
            workout.reps2 = "5"
            workout.weight2 = "150"
            workout.type3 = "Markløft"
            workout.reps3 = "5"
            workout.weight3 = "200"
            workoutDatabaseDao.insert(workout)

             */


        }

        suspend fun deleteAll(workoutDatabaseDao: WorkoutDatabaseDao) {
            workoutDatabaseDao.deleteAllWor()
            workoutDatabaseDao.deleteAllEx()
            workoutDatabaseDao.clearETab()
            workoutDatabaseDao.clearWTab()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        //_binding = null
    }

}

private fun <E> List<E>.add(childAt: E) {

}


