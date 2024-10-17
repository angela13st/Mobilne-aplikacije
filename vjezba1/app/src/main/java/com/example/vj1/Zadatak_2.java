package com.example.vj1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Zadatak_2 extends AppCompatActivity {

    private Button izracunaj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zadatak2);
        izracunaj= findViewById(R.id.button);
        izracunaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText a=(EditText) findViewById(R.id.prvi_broj);
                float x = Float.parseFloat(a.getText().toString());
                EditText b=(EditText) findViewById(R.id.drugi_broj);
                float y= Float.parseFloat(b.getText().toString());
                EditText o=(EditText) findViewById(R.id.operacija);
                String op= o.getText().toString();
                float r=0;
                TextView rezultat=findViewById(R.id.rezultat);

                if(op.equals("+"))
                {
                    r=x+y;
                }
                else if(op.equals("-"))
                {
                    r=x-y;
                }
                else if (op.equals("*"))
                {
                    r=x*y;
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

                }
                else
                {
                    CharSequence text = "Neispravan operator!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(getApplicationContext()  , text, duration);
                    toast.show();

                }
                rezultat.setText(String.format("%.2f", r));
            }
        });
    }


}