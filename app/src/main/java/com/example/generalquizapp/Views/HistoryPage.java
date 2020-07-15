package com.example.generalquizapp.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.generalquizapp.Presenters.HistoryPresenter;
import com.example.generalquizapp.R;

import org.w3c.dom.Text;

public class HistoryPage extends AppCompatActivity {

    TextView history;
    TextView category;
    TextView difficulty;
    TextView score;
    TextView time;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_page);

        loadViews();
        String language = getIntent().getStringExtra(CategoriesPage.EXTRA_LANG);

        if (language.equals("e")){
            setLocalToEnglish();
        }else {
            setLocalToUzbek();
        }

        HistoryPresenter presenter = new HistoryPresenter(this,language);
        presenter.showHistory();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void setLocalToUzbek() {
        history.setText(R.string.Arxiv);
        category.setText(R.string.fanlar_arxiv);
        difficulty.setText(R.string.daraja_arxiv);
        score.setText(R.string.ballar_arxiv);
        time.setText(R.string.vaqt_arxiv);
        back.setText(R.string.orqaga_arxiv);

    }

    private void setLocalToEnglish() {

        history.setText(R.string.history);
        category.setText(R.string.category_history);
        difficulty.setText(R.string.difficulty_history);
        score.setText(R.string.score_history);
        time.setText(R.string.time_history);
        back.setText(R.string.back_history);

    }

    private void loadViews(){
        history = findViewById(R.id.history_text);
        category = findViewById(R.id.hist_cat);
        difficulty = findViewById(R.id.hist_difficulty);
        score = findViewById(R.id.hist_score);
        time = findViewById(R.id.hist_time);
        back = findViewById(R.id.history_back);
    }
}
