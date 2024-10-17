package com.example.vjezba3;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    public void insert(Note note) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> noteDao.insert(note));
    }

    public void update(Note note) {
        new UpdateNoteAsyncTask(noteDao).execute(note);
    }

    public void deleteNoteById(int noteId) {
        new DeleteNoteAsyncTask(noteDao).execute(noteId);
    }

    public void delete(Note note) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> noteDao.delete(note));
    }

    public LiveData<String> getNoteContentById(int noteId) {
        return noteDao.getNoteContentById(noteId);
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        private InsertNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDao noteDao;

        private UpdateNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }


    private static class DeleteNoteAsyncTask extends AsyncTask<Integer, Void, Void> {
        private NoteDao noteDao;

        private DeleteNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Integer... noteIds) {
            int noteId = noteIds[0];
            noteDao.deleteNoteById(noteId);
            return null;
        }
    }
}
