package com.example.vjezba2;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RepositoryViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageViewAvatar;
    public TextView textViewRepoName;
    public TextView textViewStarsCount;

    public RepositoryViewHolder(@NonNull View itemView) {
        super(itemView);
        imageViewAvatar = itemView.findViewById(R.id.imageViewAvatar);
        textViewRepoName = itemView.findViewById(R.id.textViewRepoName);
        textViewStarsCount = itemView.findViewById(R.id.textViewStarsCount);
    }
}