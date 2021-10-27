package com.example.android.fitguuy.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.android.fitguuy.database.Workout;
import com.example.android.fitguuy.database.WorkoutDatabase;
import com.example.android.fitguuy.database.WorkoutDatabaseDao;

import java.util.List;


public class TempRepositoy {
    private WorkoutDatabaseDao workoutDatabaseDao;
    private LiveData<List<Workout>> allWorkouts;


    public TempRepositoy(Application application){
        WorkoutDatabase workoutDatabase = WorkoutDatabase.Companion.getInstance(application);
        workoutDatabaseDao = workoutDatabase.getWorkoutDatabaseDao();
        allWorkouts = workoutDatabaseDao.getAll();
    }
    //API
    public void insert(Workout workout){
        new InsertWorkoutAsyncTask(workoutDatabaseDao).execute(workout);
    }
    public void update(Workout workout){
        new UpdateWorkoutAsyncTask(workoutDatabaseDao).execute(workout);
    }
    public void delete(Workout workout){
        new DeleteWorkoutAsyncTask(workoutDatabaseDao).execute(workout);
    }
    public void deleteAllWorkouts( ){
        new DeleteAllWorkoutAsyncTask(workoutDatabaseDao).execute();
    }
    public LiveData<List<Workout>> getAllWorkout(){
        return allWorkouts;
    }
    private static class InsertWorkoutAsyncTask extends AsyncTask<Workout, Void, Void>{
        private final WorkoutDatabaseDao workoutDatabaseDao;

        private InsertWorkoutAsyncTask(WorkoutDatabaseDao workoutDatabaseDao){
            this.workoutDatabaseDao = workoutDatabaseDao;
        }
        @Override
        protected Void doInBackground(Workout... workouts) {
            workoutDatabaseDao.insert(workouts[0]);
            return null;
        }
    }
    private static class UpdateWorkoutAsyncTask extends AsyncTask<Workout, Void, Void>{
        private WorkoutDatabaseDao workoutDatabaseDao;

        private UpdateWorkoutAsyncTask(WorkoutDatabaseDao workoutDatabaseDao){
            this.workoutDatabaseDao = workoutDatabaseDao;
        }
        @Override
        protected Void doInBackground(Workout... workouts) {
            workoutDatabaseDao.update(workouts[0]);
            return null;
        }
    }
    private static class DeleteWorkoutAsyncTask extends AsyncTask<Workout, Void, Void>{
        private WorkoutDatabaseDao workoutDatabaseDao;

        private DeleteWorkoutAsyncTask(WorkoutDatabaseDao workoutDatabaseDao){
            this.workoutDatabaseDao = workoutDatabaseDao;
        }
        @Override
        protected Void doInBackground(Workout... workouts) {
            workoutDatabaseDao.delete(workouts[0]);
            return null;
        }
    }
    private static class DeleteAllWorkoutAsyncTask extends AsyncTask<Void, Void, Void>{
        private WorkoutDatabaseDao workoutDatabaseDao;

        private DeleteAllWorkoutAsyncTask(WorkoutDatabaseDao workoutDatabaseDao){
            this.workoutDatabaseDao = workoutDatabaseDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            workoutDatabaseDao.deleteAll();
            return null;
        }
    }


}
