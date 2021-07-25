package com.example.noteapp.room;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.noteapp.ui.note.NoteModel;

import java.util.List;

@androidx.room.Dao
public interface Dao {
    @Query("SELECT * FROM  notemodel")
    LiveData<List<NoteModel>> getAll();

    @Insert
    void insertTask(NoteModel noteModel);

    @Update
    void update(NoteModel noteModel);

    @Delete
    void delete(NoteModel noteModel);
}
