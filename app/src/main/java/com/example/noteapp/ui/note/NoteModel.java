package com.example.noteapp.ui.note;

import java.io.Serializable;

public class NoteModel implements Serializable {
    String title;

    public NoteModel(String title) {
        this.title = title;
    }
    public NoteModel(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
