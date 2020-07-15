package com.example.generalquizapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.generalquizapp.Models.Category_E;
import com.example.generalquizapp.Models.Category_U;
import com.example.generalquizapp.R;
import com.example.generalquizapp.Views.CategoriesPage;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {


    private ArrayList categories;
    private OnClickListener listener;
    private int num;
    private String name;
    private int easy_score;
    private int medium_score;
    private int hard_score;
    private Context context;
    private int cat_id;
    
    private long lastClicked = 0;


    public CategoryAdapter(ArrayList categoryArrayList, Context context) {
        this.categories = categoryArrayList;
        this.context = context;
    }


    public void setCategories(ArrayList categories) {
        this.categories = categories;

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView numcategory;
        TextView categoryname;
        TextView topscoreText;
        TextView easy_score;
        TextView medium_score;
        TextView hard_score;
        TextView easy_text;
        TextView medium_text;
        TextView hard_text;



        ImageView imageView;
        Spinner spinner;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            numcategory = itemView.findViewById(R.id.numCategory);
            categoryname = itemView.findViewById(R.id.categoryName);
            imageView = itemView.findViewById(R.id.pressedImage);
            topscoreText = itemView.findViewById(R.id.topScore);
            easy_score = itemView.findViewById(R.id.easy_score_text);
            medium_score = itemView.findViewById(R.id.medium_score_text);
            hard_score = itemView.findViewById(R.id.hard_score_text);
            easy_text = itemView.findViewById(R.id.easy_score);
            medium_text = itemView.findViewById(R.id.medium_score);
            hard_text = itemView.findViewById(R.id.hard_score);

            spinner = itemView.findViewById(R.id.spinner);


        }

        void bind(final int num, final String category) {


                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (SystemClock.elapsedRealtime()-lastClicked<2000) {

                            Toast.makeText(context, "In progress...", Toast.LENGTH_SHORT).show();
                            return;

                        }

                        lastClicked = SystemClock.elapsedRealtime();

                        cat_id = num;
                        listener.OnClick(num, spinner.getSelectedItem().toString(), category);
                    }
                });
            


        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_category, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        String[] diffs = new String[]{"easy", "normal", "hard"};
        String[] darajalar = new String[]{"qiyin"};


        if (categories.get(position) instanceof Category_E) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, diffs);
            holder.spinner.setAdapter(adapter);

            num = ((Category_E) categories.get(position)).get_ID();
            name = ((Category_E) categories.get(position)).getName();
            easy_score = ((Category_E) categories.get(position)).getEasy_score();
            medium_score = ((Category_E) categories.get(position)).getMedium_score();
            hard_score = ((Category_E) categories.get(position)).getHard_score();

            holder.topscoreText.setText(R.string.topscore);
            holder.easy_text.setText(R.string.easy);
            holder.medium_text.setText(R.string.normal);
            holder.hard_text.setText(R.string.hard);
        }

        if (categories.get(position) instanceof Category_U) {

            ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, darajalar);
            holder.spinner.setAdapter(adapter);

            num = ((Category_U) categories.get(position)).get_Id();
            name = ((Category_U) categories.get(position)).getName();
            easy_score = ((Category_U) categories.get(position)).getEasy_score();
            medium_score = ((Category_U) categories.get(position)).getMedium_score();
            hard_score = ((Category_U) categories.get(position)).getHard_score();

            holder.topscoreText.setText(R.string.yuqoriball);
            holder.easy_text.setText(R.string.oson);
            holder.medium_text.setText(R.string.normal_uzb);
            holder.hard_text.setText(R.string.qiyin);

        }


        holder.numcategory.setText(String.valueOf(num));
        holder.categoryname.setText(name);
        holder.easy_score.setText(" "+easy_score * 2);
        holder.medium_score.setText(" "+medium_score * 2);
        holder.hard_score.setText(" "+hard_score * 3);

        holder.bind(num,name);


    }


    @Override
    public int getItemCount() {
        return categories.size();
    }

    public interface OnClickListener {
        public void OnClick(int index, String difficulty,String category);
    }

    public void setOnClickListener(OnClickListener onClickListener) {

        this.listener = onClickListener;

    }

    public int getId() {

        return cat_id;
    }


}
