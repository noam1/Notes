package com.example.user.myapplication;

import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 * Created by User on 06/10/2016.
 */
public class InternalStorageImplement implements NotesService
{

    Context context;
    @Override
    public Note createNewNote() {
        File f=context.getFilesDir();
        File note=new File(f,generateRandomId()+".txt");
        try {
            note.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Note note1=new Note("","",note.getName());
        return note1;
    }

    @Override
    public void updateNote(Note note) {


    }

    @Override
    public List<Note> retrieve() {
        return null;
    }

    public String generateRandomId(){
        Random rnd = new Random();
        char[] characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
        String id = "";
        for (int i = 0; i < 20; i++){
            id += characters[rnd.nextInt(characters.length)];
        }
        return id;
    }
}
