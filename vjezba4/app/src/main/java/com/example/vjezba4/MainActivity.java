package com.example.vjezba4;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference predmetiRef;
    private RecyclerView recyclerView;
    private FirebaseRecyclerAdapter<Predmet, PredmetViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null) {
            // If the user is not logged in, redirect to the login activity
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }

        // Initialize Firebase references
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        predmetiRef = database.getReference("predmeti");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        Query query = predmetiRef.orderByChild("ime"); // Order by the "ime" field

        FirebaseRecyclerOptions<Predmet> options =
                new FirebaseRecyclerOptions.Builder<Predmet>()
                        .setQuery(query, Predmet.class)
                        .build();

        adapter = new FirebaseRecyclerAdapter<Predmet, PredmetViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull PredmetViewHolder holder, int position, @NonNull Predmet model) {
                // Bind data to ViewHolder
                holder.bind(model);

                // Set click listener if needed
                holder.itemView.setOnClickListener(v -> {
                    // Handle item click, e.g., open detail activity
                    openDetailActivity(getRef(position).getKey());
                });
            }

            @NonNull
            @Override
            public PredmetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                // Create a new instance of PredmetViewHolder
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_predmet, parent, false);
                return new PredmetViewHolder(view);
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    private void openDetailActivity(String predmetId) {
        // Open DetailActivity and pass the Predmet ID
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("predmetId", predmetId);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Stop listening when the activity is destroyed
        if (adapter != null) {
            adapter.stopListening();
        }
    }
}
