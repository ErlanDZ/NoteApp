package com.example.noteapp.room;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

public class App extends Application {
    public static TaskDataBase instance = null;
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static TaskDataBase getInstance() {
        if (instance == null) {
            instance = Room.databaseBuilder(context, TaskDataBase.class, "taskDatabase")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
