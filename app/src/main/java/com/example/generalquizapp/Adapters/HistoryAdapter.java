package com.example.generalquizapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.generalquizapp.Models.History;
import com.example.generalquizapp.R;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {


    private ArrayList<History> histories;


    public HistoryAdapter(ArrayList<History> histories) {
        this.histories = histories;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView category;
        TextView difficulty;
        TextView score;
        TextView time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

             category = itemView.findViewById(R.id.history_category);
             difficulty = itemView.findViewById(R.id.history_difficulty);
             score = itemView.findViewById(R.id.history_score);
             time = itemView.findViewById(R.id.history_time);


        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_history,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.category.setText(histories.get(position).getName());
        holder.difficulty.setText(histories.get(position).getDifficulty());
        holder.score.setText(histories.get(position).getScore());
        holder.time.setText(histories.get(position).getTime());


    }

    @Override
    public int getItemCount() {
        return histories.size();
    }


}
