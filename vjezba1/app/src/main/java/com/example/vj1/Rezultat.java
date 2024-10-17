package com.example.vj1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Rezultat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rezultat);
        TextView rez= findViewById(R.id.rezultat);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String r = extras.getString("key");
            rez.setText(r);
        }

    }
}