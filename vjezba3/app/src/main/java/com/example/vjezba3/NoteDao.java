package com.example.vjezba3;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("SELECT * FROM notes")
    LiveData<List<Note>> getAllNotes();

    @Query("SELECT note_content FROM notes WHERE id = :noteId")
    LiveData<String> getNoteContentById(int noteId);

    @Query("SELECT * FROM notes WHERE id = :noteId")
    Note getNoteById(int noteId);

    @Query("DELETE FROM notes")
    void deleteAllNotes();

    @Query("DELETE FROM notes WHERE id = :noteId")
    void deleteNoteById(int noteId);
}
