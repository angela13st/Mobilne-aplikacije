package com.example.vjezba4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PredmetAdapter extends RecyclerView.Adapter<PredmetAdapter.PredmetViewHolder> {

    private List<Predmet> predmeti;
    private OnItemClickListener onItemClickListener;

    public PredmetAdapter(List<Predmet> predmeti) {
        this.predmeti = predmeti;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public PredmetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_predmet, parent, false);
        return new PredmetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PredmetViewHolder holder, int position) {
        Predmet predmet = predmeti.get(position);
        holder.bind(predmet);

        // Set up the click listener for each item
        holder.itemView.setOnClickListener(view -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return predmeti.size();
    }

    static class PredmetViewHolder extends RecyclerView.ViewHolder {

        private final TextView txtIme;
   //     private final TextView txtGodina;
   //     private final TextView txtPredavac;

        public PredmetViewHolder(@NonNull View itemView) {
            super(itemView);
            txtIme = itemView.findViewById(R.id.txtIme);
       //     txtGodina = itemView.findViewById(R.id.txtGodina);
       //     txtPredavac = itemView.findViewById(R.id.txtPredavac);
        }

        public void bind(Predmet predmet) {
            txtIme.setText(predmet.getIme());
       //     txtGodina.setText(String.valueOf(predmet.getGodina()));
     //       txtPredavac.setText(predmet.getPredavac());
        }
    }
}
