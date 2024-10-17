package com.example.vjezba3;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NotesViewHolder extends RecyclerView.ViewHolder {


    private final TextView textViewDescription;

    public NotesViewHolder(@NonNull View itemView) {
        super(itemView);
        textViewDescription = itemView.findViewById(R.id.text_view_note);
    }

    public void bind(Note note) {
        textViewDescription.setText(note.getNoteContent());
    }
}
