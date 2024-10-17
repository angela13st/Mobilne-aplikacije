package com.example.vj1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Zadatak_2_2 extends AppCompatActivity {

    private Button izracunaj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zadatak22);
        Spinner spinner = (Spinner) findViewById(R.id.operacija);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.operacije,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        izracunaj= findViewById(R.id.button);izracunaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText a=(EditText) findViewById(R.id.prvi_broj);
                float x = Float.parseFloat(a.getText().toString());
                EditText b=(EditText) findViewById(R.id.drugi_broj);
                float y= Float.parseFloat(b.getText().toString());

                String op= spinner.getSelectedItem().toString();
                float r=0;
                Intent i = new Intent(Zadatak_2_2.this, Rezultat.class);

                if(op.equals("+"))
                {
                    r=x+y;
                    String rez=String.format("%.2f", r);
                    i.putExtra("key",rez );
                    startActivity(i);
                }
                else if(op.equals("-"))
                {
                    r=x-y;
                    String rez=String.format("%.2f", r);
                    i.putExtra("key",rez );
                    startActivity(i);
                }
                else if (op.equals("*"))
                {
                    r=x*y;
                    String rez=String.format("%.2f", r);
                    i.putExtra("key",rez );
                    startActivity(i);
                }
                else if (op.equals("/"))
                {
                    if(y==0)
                    {
                        CharSequence text = "Nije dozvoljeno dijeliti s 0!";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(getApplicationContext()  , text, duration);
                        toast.show();

                    }
                    else
                        r=x/y;
                        String rez=String.format("%.2f", r);
                        i.putExtra("key",rez );
                        startActivity(i);

                }
                else
                {
                    CharSequence text = "Neispravan operator!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(getApplicationContext()  , text, duration);
                    toast.show();

                }

            }
        });
    }


}