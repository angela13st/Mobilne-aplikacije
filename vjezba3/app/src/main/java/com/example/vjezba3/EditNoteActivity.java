package com.example.vjezba3;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.appcompat.app.AlertDialog;

public class EditNoteActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.notes.REPLY";
    private EditText editTextNote;
    private int noteId;
    private NoteViewModel noteViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        editTextNote = findViewById(R.id.editTextNote);

        Button buttonUpdate = findViewById(R.id.buttonUpdate);
        Button buttonDelete = findViewById(R.id.buttonDelete);

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateNote();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteNote();
            }
        });

        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        Intent intent = getIntent();
        if (intent.hasExtra("NOTE_ID")) {
            noteId = intent.getIntExtra("NOTE_ID", -1);
            observeNoteContent();
        }
    }

    private void observeNoteContent() {
        noteViewModel.getNoteContentById(noteId).observe(this, noteContent -> {
            editTextNote.setText(noteContent);
        });
    }

    private void updateNote() {
        String updatedNoteText = editTextNote.getText().toString();

        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, updatedNoteText);
        replyIntent.putExtra("NOTE_ID", noteId);
        setResult(RESULT_OK, replyIntent);
        finish();
    }

    private void deleteNote() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to delete this note?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        performDeleteNote();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

        builder.create().show();
    }

    private void performDeleteNote() {
        noteViewModel.deleteNoteById(noteId);

        Intent replyIntent = new Intent();
        replyIntent.putExtra("DELETE_NOTE", true);
        replyIntent.putExtra("NOTE_ID", noteId);
        setResult(RESULT_OK, replyIntent);
        finish();
    }

}
