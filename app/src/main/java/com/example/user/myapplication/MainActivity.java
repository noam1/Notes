package com.example.user.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
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
import android.widget.GridView;
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

    private GridView gridView;
    private FloatingActionButton fab;

    private ArrayAdapter<Note> notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteService = new InternalStorageImplement(this);

        gridView = (GridView)findViewById(R.id.grid);

        /*ImageButton plus=(ImageButton)findViewById(R.id.plus_btn);
        ImageButton minus=(ImageButton)findViewById(R.id.minus_btn);*/

        ArrayList<Note> notesArrayList = new ArrayList<>();

        notesAdapter = new ArrayAdapter<Note>(this, android.R.layout.simple_list_item_1,  notesArrayList);

        gridView.setAdapter(notesAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Note note = notesAdapter.getItem(position);

                //TODO: do stuff
            }
        });

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
            {
                Note delNote = notesAdapter.getItem(position);
                new DeleteNoteTask().execute(delNote);

                return true;
            }
        });

        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                new NewNoteTask().execute();
            }
        });

        /*plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notesAdapter.add(noteService.createNewNote());
            }
        });*/

        new LoadAllNotes().execute();
    }

    @Override
    protected void onResume()
    {
        new LoadAllNotes().execute();

        super.onResume();
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

    class LoadAllNotes extends AsyncTask<Void, Void, ArrayList<Note>>
    {
        @Override
        protected ArrayList<Note> doInBackground(Void... params)
        {
            ArrayList<String> fileNames = noteService.getNoteNames();
            ArrayList<Note> allNotes = new ArrayList<>();

            for (String name : fileNames)
            {
                Note note = noteService.loadNote(name);
                allNotes.add(note);
            }

            return allNotes;
        }

        @Override
        protected void onPostExecute(ArrayList<Note> allNotes)
        {
            notesAdapter.clear();

            for (Note note : allNotes)
                notesAdapter.add(note);

            notesAdapter.notifyDataSetChanged();
        }
    }

    class NewNoteTask extends AsyncTask<Void, Void, Note>
    {
        @Override
        protected Note doInBackground(Void... params)
        {
            Note note = noteService.createNewNote();

            return note;
        }

        @Override
        protected void onPostExecute(Note note)
        {
            Intent editIntent = new Intent(MainActivity.this, EditActivity.class);
            editIntent.putExtra("NOTE", note);

            startActivity(editIntent);
        }
    }

    class DeleteNoteTask extends AsyncTask<Note, Void, ArrayList<Note>>
    {
        @Override
        protected ArrayList<Note> doInBackground(Note... params)
        {
            noteService.delete(params[0]);

            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Note> allNotes)
        {
            new LoadAllNotes().execute();
        }
    }
}
