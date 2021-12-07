package com.example.android.fitguuy.record

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.children
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.fitguuy.R
import com.example.android.fitguuy.database.Exercise

class RecordViewAdapter :
    ListAdapter<Exercise, RecordViewAdapter.RecordViewHolder>(EqualElement()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        return RecordViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    class RecordViewHolder(itemView: View/*, private val recordViewModel: RecordViewModel*/) : RecyclerView.ViewHolder(itemView) {

        private val exerciseName: TextView = itemView.findViewById(R.id.txtExerciseName)
        private val btnWorkoutSet1: TextView = itemView.findViewById(R.id.btnWorkoutSet1)
        private val btnWorkoutSet2: TextView = itemView.findViewById(R.id.btnWorkoutSet2)
        private val btnWorkoutSet3: TextView = itemView.findViewById(R.id.btnWorkoutSet3)
        private val btnWorkoutSet4: TextView = itemView.findViewById(R.id.btnWorkoutSet4)
        private val btnWorkoutSet5: TextView = itemView.findViewById(R.id.btnWorkoutSet5)
        private var editTextWeight: EditText = itemView.findViewById(R.id.editTextCurrentWeight)
        private var reps: Int = 0

        //Setter verdiene fra øvelsen inn i viewene
        fun bind(exercise: Exercise) {
            exerciseName.text = exercise.name
            btnWorkoutSet1.text = exercise.reps.toString()
            btnWorkoutSet2.text = exercise.reps.toString()
            btnWorkoutSet3.text = exercise.reps.toString()
            btnWorkoutSet4.text = exercise.reps.toString()
            btnWorkoutSet5.text = exercise.reps.toString()
            editTextWeight.hint = exercise.weight.toString()
            reps = btnWorkoutSet1.text.toString().toInt()
        }

        init {
            itemView.findViewById<RelativeLayout>(R.id.relativeLayoutRecordRow)
                .setListnerAllButtons()
        }
        //TODO Stygg, men gjør jobben. Får lagt inn lytter for alle knappene her
        private fun RelativeLayout.setListnerAllButtons() {
            val listChildren = this.children
            listChildren.forEach { view ->
                if (view is Button)
                    view.setOnClickListener {
                        var num = view.text.toString().toInt()
                        if (num >= reps)
                            num = 0
                        else num++
                        view.text = num.toString()

                    }
            }
        }

        //TODO FINN UT HVORDAN FÅ RECORDVIEWMODEL HIT
        companion object {
            fun create(parent: ViewGroup): RecordViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.fragment_record_row, parent, false)
                return RecordViewHolder(view/*, recordViewModel*/)
            }
        }
    }
    //Sjekk for om to element er like
    class EqualElement : DiffUtil.ItemCallback<Exercise>() {
        override fun areItemsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
            return oldItem.exerciseID == newItem.exerciseID
        }
    }


}