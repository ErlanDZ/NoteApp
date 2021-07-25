package com.example.noteapp.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.noteapp.ui.note.NoteModel;

@Database(entities = NoteModel.class, version = 3, exportSchema = false)
public abstract class TaskDataBase extends RoomDatabase {
    public abstract Dao dao();
}
