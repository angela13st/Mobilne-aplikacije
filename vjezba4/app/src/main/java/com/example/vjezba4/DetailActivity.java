package com.example.vjezba4;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailActivity extends AppCompatActivity {

    private DatabaseReference predmetRef;
    private EditText imeEditText;
    private EditText godinaEditText;
    private EditText predavacEditText;
    private String predmetId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        predmetRef = database.getReference("predmeti");

        imeEditText = findViewById(R.id.editTextIme);
        godinaEditText = findViewById(R.id.editTextGodina);
        predavacEditText = findViewById(R.id.editTextPredavac);

        predmetId = getIntent().getStringExtra("predmetId");
        loadPredmetDetails(predmetId);
    }

    private void loadPredmetDetails(String predmetId) {
        predmetRef.child(predmetId).get().addOnSuccessListener(dataSnapshot -> {
            if (dataSnapshot.exists()) {
                Predmet predmet = dataSnapshot.getValue(Predmet.class);
                if (predmet != null) {
                    imeEditText.setText(predmet.getIme());
                    godinaEditText.setText(String.valueOf(predmet.getGodina()));
                    predavacEditText.setText(predmet.getPredavac());
                }
            }
        });
    }

    public void saveChanges(View view) {
        String ime = imeEditText.getText().toString();
        int godina = Integer.parseInt(godinaEditText.getText().toString());
        String predavac = predavacEditText.getText().toString();

        predmetRef.child(predmetId).child("ime").setValue(ime);
        predmetRef.child(predmetId).child("godina").setValue(godina);
        predmetRef.child(predmetId).child("predavac").setValue(predavac)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Changes saved successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(this, "Failed to save changes", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
