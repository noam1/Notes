package com.example.user.myapplication;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by User on 06/10/2016.
 */
public interface NotesService {

    public Note createNewNote();
    public void updateNote(Note note);
    public List<Note> retrieve();

}
