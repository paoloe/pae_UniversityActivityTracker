package com.example.universityactivitytracker;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class RecordAsync {
    private AppDatabase db;

    public RecordAsync(Context context){
        db = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,
                "Record").build();
    }

    public List<Record> getAllRecords(){
        try{
            return new GetHolidaysAsync().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    private class GetHolidaysAsync extends AsyncTask<Void, Void, List<Record>>{
        @Override
        protected List<Record> doInBackground(Void... voids) {
            return db.recordDao().getAll();
        }
    }

    public void insertRecord(Record record){
        new insertRecord().execute(record);
    }

    private class insertRecord extends AsyncTask<Record, Void, Void>{
        @Override
        protected Void doInBackground(Record... records){
            db.recordDao().insertAll(records);
            return null;
        }
    }
}
