package com.example.android.fitguuy.database

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*


@Database(entities = [Workout::class,  Exercise::class], version = 2, exportSchema = false)
@TypeConverters(
    WorkoutDatabase.Converters::class
)
abstract class WorkoutDatabase : RoomDatabase() {

    abstract val workoutDatabaseDao: WorkoutDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: WorkoutDatabase? = null

        fun getInstance(context: Context, scope: CoroutineScope): WorkoutDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        WorkoutDatabase::class.java,
                        "fiitguuy_database"
                    )
                        .allowMainThreadQueries()
                        .addCallback(WorkoutDatabaseCallback(scope))
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }

        }
        fun getInstance(context: Context): WorkoutDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        WorkoutDatabase::class.java,
                        "fiitguuy_database"
                    )
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }

        }


    }

    object Converters {
        @TypeConverter
        fun fromTimestamp(value: Long?): Date? {
            return value?.let { Date(it) }
        }

        @TypeConverter
        fun dateToTimestamp(date: Date?): Long? {
            return if (date == null) null else date.getTime()
        }
    }





    private class WorkoutDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch {
                        populateDatabase(database.workoutDatabaseDao)
                    }
                }
            }

            suspend fun populateDatabase(workoutDatabaseDao: WorkoutDatabaseDao) {
                // Delete all content here
                //workoutDatabaseDao.deleteAll()
                workoutDatabaseDao.deleteAll()
                //Legg til eksempler på ord
                var word = Workout( 0, 12, "", "", "")
                workoutDatabaseDao.insert( word)
                word =  Workout( 1,  1234, "", "")
                workoutDatabaseDao.insert( word)
                word = Workout()
                word.type1 = "Benkpress"
                workoutDatabaseDao.insert(word)
                word = Workout()
                word.type2 = "Knebøy"
                workoutDatabaseDao.insert(word)
                word = Workout()
                word.type3 = "Markløft"
                workoutDatabaseDao.insert(word)
            }

        }

}