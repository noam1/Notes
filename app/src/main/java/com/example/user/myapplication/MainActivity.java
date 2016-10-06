package com.example.user.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Note> notesArrayList;
    private NotesService noteService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView notesList=(ListView)findViewById(R.id.notesList);
        ImageButton plus=(ImageButton)findViewById(R.id.plus_btn);
        ImageButton minus=(ImageButton)findViewById(R.id.minus_btn);

        ArrayList<Note> notesArrayList  =new ArrayList<>();
        final ArrayAdapter<Note> notesAdapter = new ArrayAdapter<Note>(this, android.R.layout.simple_list_item_1,  notesArrayList);
        notesList.setAdapter(notesAdapter);
        notesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                notesAdapter.getItem(position);
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notesAdapter.add(noteService.createNewNote());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*public void createNewNote() throws IOException {
        File f=getFilesDir();
        File note=new File(f,generateRandomId()+".txt");
        note.createNewFile();
    }

    public void editNote(String noteName)
    {
        String a="cpntent";
        try {
            FileOutputStream fileOutputStream= openFileOutput(noteName,MODE_PRIVATE);
            fileOutputStream.write(a.getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/



/*
    public boolean fileExists(String fileName)
    {
        String[] files = fileList();
        for (String file : files) {
            if (file.equals(fileName)) {
                return true;
            }
        }
        return false;
    }*/
}
