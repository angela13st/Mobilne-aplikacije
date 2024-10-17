package com.example.vjezba2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryViewHolder> {
    private List<Repository> repositories;

    public RepositoryAdapter(List<Repository> repositories) {
        this.repositories = repositories;
    }

    @NonNull
    @Override
    public RepositoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repository, parent, false);
        return new RepositoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepositoryViewHolder holder, int position) {
        Repository repository = repositories.get(position);

        holder.textViewRepoName.setText(repository.getRepoName());
        holder.textViewStarsCount.setText(String.valueOf(repository.getStarsCount()));

        Glide.with(holder.itemView.getContext())
                .load(repository.getOwnerAvatarUrl())
                .into(holder.imageViewAvatar);
    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }

    public void setRepositories(List<Repository> repositories) {
        this.repositories = repositories;
        notifyDataSetChanged();
    }
}
