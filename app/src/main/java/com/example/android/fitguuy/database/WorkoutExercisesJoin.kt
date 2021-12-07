package com.example.android.fitguuy.database


//TODO Prøvde å koble sammen databasen via relation, ble til vi inner joinet via dao
//
//
//import androidx.room.Embedded
//import androidx.room.Relation
//
//data class WorkoutExercisesJoin(
//    @Embedded val workout: Workout,
//    @Relation(
//        parentColumn = "id",
//        entityColumn = "exerciseID"
//    )
//    val exercies: List<Exercise> = emptyList() //Oppretter en tom liste
//
//
//)