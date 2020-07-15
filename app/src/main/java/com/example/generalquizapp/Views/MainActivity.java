package com.example.generalquizapp.Views;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.generalquizapp.Dialogs.OnBackPressedDialogs;
import com.example.generalquizapp.Models.DATABASE_HELPER;
import com.example.generalquizapp.Presenters.MainPresenter;
import com.example.generalquizapp.R;

public class MainActivity extends AppCompatActivity implements OnBackPressedDialogs.dialogOnClickListener {

    public static final String TOTAL_QUESTION = "total";
    public static final String CORRECTS = "corrects";
    public static final String CATEGORY = "category";


    TextView difficulty_text;
    TextView question_nr_text;
    TextView question_text;
    TextView timer;
    TextView option1;
    TextView option2;
    TextView option3;
    TextView option4;
    CardView card1;
    CardView card2;
    CardView card3;
    CardView card4;
    ImageView imgOp1;
    ImageView imgOp2;
    ImageView imgOp3;
    ImageView imgOp4;

    Button confirm_next;
    ImageButton back;




    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FindAllView();

        Intent intent = getIntent();
        int id = intent.getIntExtra(CategoriesPage.ID_EXTRA, 0);
        String LANGUAGE = intent.getStringExtra(CategoriesPage.EXTRA_LANG);
        String difficulty = intent.getStringExtra(CategoriesPage.DIFFICULTY_EXTRA);
        String category_name = intent.getStringExtra(CategoriesPage.CATEGORY_EXTRA);


        difficulty_text.setText(category_name+": "+difficulty);


        presenter = new MainPresenter(this, id, LANGUAGE,difficulty);

        presenter.showNextQuestion();

        confirm_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                presenter.prepareQuestionsPage();

            }
        });
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.optionSelected(1);
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.optionSelected(2);
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.optionSelected(3);
            }
        });
        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.optionSelected(4);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                presenter.OnBackClicked();
            }
        });


    }

    private void FindAllView() {
        question_nr_text = findViewById(R.id.question_nr);
        question_text = findViewById(R.id.question_text);
        timer = findViewById(R.id.timer);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        card1 = findViewById(R.id.card_op1);
        card2 = findViewById(R.id.card_op2);
        card3 = findViewById(R.id.card_op3);
        card4 = findViewById(R.id.card_op4);
        imgOp1 = findViewById(R.id.img_op1);
        imgOp2 = findViewById(R.id.img_op2);
        imgOp3 = findViewById(R.id.img_op3);
        imgOp4 = findViewById(R.id.img_op4);
        confirm_next = findViewById(R.id.confirm_next);
        back = findViewById(R.id.back);

        difficulty_text = findViewById(R.id.difficulty);
    }

    public void makeUncheckedAll() {

        imgOp1.setImageResource(R.drawable.unchecked);
        imgOp2.setImageResource(R.drawable.unchecked);
        imgOp3.setImageResource(R.drawable.unchecked);
        imgOp4.setImageResource(R.drawable.unchecked);

    }

    public void makeChecked(int index) {
        if (index == 1) {
            imgOp1.setImageResource(R.drawable.checked);
        }
        if (index == 2) {
            imgOp2.setImageResource(R.drawable.checked);
        }
        if (index == 3) {
            imgOp3.setImageResource(R.drawable.checked);
        }
        if (index == 4) {
            imgOp4.setImageResource(R.drawable.checked);
        }

    }

    public void makeUnChecked(int index) {
        if (index == 1) {
            imgOp1.setImageResource(R.drawable.unchecked);
        }
        if (index == 2) {
            imgOp2.setImageResource(R.drawable.unchecked);
        }
        if (index == 3) {
            imgOp3.setImageResource(R.drawable.unchecked);
        }
        if (index == 4) {
            imgOp4.setImageResource(R.drawable.unchecked);
        }

    }

    public void setQuestoinAndOps(String question, String op1, String op2, String op3, String op4, int questionCount, int category, int total_Questions) {

        question_nr_text.setText("- " + questionCount + " /" + total_Questions + " -");
        question_text.setText(question);
        option1.setText(op1);
        option2.setText(op2);
        option3.setText(op3);
        option4.setText(op4);

    }

    public void setConfirm_next(String string) {
        confirm_next.setText(string);
    }

    public void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    public void showResults(int total, int correct, int cat_id,String language,String difficulty) {

        Intent intent = new Intent(this, ShowResultsPage.class);
        intent.putExtra(TOTAL_QUESTION, total);
        intent.putExtra(CORRECTS, correct);
        intent.putExtra(CATEGORY, cat_id);
        intent.putExtra(CategoriesPage.EXTRA_LANG,language);
        intent.putExtra(CategoriesPage.DIFFICULTY_EXTRA,difficulty);
        startActivity(intent);
        finish();

    }


    public void setTime(String time, int color) {
        timer.setText(time);
        timer.setTextColor(color);
    }

    public void makeUnClickable() {
        card1.setAlpha((float) 0.8);
        card2.setAlpha((float) 0.8);
        card3.setAlpha((float) 0.8);
        card4.setAlpha((float) 0.8);
        card1.setClickable(false);
        card2.setClickable(false);
        card3.setClickable(false);
        card4.setClickable(false);
    }

    public void makeClickable() {
        card1.setAlpha((float) 1.0);
        card2.setAlpha((float) 1.0);
        card3.setAlpha((float) 1.0);
        card4.setAlpha((float) 1.0);
        card1.setClickable(true);
        card2.setClickable(true);
        card3.setClickable(true);
        card4.setClickable(true);
    }

    @Override
    public void onBackPressed() {

        presenter.OnBackClicked();

    }

    @Override
    public void OnOkClick() {

        finish();

    }

    @Override
    public void OnCancelClick() {

        presenter.resumeTimer();
    }

    @Override
    public void resumeTimer() {
        presenter.cancelTimer();
    }

}
