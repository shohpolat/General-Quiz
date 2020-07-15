package com.example.generalquizapp.Presenters;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.generalquizapp.Models.Category_E;
import com.example.generalquizapp.Models.Category_U;
import com.example.generalquizapp.Models.Repository;
import com.example.generalquizapp.R;
import com.example.generalquizapp.Views.MainActivity;
import com.example.generalquizapp.Views.ShowResultsPage;

import java.util.ArrayList;

public class ResultPresenter {

    private ShowResultsPage view;
    private Repository repository;

    private int total_question = 0;
    private int correct_answers = 0;
    private int wrong_answers = 0;

    private int category_id;
    private String LANGUAGE;
    private String DIFFICULTY;

    private ArrayList categoriesList;

    public ResultPresenter(ShowResultsPage view, String language,String difficulty) {
        this.view = view;
        repository = Repository.getInstance(this.view);
        this.LANGUAGE = language;
        this.DIFFICULTY = difficulty;
        if (LANGUAGE.equals("e")) {
            categoriesList = repository.getAllEnglishCategories();
        } else {
            categoriesList = repository.getAllUzbekCategories();
        }
    }


    public void getResults() {

        total_question = view.getIntent().getIntExtra(MainActivity.TOTAL_QUESTION, 0);
        correct_answers = view.getIntent().getIntExtra(MainActivity.CORRECTS, 0);
        wrong_answers = total_question - correct_answers;
        category_id = view.getIntent().getIntExtra(MainActivity.CATEGORY, 0);


        if (LANGUAGE.equals("e")) {
            String category = ((Category_E) categoriesList.get(category_id-1)).getName();
            repository.insertToHistory(category, DIFFICULTY, String.valueOf(correct_answers), LANGUAGE);
        }else {
            String category = ((Category_U) categoriesList.get(category_id-1)).getName();
            repository.insertToHistory(category, DIFFICULTY, String.valueOf(correct_answers), LANGUAGE);
        }

    }


    public void updateScores() {

        int top_score = 0;
        int id = 0;
        Toast.makeText(view, DIFFICULTY, Toast.LENGTH_SHORT).show();
        for (int i = 0; i < categoriesList.size(); i++) {

            if (categoriesList.get(i) instanceof Category_E) {
                id = ((Category_E) categoriesList.get(i)).get_ID();

                if (DIFFICULTY.equals("easy")){

                top_score = ((Category_E) categoriesList.get(i)).getEasy_score();

                }else if (DIFFICULTY.equals("normal")){

                top_score = ((Category_E) categoriesList.get(i)).getMedium_score();

                }else if (DIFFICULTY.equals("hard")){

                top_score = ((Category_E) categoriesList.get(i)).getHard_score();

                }
                if (category_id == id) {


                    if (correct_answers > top_score) {
                        top_score = correct_answers;
                        repository.updateEnglishCategory(top_score, category_id,DIFFICULTY);
                        return;
                    }

                }
            } else {
                id = ((Category_U) categoriesList.get(i)).get_Id();
                if (DIFFICULTY.equals("easy")){

                    top_score = ((Category_U) categoriesList.get(i)).getEasy_score();

                }else if (DIFFICULTY.equals("normal")){

                    top_score = ((Category_U) categoriesList.get(i)).getMedium_score();

                }else if (DIFFICULTY.equals("hard")){

                    top_score = ((Category_U) categoriesList.get(i)).getHard_score();

                }
                if (category_id == id) {


                    if (correct_answers > top_score) {
                        top_score = correct_answers;
                        repository.updateUzbekCategory(top_score, category_id,DIFFICULTY);
                        return;
                    }

                }
            }


        }


    }

    public void loadResults() {
        view.loadResults(total_question, correct_answers, wrong_answers);
    }





}
