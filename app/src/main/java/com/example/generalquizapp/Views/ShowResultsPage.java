package com.example.generalquizapp.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.print.PrinterId;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.generalquizapp.Presenters.ResultPresenter;
import com.example.generalquizapp.R;

import javax.net.ssl.SSLHandshakeException;

public class ShowResultsPage extends AppCompatActivity {

    TextView total;
    TextView correct;
    TextView wrong;
    TextView totalText;
    TextView correctText;
    TextView wrongText;
    TextView your_results_text;

    TextView recommendation;
    Button restart;
    Button back;

    private String LANGUAGE;
    private int ID;
    private String DIFFICULTY;
    private ResultPresenter presenter;

    private long lastClicked=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_results_page);


        your_results_text = findViewById(R.id.your_results_text);
        total = findViewById(R.id.total_Q);
        correct = findViewById(R.id.correct_Q);
        wrong = findViewById(R.id.wrong_Q);
        totalText = findViewById(R.id.total);
        correctText = findViewById(R.id.correct);
        wrongText = findViewById(R.id.wrong);

        restart = findViewById(R.id.restart);
        back = findViewById(R.id.back_to_main);
        recommendation = findViewById(R.id.score);

        ID = getIntent().getIntExtra(MainActivity.CATEGORY, 0);
        LANGUAGE = getIntent().getStringExtra(CategoriesPage.EXTRA_LANG);
        DIFFICULTY = getIntent().getStringExtra(CategoriesPage.DIFFICULTY_EXTRA);
        presenter = new ResultPresenter(this, LANGUAGE, DIFFICULTY);


        if (LANGUAGE.equals("e")) {
            your_results_text.setText(R.string.results);
            totalText.setText(R.string.total_question);
            correctText.setText(R.string.corrects);
            wrongText.setText(R.string.wrongs);
            restart.setText(R.string.restart);
            back.setText(R.string.back_to_main);
        } else {
            your_results_text.setText(R.string.natijalar);
            totalText.setText(R.string.jamisavol);
            correctText.setText(R.string.togrijavob);
            wrongText.setText(R.string.notogrijavob);
            restart.setText(R.string.qayta_boshlash);
            back.setText(R.string.boshiga_qaytish);
        }

        presenter.getResults();
        presenter.loadResults();
        presenter.updateScores();


        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (SystemClock.elapsedRealtime()-lastClicked<2000){
                    Toast.makeText(ShowResultsPage.this, "In progress...", Toast.LENGTH_SHORT).show();
                    return;
                }

                lastClicked = SystemClock.elapsedRealtime();

                presenter.updateScores();
                Intent resIntent = new Intent(ShowResultsPage.this, MainActivity.class);
                resIntent.putExtra(CategoriesPage.ID_EXTRA, ID);
                resIntent.putExtra(CategoriesPage.EXTRA_LANG, LANGUAGE);
                resIntent.putExtra(CategoriesPage.DIFFICULTY_EXTRA, DIFFICULTY);
                startActivity(resIntent);
                finish();


            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (SystemClock.elapsedRealtime()-lastClicked<2000){
                    Toast.makeText(ShowResultsPage.this, "In progress...", Toast.LENGTH_SHORT).show();
                    return;
                }

                lastClicked = SystemClock.elapsedRealtime();
                presenter.updateScores();
                finish();

            }
        });
    }


    public void loadResults(int total_question, int correct_answers, int wrong_answers) {

        total.setText(String.valueOf(total_question));
        correct.setText(String.valueOf(correct_answers));
        wrong.setText(String.valueOf(wrong_answers));
        if (LANGUAGE.equals("e")) {
            EnglishComments(correct_answers);
        } else {
            UzbekComments(correct_answers);
        }

    }

    public void EnglishComments(int correct_answers) {

        int total_score = 0;
        if (DIFFICULTY.equals("easy") || DIFFICULTY.equals("normal")) {
            total_score = correct_answers * 2;
        } else {
            total_score = correct_answers * 3;
        }

        if (correct_answers == 20) {

            recommendation.setText("Score: " + total_score + "\n" + "Excellent!");
            recommendation.setTextColor(Color.GREEN);

        }
        if (correct_answers == 18 || correct_answers == 19) {
            recommendation.setText("Score: " + total_score + "\n" + "Perfect!");
            recommendation.setTextColor(Color.YELLOW);
        }
        if (correct_answers >= 15 && correct_answers <= 17) {

            recommendation.setText("Score: " + total_score + "\n" + "Well done!");
            recommendation.setTextColor(Color.BLUE);
        } else {

            recommendation.setText("Score: " + total_score);

        }

    }

    public void UzbekComments(int correct_answers) {

        int total_score = 0;
        if (DIFFICULTY.equals("easy") || DIFFICULTY.equals("normal")) {
            total_score = correct_answers * 2;
        } else {
            total_score = correct_answers * 3;
        }

        if (correct_answers >= 17 && correct_answers <= 20) {

            recommendation.setText("to'plangan ball: " + total_score + "\n" + "Barakalla!");
            recommendation.setTextColor(Color.BLUE);
        } else {
            recommendation.setText("to'plangan ball: " + total_score);
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        presenter.updateScores();
    }

    @Override
    protected void onStop() {
        super.onStop();


    }
}
