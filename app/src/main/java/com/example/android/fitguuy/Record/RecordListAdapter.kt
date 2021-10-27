package com.example.android.fitguuy.Record

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.fitguuy.database.Exercise
import com.example.android.fitguuy.database.Workout
import fitguuy.databinding.RecordListItemBinding


class RecordListAdapter: ListAdapter<Exercise, RecordListAdapter.RecordViewHolder>(WorkoutInDbDiffCallback()) {

    var lastWorkout: Workout = Workout()
    var listItemsHolder: MutableList<RecordViewHolder> = arrayListOf()
    var  workoutList: ArrayList<Workout> = ArrayList()
    class WorkoutInDbDiffCallback : DiffUtil.ItemCallback<Exercise>() {
        override fun areItemsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecordViewHolder {
        return RecordViewHolder.from(parent)
    }

    public fun setWorkout(workout: Workout){
        this.workoutList.add(workout)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        val exercise = getItem(position)
        listItemsHolder.add(holder)

        //holder.
        //holder.lastWeight.text = lastWorkout.weight1
        holder.exerciseName.text = exercise.name
        Log.i("RecordListAdapter", "OnBindView: ${ holder.exerciseName.text.toString() }")
        listItemsHolder.add(holder)
    }
    
    class RecordViewHolder(binding: RecordListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val lastWeight: TextView
        val exerciseName: TextView
        val currentWeight: TextView
        val button1: Button

        init {
            exerciseName = binding.txtExerciseName
            lastWeight = binding.txtLastWeight
            currentWeight = binding.txtCurrentWeight
            button1 = binding.figure1
        }

        companion object {
            fun from(parent: ViewGroup): RecordViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecordListItemBinding.inflate(layoutInflater, parent, false)

                return RecordViewHolder(binding)
            }
        }
    }


}