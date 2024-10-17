package com.example.vjezba3;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository repository;
    private LiveData<List<Note>> allNotes;

    public NoteViewModel(Application application) {
        super(application);
        repository = new NoteRepository(application);
        allNotes = repository.getAllNotes();
    }

    public void insert(Note note) {
        repository.insert(note);
    }

    public void update(Note note) {
        repository.update(note);
    }

    public void deleteNoteById(int noteId) {
        repository.deleteNoteById(noteId);
    }

    public void delete(Note note) {
        repository.delete(note);
    }

    public LiveData<String> getNoteContentById(int noteId) {
        return repository.getNoteContentById(noteId);
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}
