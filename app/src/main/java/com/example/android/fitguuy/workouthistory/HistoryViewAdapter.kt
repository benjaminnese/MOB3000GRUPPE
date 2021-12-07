package com.example.android.fitguuy.workouthistory

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.fitguuy.R
import com.example.android.fitguuy.database.RepositoryImpl
import com.example.android.fitguuy.database.Workout



class HistoryViewAdapter(
    private val clickListener: ItemClickListener,
   repositoryImpl: RepositoryImpl
) :  ListAdapter<Workout, HistoryViewAdapter.HistoryViewHolder>(EqualElement())  {

    private val workouts = repositoryImpl.getAllAsList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val current = getItem(position)
        Log.i("HistoryViewAdapter", current.toString())
        holder.bind(current)
        //Setter lytter på alle workoutene i listen på historie
        for(workout in workouts){
            holder.itemView.setOnClickListener {
                clickListener.onItemClick(
                    workout
                )
                Log.i("HistoryViewAdapter", "Trykket på current")
            }
        }

    }
    class HistoryViewHolder(itemView: View/*, private val recordViewModel: RecordViewModel*/) : RecyclerView.ViewHolder(itemView) {
        var idTextView: TextView = itemView.findViewById(R.id.textHistroyRowName)
        var dateTextView: TextView = itemView.findViewById(R.id.textHistoryRowDate)


        fun bind(workout: Workout){
            Log.i("HistoryViewAdapter", "Inne i bind")
            dateTextView.text =("Date: " + workout.date.toString())
            idTextView.text = ("ID: " + workout.workoutId.toString())
        }

        companion object {
            fun create(parent: ViewGroup): HistoryViewHolder {
                Log.i("HistoryViewAdapter", "Inni create objekt")
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.fragment_history_workout_row, parent, false)
                Log.i("HistoryViewAdapter", view.toString())
                return HistoryViewHolder(view)
            }
        }
    }

    class EqualElement : DiffUtil.ItemCallback<Workout>() {
        override fun areItemsTheSame(oldItem: Workout, newItem: Workout): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Workout, newItem: Workout): Boolean {
            return oldItem.workoutId == newItem.workoutId
        }
    }
    //Metoden retuneres til HistroyFragment for bruk der
    interface ItemClickListener {
        fun onItemClick(workout: Workout)
    }
}
