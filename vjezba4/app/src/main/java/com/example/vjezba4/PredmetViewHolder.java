package com.example.vjezba4;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PredmetViewHolder extends RecyclerView.ViewHolder {

    private TextView txtIme;
//    private TextView txtGodina;
//    private TextView txtPredavac;

    public PredmetViewHolder(@NonNull View itemView) {
        super(itemView);

        // Inicijalizacija elemenata unutar ViewHolder-a
        txtIme = itemView.findViewById(R.id.txtIme);
//        txtGodina = itemView.findViewById(R.id.txtGodina);
//        txtPredavac = itemView.findViewById(R.id.txtPredavac);
    }

    public void bind(Predmet predmet) {
        // Postavljanje podataka unutar ViewHolder-a
        txtIme.setText(predmet.getIme());
//        txtGodina.setText(String.valueOf(predmet.getGodina()));
//        txtPredavac.setText(predmet.getPredavac());
    }
}
