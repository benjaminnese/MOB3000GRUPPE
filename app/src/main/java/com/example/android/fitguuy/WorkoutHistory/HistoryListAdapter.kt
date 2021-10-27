package com.example.android.fitguuy.WorkoutHistory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.fitguuy.database.Workout
import fitguuy.R



class HistoryListAdapter(private val clickListener: ItemClickListener)
    : RecyclerView.Adapter<HistoryListAdapter.HistoryViewHolder>() {


    private var workoutList: ArrayList<Workout> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_workout_history_row, parent, false)

        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        var currentWorkout = workoutList.get(position)
        holder.idTextView.setText(currentWorkout.id.toString())
        holder.dateTextView.setText(currentWorkout.id.toString() )
        holder.exceriesTextView.setText(currentWorkout.type1)

        holder.itemView.setOnClickListener(View.OnClickListener {
            clickListener.onItemClick(
                currentWorkout
            )
        })
    }
    //Get list into recylever view
    public fun setWorkout(workoutList:List<Workout>){
        this.workoutList = workoutList as ArrayList<Workout>
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int {
        return workoutList.size
    }

    inner class HistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var idTextView: TextView
        var dateTextView: TextView
        var exceriesTextView: TextView

        init {
            idTextView = view.findViewById(R.id.textId)
            dateTextView = view.findViewById(R.id.textDate)
            exceriesTextView = view.findViewById(R.id.textExceriesType)

        }
    }

    interface ItemClickListener {
        fun onItemClick(workout: Workout)
    }
}
