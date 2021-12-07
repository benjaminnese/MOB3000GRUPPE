package com.example.android.fitguuy.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.android.fitguuy.utils.FillDatabaseWithContentWorker
import com.example.android.fitguuy.utils.TimeConverters

//https://github.dev/android/sunflower/blob/compose/app/src/main/java/com/google/samples/apps/sunflower

//TODO ved endring av database entiter må du endre versionen 50->51 osv..
@Database(entities = [Workout::class, Exercise::class], version = 51, exportSchema = true)
@TypeConverters(TimeConverters::class)
abstract class WorkoutDatabase : RoomDatabase() {
    abstract fun workoutDatabaseDao(): WorkoutDatabaseDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: WorkoutDatabase? = null

        fun getInstance(context: Context): WorkoutDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }

            }
        }

        //Bygger databasen med funksjons kall til FillDatabaseWithContentWorker
        //med hjelp av synkroniserte arbeidere fra androidX. Bruker WorkManager
        //Siden vi bare trenger å fylle databasen under commit

        private fun buildDatabase(context: Context): WorkoutDatabase {
            Log.i("Database", "Database blir opprettet")
            return Room.databaseBuilder(context, WorkoutDatabase::class.java, "workoutDB")
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        val request =
                            OneTimeWorkRequestBuilder<FillDatabaseWithContentWorker>().build()
                        Log.i("Database: ", request.toString())
                        WorkManager.getInstance(context).enqueue(request)
                    }
                }).allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }

        private fun fillExsistingDatabase(context: Context): WorkoutDatabase {
            return Room.databaseBuilder(context, WorkoutDatabase::class.java, "workoutDB")
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onOpen(db: SupportSQLiteDatabase) {
                        super.onOpen(db)
                        val request =
                            OneTimeWorkRequestBuilder<FillDatabaseWithContentWorker>().build()
                        WorkManager.getInstance(context).enqueue(request)
                    }
                })
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}



