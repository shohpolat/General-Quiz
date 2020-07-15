package com.example.generalquizapp.Presenters;

import android.graphics.Color;
import android.os.CountDownTimer;

import com.example.generalquizapp.Dialogs.OnBackPressedDialogs;
import com.example.generalquizapp.Models.Category_E;
import com.example.generalquizapp.Views.MainActivity;
import com.example.generalquizapp.Models.Question;
import com.example.generalquizapp.Models.Repository;
import com.example.generalquizapp.other.Timer;

import java.util.ArrayList;
import java.util.Locale;

public class MainPresenter {

    private Repository repository;
    private MainActivity view;

    private ArrayList<Question> questionsList;
    private Question currentQuestion;
    private int total_questions;
    private int question_counter = 0;
    private int score = 0;
    private boolean answered = false;
    private int selected = 0;
    private int lastselected = 0;
    private final long COUNTDOWN_TOTAL = 40000;
    private long timeLeftInMillis = 0;
    private CountDownTimer countDownTimer;
    private int cat_ID;
    private String LANGUAGE;
    private String DIFFICULTY;
    private boolean isDialogShown = false;


    public MainPresenter(MainActivity view, int id, String language,String difficulty) {
        this.view = view;
        this.repository = Repository.getInstance(this.view);
        this.cat_ID = id;
        this.LANGUAGE = language;
        this.DIFFICULTY = difficulty;
        questionsList = repository.getQuestions(cat_ID, LANGUAGE,DIFFICULTY);
        total_questions = questionsList.size();
    }


    public void prepareQuestionsPage() {

        if (!answered) {
            if (selected > 0) {
                checkAnswer();
            } else {
                if (LANGUAGE.equals("e")) {
                    view.showToast("Please select an answer!");
                } else {

                    view.showToast("Javobingizni tanlang!");
                }
            }
        } else {
            showNextQuestion();
        }

    }

    public void showNextQuestion() {
        view.makeUncheckedAll();
        view.makeClickable();

        if (question_counter < total_questions) {
            currentQuestion = questionsList.get(question_counter);
            question_counter++;
            view.setQuestoinAndOps(currentQuestion.getQuestion(),
                    currentQuestion.getOption1(),
                    currentQuestion.getOption2(),
                    currentQuestion.getOption3(),
                    currentQuestion.getOption4(),
                    question_counter,
                    currentQuestion.getCategory(),
                    total_questions);


            selected = 0;
            answered = false;
            if (LANGUAGE.equals("e")) {
                view.setConfirm_next("confirm");

            } else {
                view.setConfirm_next("Tasdiqlash");

            }

            if (DIFFICULTY.equals("easy")){

                timeLeftInMillis = 20000;

            }else if (DIFFICULTY.equals("normal")){
                timeLeftInMillis = 30000;

            }else {
                timeLeftInMillis = COUNTDOWN_TOTAL;

            }

            countDownTimer = new Timer(timeLeftInMillis, 1000, this);

            countDownTimer.start();


        } else {
            finishQuiz();
        }
    }


    private void finishQuiz() {

        view.showResults(total_questions, score, cat_ID, LANGUAGE,DIFFICULTY);

    }

    private void checkAnswer() {


        countDownTimer.cancel();
        view.makeUnClickable();
        answered = true;

        if (selected == currentQuestion.getAnswer()) {
            score++;
        }

        if (question_counter < total_questions) {
            if (LANGUAGE.equals("e")) {

                view.setConfirm_next("next");
            } else {
                view.setConfirm_next("Keyingisi");

            }
        } else {
            if (LANGUAGE.equals("e")) {

                view.setConfirm_next("finish");
            } else {

                view.setConfirm_next("Natijalarni\nKo'rish");
            }
        }
    }

    public void optionSelected(int index) {
        selected = index;
        if (lastselected != 0) {
            view.makeUnChecked(lastselected);
        }
        lastselected = selected;
        view.makeChecked(selected);
    }

    public void onTick(Long l) {
        timeLeftInMillis = l;

        int minutes = (int) ((timeLeftInMillis / 1000) / 60);
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        int color = Color.BLACK;

        String updatedTime = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        if (timeLeftInMillis < 10000) {
            color = Color.RED;
        }

        view.setTime(updatedTime, color);
    }

    public void onFinish() {
        view.makeUnClickable();
        answered = true;

        if (question_counter < total_questions) {
            if (LANGUAGE.equals("e")) {
                view.setConfirm_next("next");

            } else {

                view.setConfirm_next("Keyingisi");
            }
        } else {
            if (LANGUAGE.equals("e")) {

                view.setConfirm_next("finish");

            } else {

                view.setConfirm_next("Natijalarni\nKo'rish");

            }
        }
    }

    public void OnBackClicked() {

        OnBackPressedDialogs dialogs = new OnBackPressedDialogs(LANGUAGE);
        dialogs.show(view.getSupportFragmentManager(), "warning");

    }

    public void cancelTimer() {

        countDownTimer.cancel();

    }

    public void resumeTimer() {

        if (!answered) {
            countDownTimer.cancel();
            countDownTimer = new Timer(timeLeftInMillis, 1000, this);
            countDownTimer.start();
        }
    }


}








































