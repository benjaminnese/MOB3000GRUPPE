package com.example.android.fitguuy.utils

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.android.fitguuy.database.Exercise
import com.example.android.fitguuy.database.Workout
import com.example.android.fitguuy.database.WorkoutDatabase

//Forbedret versjon etter å sett hvordan det ble løst i
//https://github.dev/android/sunflower/blob/compose/app/src/main/java/com/google/samples/apps/sunflower

//Bruker her WorkManager til å gjøre arbeid vi ikke får lov til å gjøre på main "thread"
open class FillDatabaseWithContentWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) { //Kotlin corutine
    private val dbDao = WorkoutDatabase.getInstance(applicationContext).workoutDatabaseDao()
    override suspend fun doWork(): Result {
        try {
            Log.i("FillDatabaseWithContentWorker", "Henter database")
            dbDao.insertWorkout(Workout(1, date = 23))
            Log.i("FillDatabaseWithContentWorker", "Før innfylling løkken")
            dbDao.insertExercise(
                Exercise(
                    exerciseID = 0,
                    //Alle treninger får referanse til økt 1
                    name = "Bench",
                    reps = 5,
                    sets = 5,
                    weight = 50,
                    refKey = 1
                )
            ) //Retunere resultat basert på om det gikk bra eller ikke
            Log.i(TAG, "FillDatabaseWithContentWorker SUCESS")
            return Result.success()
        } catch (ex: Exception) {
            Log.e(TAG, "FillDatabaseWithContentWorker FAILED")
            return Result.failure()
        }

        // Indicate whether the work finished successfully with the Result

    }
    //Tag shortcut
    companion object {
        private const val TAG = "Database, FillDatabase"
    }
}