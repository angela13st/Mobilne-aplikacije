package com.example.vjezba3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class AddNoteActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.notes.REPLY";

    private EditText editTextNote;
    private NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTextNote = findViewById(R.id.editTextNote);

        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        Button buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNote();
            }
        });
    }

    private void saveNote() {
        String noteText = editTextNote.getText().toString();

        if (!noteText.isEmpty()) {
            Note note = new Note(noteText);
            noteViewModel.insert(note);

            Intent replyIntent = new Intent();
            replyIntent.putExtra(EXTRA_REPLY, noteText);
            setResult(RESULT_OK, replyIntent);
        }

        finish();
    }
}
