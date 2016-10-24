package com.example.user.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    private EditText editText;
    private Note note;

    private NotesService notesService;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        notesService = new InternalStorageImplement(this);

        note = (Note)getIntent().getSerializableExtra("NOTE");

        this.editText = (EditText)findViewById(R.id.edit);
        this.editText.setText(note.content);
    }

    @Override
    public void onBackPressed()
    {
        note.setContent(editText.getText().toString());

        new UpdateNote().execute(note);

        super.onBackPressed();
    }

    class UpdateNote extends AsyncTask<Note, Void, Void>
    {
        @Override
        protected Void doInBackground(Note... params)
        {
            notesService.updateNote(params[0]);

            return null;
        }
    }
}
