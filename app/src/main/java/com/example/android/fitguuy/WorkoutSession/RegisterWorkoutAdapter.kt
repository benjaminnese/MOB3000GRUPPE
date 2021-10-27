package com.example.android.fitguuy


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.fitguuy.database.Workout
import fitguuy.R

class RegisterWorkoutAdapter(view: View, private val clickListener: RegisterWorkoutAdapter.ItemClickListener) :
    RecyclerView.Adapter<RegisterWorkoutAdapter.RegisterWorkoutHolder>() {


    private var workoutSessionList: ArrayList<Workout> = ArrayList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RegisterWorkoutHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_workout_session_row, parent, false)
        return RegisterWorkoutHolder(view)
    }

    inner class RegisterWorkoutHolder(view: View): RecyclerView.ViewHolder(view) {

         var txtWorkoutExcersis: TextView
         var btnWorkoutSet1: TextView
         var btnWorkoutSet2: TextView
         var btnWorkoutSet3: TextView
         var btnWorkoutSet4: TextView
         var btnWorkoutSet5: TextView
         init{
             txtWorkoutExcersis = view.findViewById(R.id.txtWorkoutExcersis)
             btnWorkoutSet1 = view.findViewById(R.id.btnWorkoutSet1)
             btnWorkoutSet2 = view.findViewById(R.id.btnWorkoutSet2)
             btnWorkoutSet3 = view.findViewById(R.id.btnWorkoutSet3)
             btnWorkoutSet4 = view.findViewById(R.id.btnWorkoutSet4)
             btnWorkoutSet5 = view.findViewById(R.id.btnWorkoutSet5)
         }
    }
    override fun onBindViewHolder(holder: RegisterWorkoutHolder, position: Int) {
        var currentWorkoutSessionRow = workoutSessionList.get(position)
        holder.txtWorkoutExcersis.setText(currentWorkoutSessionRow.type1)
//        holder.btnWorkoutSet1.setText(currentWorkoutSessionRow.ty)
//        holder.btnWorkoutSet2.setText(currentWorkoutSessionRow.reps)
//        holder.btnWorkoutSet3.setText(currentWorkoutSessionRow.reps)
//        holder.btnWorkoutSet4.setText(currentWorkoutSessionRow.reps)
//        holder.btnWorkoutSet5.setText(currentWorkoutSessionRow.reps)

        holder.itemView.setOnClickListener(View.OnClickListener {
            clickListener.onItemClick(
                currentWorkoutSessionRow
            )
        })
    }
    fun setWorkoutSessionList(workoutSessionList: List<Workout>){
        this.workoutSessionList = workoutSessionList as ArrayList<Workout>
    }

      override fun getItemCount(): Int {
       return workoutSessionList.size
    }
    interface ItemClickListener {
        fun onItemClick(workout: Workout)
    }


}